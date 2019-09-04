import de.dreambeam.veusz._

object GraphRenderDemo extends App {
  // XY Plots use 2 one-dimensional datasets
  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map(_ * 1.25)
  val ySin = xData.map(2 * Math.sin(_) + 5)


  // create a linear XY Point Plot with Lines
  val xyLinearPlot = GraphItems.XY(xData, yLinear, colorMarkers = yLinear)
  xyLinearPlot.config.plotLine.color = "darkblue"
  xyLinearPlot.config.markerFill.color = "blue"
  xyLinearPlot.config.colorConfig.min = 0
  xyLinearPlot.config.colorConfig.max = 10
  // create a sinus XY Point Plot with Lines
  val xySinusPlot = GraphItems.XY(xData, ySin)
  xySinusPlot.config.plotLine.color = "darkred"
  xySinusPlot.config.markerFill.color = "red"


  val img = GraphItems.Shapes.ImageFile("C:/temp/1.png", Vector(4.5303), Vector(4.2213), Vector(0.8614), Vector(0.64532), rotate = 75, positioning = Positioning.Axes)

  //Todo colorbar is not working
  val cb = GraphItems.Colorbar(widget = "xy", "label")
  cb.config.main.height = Some(1.0 cm)

  // put both XY Plots into a Graph
  val xAxis = GraphItems.XAxis("X", min=Some(1), max=Some(9))
  val yAxis = GraphItems.YAxis("Y")
  val graph = PageItems.Graph(Vector(xAxis, yAxis),xyLinearPlot, xySinusPlot, img, cb)

  /*
  graph.axis(0).label = "X Axis" //Axis can also be defined in the Graph constructor
  graph.axis(0).min = Some(1)
  graph.axis(0).max = Some(9)
  graph.axis(1).label = "Y Axis" //More than just two axis is possible

  graph.axis(0).min = Some(2)
  */

  graph.openInVeusz("newTest")



}
