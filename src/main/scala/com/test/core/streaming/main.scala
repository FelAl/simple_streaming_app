import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object SimpleApp {
  def main(args: Array[String]) {
    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SimpleStreamingAppKafka")
    val ssc = new StreamingContext(sparkConf, Seconds(1))
    val topicMap = topics.split(",").map((_, 1)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    words.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}
