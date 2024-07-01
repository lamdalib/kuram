import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object Dependencies {
  lazy val munit = Def.setting("org.scalameta" %%% "munit" % "1.0.0-M10")
}
