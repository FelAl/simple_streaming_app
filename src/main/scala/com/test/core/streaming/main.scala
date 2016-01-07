import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.log4j.Logger

object SimpleApp {
  val Log = Logger.getLogger(SimpleApp.this.getClass().getSimpleName())

  def main(args: Array[String]) {
    val mainThread = Thread.currentThread();
    Runtime.getRuntime.addShutdownHook(new Thread() {override def run = {
      Log.info("----CTRL-C HANDLING-----")
      mainThread.join()
    }})
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleApp")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    words.print()
    Log.info("DEBUG info:" + zkQuorum)
    
    ssc.start()
    ssc.awaitTermination()
  }
}
  