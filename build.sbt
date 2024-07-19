import Dependencies._

ThisBuild / scalaVersion := "3.4.2"

ThisBuild / name := "Kuram"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.kattulib"
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
        id      = "csgn", 
        name    = "Sergen Çepoğlu", 
        email   = "dev.csgn@gmail.com", 
        url     = url("https://github.com/csgn")
    ),
)


ThisBuild / testFrameworks += new TestFramework("munit.Framework")

ThisBuild / autoAPIMappings := true
Compile / doc / scalacOptions ++= Seq(
    "-doc-title", (ThisBuild / name).value,
    "-project-version", (ThisBuild / version).value,
    "-project-logo", "docs/icon.jpeg",
)

lazy val example = project
    .in(file("example"))
    .dependsOn(root)
    .settings(
        name := "example"
    )

lazy val root = project
    .in(file("."))
    .settings(
        libraryDependencies ++= {
            Seq(
                munit.value % Test,
            )
        },
    )
