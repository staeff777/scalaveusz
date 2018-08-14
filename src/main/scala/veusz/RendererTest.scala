package veusz

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
