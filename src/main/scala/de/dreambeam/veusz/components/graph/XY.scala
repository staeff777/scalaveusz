package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object XY {

  def apply(x: Vector[Double],
            y: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            labels: Vector[String] = Vector.empty[String],
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "xy",
            config: XYConfig = XYConfig()
           ): XY = {
    XY(Numerical(x), Numerical(y), Numerical(scaleMarkers), Numerical(colorMarkers), Text(labels), keyText, xAxis, yAxis, name, config)
  }


  def apply(x: Data,
            y: Data
           ): XY = {
    XY(x, y, scaleMarkers = Numerical( Vector.empty[Double]), colorMarkers = Numerical( Vector.empty[Double]), labels = Text(Vector.empty), keyText="", xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }

  def apply(x: Data,
            y: Data,
            labels: Text,
           ): XY = {
    XY(x, y, scaleMarkers = Numerical( Vector.empty[Double]), colorMarkers = Numerical( Vector.empty[Double]), labels,  keyText="", xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
           ): XY = {
    XY(x, y, scaleMarkers, colorMarkers = Numerical(Vector.empty), labels = Text(Vector.empty), keyText="", xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            labels: Text,
           ): XY = {
    XY(x, y, scaleMarkers, colorMarkers = Numerical(Vector.empty), labels, keyText="", xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }


  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical
           ): XY = {
    XY(x, y, scaleMarkers, colorMarkers, labels = Text(Vector.empty), keyText="", xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }


  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            labels: Text,
            keyText: String
           ): XY = {
    XY(x, y, scaleMarkers, colorMarkers, labels, keyText, xAxis = "x", yAxis="y", name = "xy", XYConfig())
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            keyText: String,
            xAxis: String,
            yAxis: String
           ): XY = {

    XY(x, y, scaleMarkers, colorMarkers,labels = Text(Vector.empty), keyText, xAxis, yAxis, name = "xy", XYConfig())
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            labels: Text,
            keyText: String,
            xAxis: String,
            yAxis: String
           ): XY = {

    XY(x, y, scaleMarkers, colorMarkers,labels, keyText, xAxis, yAxis, name = "xy", XYConfig())
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

    XY(x, y, scaleMarkers, colorMarkers, labels = Text(Vector.empty), keyText, xAxis, yAxis, name, XYConfig())
  }

  def apply(x: Data,
            y: Data,
            scaleMarkers: Numerical,
            colorMarkers: Numerical,
            labels: Text,
            keyText: String,
            xAxis: String,
            yAxis: String,
            name: String
           ): XY = {

    XY(x, y, scaleMarkers, colorMarkers, labels, keyText, xAxis, yAxis, name, XYConfig())
  }
}

case class XY(var x: Data,
              var y: Data,
              var scaleMarkers: Numerical,
              var colorMarkers: Numerical,
              var labels: Text,
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