import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "twiscala"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.mindrot" % "jbcrypt" % "0.3m",
    "com.typesafe.slick" %% "slick" % "1.0.1",
    "com.h2database" % "h2" % "1.3.166" ,
    "com.yasuoza.plugin" % "play2-redis" % "0.1.0"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
