import de.dreambeam.veusz.components.graph.{Graph, XY}
import de.dreambeam.veusz.components.nonorthgraphs.{NonOrthPoint, PolarGraph}
import de.dreambeam.veusz.components.{Document, Page}

object DocumentRenderTest extends App{

  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map(_ * 1.25)
  val ySin = xData.map( Math.sin(_) + 5)

  val xyLinearPlot = XY(xData, yLinear, colorMarkers = yLinear)
  xyLinearPlot.config.plotLine.color = "darkblue"
  xyLinearPlot.config.markerFill.color = "blue"

  val page1 = Page(Graph(xyLinearPlot))


  val nonOrt = NonOrthPoint(xData, ySin.map(_.toDegrees))

  val page2 = Page(PolarGraph(nonOrt))

  val doc =  Document(page1, page2)
    doc.exportAndOpen("c:\\temp\\document.pdf")

}
