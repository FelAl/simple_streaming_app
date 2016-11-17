import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.log4j.Logger

object SimpleApp {
  val Log = Logger.getLogger(SimpleApp.this.getClass().getSimpleName())

  def main(args: Array[String]) {
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleApp")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val stream = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    stream.foreachRDD( rdd => 
      if(rdd.isEmpty() == false){
        rdd.foreach(x => println(x._2))
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
