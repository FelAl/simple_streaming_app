val spark_core = "org.apache.spark" % "spark-core_2.11" % "1.5.2"
val spark_str_kafka   = "org.apache.spark" % "spark-streaming-kafka_2.11" % "1.5.2"
val spark_stream   = "org.apache.spark" % "spark-streaming_2.11" % "1.5.2"


lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "simple_streaming_app",
    libraryDependencies += spark_core,
    libraryDependencies += spark_str_kafka,
    libraryDependencies += spark_stream
  )
