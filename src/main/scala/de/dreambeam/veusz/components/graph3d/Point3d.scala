package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.data._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem, GraphItem}

object Point3d {

  def apply(x: Vector[Double],
            y: Vector[Double],
            z: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            zAxis: String = "z",
            name: String = "point3d"): Point3d = {
    new Point3d(Numerical(x), Numerical(y), Numerical(z), Numerical(scaleMarkers), Numerical(colorMarkers), keyText, xAxis, yAxis, zAxis, name, Point3dConfig())
  }

  def apply(x: Data, y: Data, z: Data, scaleMarkers: Numerical, colorMarkers: Numerical, keyText: String, xAxis: String, yAxis: String, zAxis: String, name: String): Point3d = {

    new Point3d(x, y, z, scaleMarkers, colorMarkers, keyText, xAxis, yAxis, zAxis, name, Point3dConfig())
  }
}

case class Point3d(var x: Data,
                   var y: Data,
                   var z: Data,
                   var scaleMarkers: Numerical,
                   var colorMarkers: Numerical,
                   var keyText: String,
                   var xAxis: String,
                   var yAxis: String,
                   var zAxis: String,
                   var name: String,
                   var config: Point3dConfig)
    extends Graph3DItem with Configurable with Executable {
  val group = "point3d"

}

case class Point3dConfig(main: Point3dMainConfig = Point3dMainConfig(),
                         plotLine: PlotLineConfig = PlotLineConfig(),
                         markerBorder: MarkerBorderConfig = MarkerBorderConfig(),
                         markerFill: MarkerFillConfig = MarkerFillConfig(),
                         errorBarLine: XYErrorBarLineConfig = XYErrorBarLineConfig(),
                         fillBelow: XYFillConfig = XYFillConfig(fillTo = FillTo.bottom),
                         fillAbove: XYFillConfig = XYFillConfig(fillTo = FillTo.top),
                         label: XYLabelConfig = XYLabelConfig(),
                         colorConfig: ColorConfig = ColorConfig())

case class Point3dMainConfig(autoRange: String = AutoRange.NextTick, hide: Boolean = false)