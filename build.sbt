name := "regenbogen"

version := "0.1"

scalaVersion := "2.11.12"

val akkaVersion = "2.5.23"
val akkaHttpVersion = "10.1.9"

libraryDependencies += "com.bot4s" %% "telegram-core" % "4.4.0-RC1"
libraryDependencies += "com.bot4s" %% "telegram-akka" % "4.4.0-RC1"
libraryDependencies += "com.softwaremill.sttp" %% "json4s" % "1.6.4"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.0"

libraryDependencies += "com.typesafe.akka" %% "akka-http"   % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-actor"  % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

mainClass in Compile := Some("com.renarde.regenbogen.MainApp")
herokuFatJar in Compile := Some((assemblyOutputPath in assembly).value)