import Dependencies._

/* project settings */
ThisBuild / scalaVersion := "3.4.2"
ThisBuild / name := "kuram"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.kattulib"
ThisBuild / organizationName := "kattulib"
ThisBuild / description := "Minimal Scala library for functional programming"
ThisBuild / licenses := List(("MIT", url("https://opensource.org/license/mit")))
ThisBuild / startYear := Some(2024)
ThisBuild / homepage := Some(url("https://github.com/kattulib/kuram"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/kattulib/kuram"),
    "git@github.com:kattulib/kuram.git"
  )
)
ThisBuild / developers ++= List(
  Developer(
    id = "csgn",
    name = "Sergen Çepoğlu",
    email = "dev.csgn@gmail.com",
    url = url("https://github.com/csgn")
  )
)

/* test settings */
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

/* scalafix settings */
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

/* scaladoc settings */
ThisBuild / autoAPIMappings := true
Compile / doc / scalacOptions ++= Seq(
  "-doc-title",
  (ThisBuild / name).value,
  "-project-version",
  (ThisBuild / version).value,
  "-project-logo",
  "docs/icon.jpeg"
)

/* publish settings */
ThisBuild / publishMavenStyle := true

val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-Wunused:all",
    "-rewrite",
    "-no-indent"
  )
)

lazy val example = project
  .in(file("example"))
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "example",
    publish / skip := true
  )
  .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val tests = project
  .in(file("tests"))
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "tests",
    publish / skip := true,
    libraryDependencies ++= {
      Seq(
        munit.value % Test
      )
    }
  )
  .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val laws = project
  .in(file("laws"))
  .dependsOn(core)
  .settings(commonSettings)
  .settings(
    name := "laws",
    publish / skip := true,
  )
  .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val core = project
  .in(file("core"))
  .settings(commonSettings)
  .settings(
    name := "core",
    moduleName := "kuram-core"
  )
  .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val kuram = project
  .in(file("."))
  .aggregate(core, laws, tests, example)
