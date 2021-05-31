name := "SparkKafkaCassandra"

version := "0.1"

scalaVersion := "2.11.12"
val sparkVersion = "2.3.2"

val cassandraConnectorVersion = "2.5.2"

val kafkaVersion = "2.4.0"
val log4jVersion = "2.4.1"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  // streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,

  // streaming-kafka
  "org.apache.spark" % "spark-sql-kafka-0-10_2.11" % sparkVersion,

  // low-level integrations
  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % sparkVersion,

  "com.datastax.spark" %% "spark-cassandra-connector" % cassandraConnectorVersion,


  // logging
  "org.apache.logging.log4j" % "log4j-api" % log4jVersion,
  "org.apache.logging.log4j" % "log4j-core" % log4jVersion,

  // kafka
  "org.apache.kafka" %% "kafka" % kafkaVersion,
  "org.apache.kafka" % "kafka-streams" % kafkaVersion
)
