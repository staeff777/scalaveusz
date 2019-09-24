inThisBuild(
          List(
            organization := "de.dreambeam",
            homepage := Some(url("https://github.com/staeff777/scalaveusz")),
            licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
            developers := List(
              Developer(
                "staeff777",
                "Stefan Kaufmann",
                "stefan@dreambeam.de",
                url("http://dreambeam.de")
              ),
              Developer(
                "aleph01",
                "Julian Eger-Benninger",
                "",
                url("http://github.com")
              )
            )
          ))

name := "scalaveusz"
organization := "de.dreambeam"

version := "0.4.6"

scalaVersion := "2.12.9"

libraryDependencies ++= Seq(
          "org.scalactic" %% "scalactic" % "3.0.8",
          "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

crossScalaVersions := Seq("2.12.9", "2.13.0")
