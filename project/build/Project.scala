

import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {

  val liftVersion = "2.2-RC4"
  val lift = "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default"
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default"
}
