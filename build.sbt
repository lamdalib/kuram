import Dependencies.*

ThisBuild / scalaVersion := "3.4.2"
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / name := "Kuram"

ThisBuild / organization := "io.github.kattulib"
ThisBuild / description := "Implementation of Category Theory"
ThisBuild / licenses := List(("MIT", url("https://opensource.org/license/mit")))
ThisBuild / startYear := Some(2024)
ThisBuild / homepage := Some(url("https://github.com/kattulib/kuram"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/kattulib/kuram"), "git@github.com:kattulib/kuram.git"))
ThisBuild / developers ++= List(
    Developer(
        id = "csgn", 
        name = "Sergen Çepoğlu", 
        email = "dev.csgn@gmail.com", 
        url = url("https://github.com/csgn")
    ),
)

ThisBuild / autoAPIMappings := true
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

Compile / doc / scalacOptions ++= Seq(
    "-doc-title", (ThisBuild / name).value,
    "-project-version", (ThisBuild / version).value,
    "-project-logo", "docs/icon.jpeg",
)

val scala3Version = "3.4.2"

lazy val root = project
    .in(file("."))
    .settings(
        // Dependencies
        libraryDependencies ++= {
            Seq(
                munit.value % Test,
            )
        },
    )
