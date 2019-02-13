name := "ScalaVeusz4"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "com.nrinaudo" %% "kantan.csv" % "0.4.0",
  "com.nrinaudo" %% "kantan.csv-generic" % "0.4.0",
  "com.nrinaudo" %% "kantan.csv-java8" % "0.4.0",
  "com.github.haifengl" %% "smile-scala" % "1.5.2",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"