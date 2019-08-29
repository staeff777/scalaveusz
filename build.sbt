inThisBuild(List(
  organization := "de.dreambeam",
  homepage := Some(url("https://github.com/staeff777/scalaveusz")),
  licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "staeff777",
      "Stefan Kaufmann",
      "stefan@dreambeam.de",
      url("http://dreambeam.de")
    )
  )
))

name := "scalaveusz"
organization := "de.dreambeam"

version := "0.3.5-SNAPSHOT"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"