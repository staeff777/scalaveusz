package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object XY {

  def apply(x: Vector[Double],
            y: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "xy",
            config: XYConfig = XYConfig()
           ): XY = {
    XY(Numerical(x), Numerical(y), Numerical(scaleMarkers), Numerical(colorMarkers), keyText, xAxis, yAxis, name, config)
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

    XY(x, y, scaleMarkers, colorMarkers, keyText, xAxis, yAxis, name, XYConfig())
  }
}

case class XY(var x: Data,
              var y: Data,
              var scaleMarkers: Numerical,
              var colorMarkers: Numerical,
              var keyText: String,
              var xAxis: String,
              var yAxis: String,
              var name: String,
              var config: XYConfig)
  extends GraphItem
    with Configurable
    with Executable {
  val group = "xy"

}

case class XYConfig(main: XYMainConfig = XYMainConfig(),
                    plotLine: PlotLineConfig = PlotLineConfig(),
                    markerBorder: MarkerBorderConfig = MarkerBorderConfig(),
                    markerFill: MarkerFillConfig = MarkerFillConfig(),
                    errorBarLine: XYErrorBarLineConfig = XYErrorBarLineConfig(),
                    fillBelow: XYFillConfig = XYFillConfig(fillTo = FillTo.bottom),
                    fillAbove: XYFillConfig = XYFillConfig(fillTo = FillTo.top),
                    label: XYLabelConfig = XYLabelConfig(),
                    colorConfig: ColorConfig = ColorConfig())