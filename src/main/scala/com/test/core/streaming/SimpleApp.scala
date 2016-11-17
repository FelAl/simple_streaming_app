import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.NullWritable
import org.apache.log4j.Logger
import org.apache.spark.sql._
import org.apache.spark.sql.functions.{concat, lit}
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat

object SimpleApp {
  val Log = Logger.getLogger(SimpleApp.this.getClass().getSimpleName())

  def main(args: Array[String]) {
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleApp")
    val sc = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sc, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val stream = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    println("=======")
    println(stream.getClass)
    stream.foreachRDD( rdd => {
      if(rdd.isEmpty() == false){
        import sqlContext.implicits._
        rdd.foreach(x => println(x._2 + "olololol"))
        println(rdd.getClass)
        val dataframe = rdd.toDF()
        println(dataframe.getClass)
        dataframe.show()
        println("=====")
        dataframe.printSchema()
        println("=====")
        val newdf = dataframe.drop("_1")
        newdf.show()
        newdf.printSchema()
        println("=====")
        // println(dataframe._1)
        // println(dataframe._2)
        println("===writing to hdfs")
        newdf.write.mode("append").text("hdfs://0.0.0.0:9000/test2.txt")
        // works without append
        // rdd.saveAsHadoopFile("hdfs://0.0.0.0:9000/rdd.txt", classOf[String], classOf[String], 
        //   classOf[MultipleTextOutputFormat[Any,Any]])
        // doesn't work
        // rdd.saveAsSequenceFile("hdfs://0.0.0.0:9000/")
      }  
    })
    Log.warn("DEBUG info:" + zkQuorum)

    sys.ShutdownHookThread({
      println("< received SIGTERM (shutdown hook)")
      try {
        ssc.stop(stopSparkContext = true, stopGracefully = true)
        } catch {
          case e: Throwable => {println("exception on ssc.stop(true, true) occured")}
        }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
