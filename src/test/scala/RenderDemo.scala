import de.dreambeam.veusz.components.{Document, Graph, Image, ImageFile, XY}
import de.dreambeam.veusz.format.Positioning

object RenderDemo extends App {
  // XY Plots use 2 one-dimensional datasets
  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map(_ * 1.25)
  val ySin = xData.map(2 * Math.sin(_) + 5)

  // create a linear XY Point Plot with Lines
  val xyLinearPlot = XY(xData, yLinear)
  xyLinearPlot.config.plotLine.color = "darkblue"
  xyLinearPlot.config.markerFill.color = "blue"

  // create a sinus XY Point Plot with Lines
  val xySinusPlot = XY(xData, ySin)
  xySinusPlot.config.plotLine.color = "darkred"
  xySinusPlot.config.markerFill.color = "red"


  val img = ImageFile("C:/temp/1.png", Vector(4.5303), Vector(4.2213), Vector(0.8614), Vector(0.64532), rotate = 75, positioning = Positioning.Axes)

  // put both XY Plots into a Graph
  val graph = Graph(xyLinearPlot, xySinusPlot, img)

  graph.axis(0).label = "X Axis" //Axis can also be defined in the Graph constructor
  graph.axis(1).label = "Y Axis" //More than just two axis is possible

  //graph.show("newTest")


  val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield
    (x.toDouble, y.toDouble) -> (x + y).toDouble
    ).toMap


  val img2d = Image.apply(dataset)
  img2d.show("im2d")

}
