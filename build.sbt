import Dependencies._

/* Project settings */
ThisBuild / scalaVersion := "2.13.15"
ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / description := "Minimal functional programming library for Scala"
ThisBuild / licenses := List(("MIT", url("https://opensource.org/license/mit")))
ThisBuild / developers ++= List(
  Developer(
    id = "csgn",
    name = "Sergen Çepoğlu",
    email = "dev.csgn@gmail.com",
    url = url("https://github.com/csgn")
  )
)

/* Test settings */
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

/* Scalafix settings */
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

/* Publish settings */
ThisBuild / publishMavenStyle := true

lazy val tests = project
  .in(file("tests"))
  .dependsOn(core)
  .settings(
    name := "tests",
    publish / skip := true,
    libraryDependencies ++= {
      Seq(
        munit % Test,
      )
    }
  )
  .enablePlugins(ScalafixPlugin)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "core",
    moduleName := "lamda-core"
  )
  .enablePlugins(ScalafixPlugin)

lazy val lamda = project
  .in(file("."))
  .settings(
    name := "lamda",
  )
  .aggregate(core, tests)
