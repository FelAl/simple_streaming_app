import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.NullWritable
import org.apache.log4j.Logger
// import org.apache.spark.sql._

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat

object SimpleApp {
  val Log = Logger.getLogger(SimpleApp.this.getClass().getSimpleName())

  // def write(uri: String, filePath: String, data: Array[Byte]) = {
  //   System.setProperty("HADOOP_USER_NAME", "Alexander")
  //   val path = new Path(filePath)
  //   val conf = new Configuration()
  //   conf.set("fs.defaultFS", uri)
  //   val fs = FileSystem.get(conf)
  //   val os = fs.create(path)
  //   os.write(data)
  //   fs.close()
  // }

  def main(args: Array[String]) {
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleApp")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val stream = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    stream.foreachRDD( rdd => 
      if(rdd.isEmpty() == false){
        rdd.foreach(x => println(x._2 + "olololol"))
        // works without append
        rdd.saveAsHadoopFile("hdfs://0.0.0.0:9000/rdd.txt", classOf[String], classOf[String], 
          classOf[MultipleTextOutputFormat[Any,Any]])
        // doesn't work
        // rdd.saveAsSequenceFile("hdfs://0.0.0.0:9000/")
      }
    )
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
