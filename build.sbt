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

version := "0.0.10"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.nrinaudo" %% "kantan.csv" % "0.4.0",
  "com.nrinaudo" %% "kantan.csv-generic" % "0.4.0",
  "com.nrinaudo" %% "kantan.csv-java8" % "0.4.0",
  "com.github.haifengl" %% "smile-scala" % "1.5.2",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"