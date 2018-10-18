package de.dreambeam.veusz

import veusz.model._
import VeuszOutput._


object RendererTest extends App{


  val xyData = XYData(XYDataEntry(1.0 to 10.0 by 1 toVector), XYDataEntry(11.0 to 55.0 by 1 toVector, name="h"))
  val graph = Graph(GraphItems.XY(xyData ))

  val p = Page(graph)
  val document = Document(p)

  document.show("newTest")
}
