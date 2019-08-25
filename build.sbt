name := "regenbogen"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "com.bot4s" %% "telegram-core" % "4.4.0-RC1"
libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.9"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23"
libraryDependencies += "com.softwaremill.sttp" %% "json4s" % "1.6.4"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.0"

mainClass in compile := Some("com.renarde.regenbogen.MainApp")