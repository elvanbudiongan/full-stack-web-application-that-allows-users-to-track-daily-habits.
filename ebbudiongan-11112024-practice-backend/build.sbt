name := """ebbudiongan-11112024-practice-backend"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.1"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
  "org.postgresql" % "postgresql" % "42.7.4",
  "org.playframework" %% "play-slick" % "6.1.1",
  "org.playframework" %% "play-slick-evolutions" % "6.1.1"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
