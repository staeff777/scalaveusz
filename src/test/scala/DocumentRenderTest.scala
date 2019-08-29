import de.dreambeam.veusz.components.{Document, Graph, NonOrthPoint, Page, PolarGraph, XY}

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
  doc.setGlobalVueszPath("C:\\Program Files (x86)\\Veusz\\veusz.exe")
  doc.export("c:\\temp\\document.pdf")

}
