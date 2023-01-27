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

version := "0.8.0"


scalaVersion := "3.1.2" //scala 3.2.1 has currently a doc bug https://github.com/lampepfl/dotty/issues/15927

libraryDependencies ++= Seq(
          "org.scalactic" %% "scalactic" % "3.2.15",
          "org.scalatest" %% "scalatest" % "3.2.15" % "test"
)

//resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

crossScalaVersions := Seq("2.12.17", "2.13.0", "3.1.2")
