name := "akka-sample"

version := "0.1"

val akkaVersion = "2.6.15"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
)