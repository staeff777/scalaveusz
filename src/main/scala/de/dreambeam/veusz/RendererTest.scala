package de.dreambeam.veusz

import de.dreambeam.veusz.VeuszOutput._
import de.dreambeam.veusz.model.GraphItems.Positioning
import de.dreambeam.veusz.model._

object RendererTest extends App {

  // XY Plots use 2 one-dimensional datasets
  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map (_ * 1.25)
  val ySin = xData.map (2 * Math.sin(_) + 5)

  // create a linear XY Point Plot with Lines
  val xyDataLinear = XYData(XYDataEntry(xData), XYDataEntry(yLinear))
  val xyLinearPlot = GraphItems.XY(xyDataLinear)
  xyLinearPlot.config.lineStyle.color = "darkblue"
  xyLinearPlot.config.markerFill.color = "blue"



  xyLinearPlot.config.markerFill.colorMap = "density-heat@200"
  xyLinearPlot.config.colorConfig.min = 0.1
  xyLinearPlot.config.colorConfig.max = 10
  xyLinearPlot.config.colorConfig.scaling = Scaling.sqrt

  // create a sinus XY Point Plot with Lines
  val xyDataSinus = XYData(XYDataEntry(xData), XYDataEntry(ySin))
  val xySinusPlot = GraphItems.XY(xyDataSinus)
  xySinusPlot.config.lineStyle.color = "darkred"
  xySinusPlot.config.markerFill.color = "red"



  val img = GraphItems.ImageFile("C:/temp/1.png", "4.5303514631043", "4.221334287531807", "0.8614344783715014", "0.645324427480916", rotate = 75, positioning = Positioning.axes)

  // bot both XY Plots into a Graph
  val graph = Graph(xyLinearPlot, xySinusPlot, img)

  graph.axis(0).label = "X Axis" //Axis can also be defined in the Graph constructor
  graph.axis(1).label = "Y Axis" //More than just two axis is possible

  val p = Page(graph)
  val document = Document(p)

  //document will be saved in a "veusz-Directory" and then opened by the operating system
  //the show command comes from VeuszOutput
  document.show("newTest")
}
