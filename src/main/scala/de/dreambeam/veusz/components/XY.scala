package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.data._
import de.dreambeam.veusz.util.MemoryTools.uniqueReference
import de.dreambeam.veusz.format._

object XY {

  def apply(x: Vector[Double],
            y: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "xy",
           ): XY = {
    val xName = uniqueReference(Numerical(x), "x")
    val yName = uniqueReference(Numerical(y), "y")
    val scaleName = uniqueReference(Numerical(scaleMarkers), "s")
    val colorName = uniqueReference(Numerical(colorMarkers), "c")
    XY(xName, yName, scaleName, colorName, keyText, xAxis, yAxis, name)
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            keyText: String,
            xAxis: String,
            yAxis: String,
            name: String
           ): XY = {
    val xName = uniqueReference(x, "x")
    val yName = uniqueReference(y, "y")
    val scaleName = uniqueReference(scaleMarkers, "s")
    val colorName = uniqueReference(scaleMarkers, "c")
    XY(xName, yName, scaleName, colorName, keyText, xAxis, yAxis, name)
  }
}

case class XY (xName: String,
               yName: String,
               scaleName: String,
               colorName: String,
               keyText: String,
               xAxis: String,
               yAxis: String,
               var name: String)
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "xy"
  var config = XYConfig()
}

case class XYConfig(main: XYMainConfig = XYMainConfig(),
                    plotLine: XYPlotLineConfig = XYPlotLineConfig(),
                    markerBorder: XYMarkerBorderConfig = XYMarkerBorderConfig(),
                    markerFill: XYMarkerFillConfig = XYMarkerFillConfig(),
                    errorBarLine: XYErrorBarLineConfig = XYErrorBarLineConfig(),
                    fillBelow: XYFillConfig = XYFillConfig(fillTo = FillTo.bottom),
                    fillAbove: XYFillConfig = XYFillConfig(fillTo = FillTo.top),
                    label: XYLabelConfig = XYLabelConfig())