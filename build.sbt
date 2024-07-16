import Dependencies._

ThisBuild / scalaVersion := "3.4.2"
ThisBuild / version := "1.0"
ThisBuild / name := "Kuram"
ThisBuild / organization := "org.kattu"
ThisBuild / description := "Implementation of Category Theory"
ThisBuild / licenses := List(("MIT", url("https://opensource.org/license/mit")))
ThisBuild / startYear := Some(2024)
ThisBuild / developers ++= List(
    Developer("csgn", "Sergen Çepoğlu", "dev.csgn@gmail.com", url("https://github.com/csgn")),
)
ThisBuild / testFrameworks += new TestFramework("munit.Framework")
ThisBuild / autoAPIMappings := true

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
