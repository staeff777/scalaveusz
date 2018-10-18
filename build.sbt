
inThisBuild(List(
  organization := "de.datenberg",
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
organization := "de.datenberg"

version := "1.0"

scalaVersion := "2.12.6"


libraryDependencies ++= Seq( //"com.softwaremill.quicklens" %% "quicklens" % "1.4.11",
                            // "com.lihaoyi" %% "sourcecode" % "0.1.3" //https://github.com/lihaoyi/sourcecode
)
