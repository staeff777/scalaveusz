package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.data._
import de.dreambeam.veusz.util.MemoryTools.uniqueReference
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.format._

object XY {

  def apply(x: Vector[Double],
            y: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            name: String = "xy",
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y"
           ) = {
    val xName = uniqueReference(Numerical(x), "x")
    val yName = uniqueReference(Numerical(y), "y")
    val scaleName = uniqueReference(Numerical(scaleMarkers), "s")
    val colorName = uniqueReference(Numerical(colorMarkers), "c")
    new XY(xName, yName, scaleName, colorName, name, keyText, xAxis, yAxis)
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            name: String,
            keyText: String,
            xAxis: String,
            yAxis: String
           ) = {
    val xName = uniqueReference(x, "x")
    val yName = uniqueReference(y, "y")
    val scaleName = uniqueReference(scaleMarkers, "s")
    val colorName = uniqueReference(scaleMarkers, "c")
    new XY(xName, yName, scaleName, colorName, name, keyText, xAxis, yAxis)
  }
}

class XY private (val xName: String,
                  val yName: String,
                  val scaleName: String,
                  val colorName: String,
                  val name: String,
                  val keyText: String,
                  val xAxis: String,
                  val yAxis: String,
                  val children: Option[Children] = None
                 )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "xy"

  object config {
    val main = XYMainConfig()
    val plotLine = XYPlotLineConfig()
    val markerBorder = XYMarkerBorderConfig()
    val markerFill = XYMarkerFillConfig()
    val errorBarLine = XYErrorBarLineConfig()
    val fillBelow = XYFillConfig(fillTo = FillTo.bottom)
    val fillAbove = XYFillConfig(fillTo = FillTo.top)
    val label = XYLabelConfig()
  }

}
