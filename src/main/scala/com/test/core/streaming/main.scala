import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.log4j.Logger

object SimpleApp {
  val Log = Logger.getLogger(SimpleApp.this.getClass().getSimpleName())

  def main(args: Array[String]) {
    // val mainThread = Thread.currentThread();
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleApp")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).foreachRDD( rdd => 
      if(rdd.isEmpty() == false){
        rdd.foreach(x => println(x._2))
      }
    )

    Log.warn("DEBUG info:" + zkQuorum)
    sys.addShutdownHook(() => {
      println("< received SIGTERM (shutdown hook)")
      Log.warn("----CTRL-C HANDLING-----")
      Log.error("----CTRL-C HANDLING----- Log error")
      // val sc = SparkContext.getOrCreate()
      try ssc.stop(true) catch { case _ : Throwable => { } }
      // sc.stop()
      sys.exit(0)
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
  