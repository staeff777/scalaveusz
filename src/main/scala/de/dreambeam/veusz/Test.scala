package de.dreambeam.veusz

import de.dreambeam.veusz.components._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.data.Numerical

/*
object Session extends App {
  val xData = (1.0 to 10.0 by 0.5).toVector
  val yLinear = xData.map(_ * 1.25)
  val ySin = xData.map(2 * Math.sin(_) + 5)

  val xyLinear = XY(xData, yLinear, scaleMarkers = yLinear, colorMarkers = xData, keyText = "x")
  xyLinear.config.plotLine.color = "darkblue"
  xyLinear.config.markerFill.color = "blue"

  val xySinus = XY(xData, ySin, scaleMarkers = ySin, keyText = "y")
  xySinus.config.plotLine.color = "darkred"
  xySinus.config.markerFill.color = "red"

  val graph = Graph(xyLinear, xySinus, Key())
  graph.axis(0).label = "X Axis"
  graph.axis(1).label = "Y Axis"

  graph.show("Graph")
}
*/

object Session extends App {
  val covariance = Covariance()
  covariance.show("Covariance")
}