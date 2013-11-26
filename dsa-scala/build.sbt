name := "dsa-scala"

organization := "io.github.pkmishra"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

showSuccess  := true

showTiming   := true

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9"

libraryDependencies += "org.clapper" %% "grizzled-slf4j" % "1.0.1"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.2"

initialCommands := "import io.github.pkmishra.dsascala._"
