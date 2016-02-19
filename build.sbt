name := """distributed-actor-system"""

version := "2.4.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "com.typesafe.akka" %% "akka-remote" % "2.4.0",
  "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  "io.spray" %%  "spray-json" % "1.3.2"
)


fork in run := true