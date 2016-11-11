// import AssemblyKeys._


name := "SimpleApp"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.5.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.5.2"
// // libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.3"
// libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.6.3"
libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.6.3"



assemblyMergeStrategy in assembly := {
  // case PathList("org", "apache", "htrace", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last

  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}



// mainClass in assembly := Some("SimpleApp")

// val spark_core = "org.apache.spark" %% "spark-core" % "1.5.2"
// val spark_str_kafka   = "org.apache.spark" %% "spark-streaming-kafka" % "1.5.2"
// val spark_stream   = "org.apache.spark" %% "spark-streaming" % "1.5.2"


// lazy val buildSettings = Seq(
//   version := "0.1-SNAPSHOT",
//   organization := "com.example",
//   scalaVersion := "2.11.7"
// )

// lazy val root = (project in file(".")).
//   settings(buildSettings: _*).
//   settings(assemblySettings: _*).
//   settings(
//     name := "simple_streaming_app",
//     libraryDependencies += spark_core,
//     libraryDependencies += spark_stream,
//     libraryDependencies += spark_str_kafka
//   )

// assemblyMergeStrategy in assembly := {
//   case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
//   case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
//   case PathList("org", "apache", xs @ _*) => MergeStrategy.last
//   case PathList("com", "google", xs @ _*) => MergeStrategy.last
//   case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
//   case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
//   case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
//   case "about.html" => MergeStrategy.rename
//   case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
//   case "META-INF/mailcap" => MergeStrategy.last
//   case "META-INF/mimetypes.default" => MergeStrategy.last
//   case "plugin.properties" => MergeStrategy.last
//   case "log4j.properties" => MergeStrategy.last
//   case x =>
//     val oldStrategy = (assemblyMergeStrategy in assembly).value
//     oldStrategy(x)
// }

// mainClass in assembly := Some("SimpleApp")
