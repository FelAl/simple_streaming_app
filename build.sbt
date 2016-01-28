import AssemblyKeys._

val spark_core = "org.apache.spark" % "spark-core_2.11" % "1.5.2" % "provided"
val spark_str_kafka   = "org.apache.spark" % "spark-streaming-kafka_2.11" % "1.5.2" % "provided"
val spark_stream   = "org.apache.spark" % "spark-streaming_2.11" % "1.5.2" % "provided"


lazy val buildSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.example",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(buildSettings: _*).
  settings(assemblySettings: _*).
  settings(
    name := "simple_streaming_app",
    libraryDependencies += spark_core,
    libraryDependencies += spark_stream,
    libraryDependencies += spark_str_kafka
  )
mainClass in assembly := Some("SimpleApp")
