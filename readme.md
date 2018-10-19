# VeuszLib

[![Build Status](https://travis-ci.org/staeff777/scalaveusz.svg?branch=master)](https://travis-ci.org/staeff777/scalaveusz)[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.dreambeam/scalaveusz/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.dreambeam/svalaveusz) (Continuous Integration currently WIP)


Create veusz charts in scala.

Api is work in progress.

```scala
import veusz.model._
import veusz.model.VeuszOutput._


object RendererTest extends App{


  implicit val documentConfig = DocumentConfig()
  implicit val pageConfig = model.PageConfig()
  implicit val graphConfig = GraphConfig()
  implicit val xYConfig = GraphItems.XYConfig()


  val xyData = XYData(XYDataEntry(1.0 to 10.0 by 1 toVector), XYDataEntry(11.0 to 55.0 by 1 toVector, name="h"))

  val graph = Graph(GraphItems.XY(xyData ))

  val p = Page(graph)
  val document = Document(p)


  document.show("newTest")
}

```