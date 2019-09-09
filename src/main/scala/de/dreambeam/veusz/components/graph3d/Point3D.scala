package de.dreambeam.veusz.components.graph3d
import de.dreambeam.veusz.format.SizeUnits._
import de.dreambeam.veusz.data._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem, GraphItem}

object Point3D {

  def apply(x: Vector[Double],
            y: Vector[Double],
            z: Vector[Double],
            scaleMarkers: Vector[Double] = Vector.empty[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            zAxis: String = "z",
            name: String = "point3d",
            config: Point3DConfig = Point3DConfig()): Point3D =
    new Point3D(Numerical(x), Numerical(y), Numerical(z), Numerical(scaleMarkers), Numerical(colorMarkers), keyText, xAxis, yAxis, zAxis, name, Point3DConfig())

  def apply(x: Data, y: Data, z: Data, scaleMarkers: Numerical, colorMarkers: Numerical, keyText: String, xAxis: String, yAxis: String, zAxis: String, name: String): Point3D =
    new Point3D(x, y, z, scaleMarkers, colorMarkers, keyText, xAxis, yAxis, zAxis, name, Point3DConfig())

}

case class Point3D(var x: Data,
                   var y: Data,
                   var z: Data,
                   var scaleMarkers: Numerical,
                   var colorMarkers: Numerical,
                   var keyText: String,
                   var xAxis: String,
                   var yAxis: String,
                   var zAxis: String,
                   var name: String,
                   var config: Point3DConfig)
    extends Graph3DItem with Configurable with Executable {
  val group = "point3d"

}

case class Point3DConfig(main: Point3DMainConfig = Point3DMainConfig(),
                         lineStyle: LineStyle3DConfig = LineStyle3DConfig(),
                         markerFill: MarkerFill3DConfig = MarkerFill3DConfig(),
                         markerBorder: MarkerBorder3DConfig = MarkerBorder3DConfig(),
                         errorBar: ErrorBar3DConfig = ErrorBar3DConfig(),
                         colorConfig: ColorConfig = ColorConfig())

case class Point3DMainConfig(autoRange: String = AutoRange.NextTick, hide: Boolean = false)

case class LineStyle3DConfig(
          var color: String = Colors.Auto,
          var width: Double = 1.0,
          var style: String = LineStyle.Solid,
          var transparency: Int = 0,
          var reflectivity: Int = 0,
          var colorMap: String = "",
          var invertMap: Boolean = false,
          var hide: Boolean = false,
)

case class MarkerFill3DConfig(
          var color: String = Colors.Auto,
          var style: String = FillStyle.Solid,
          var transparency: Int = 0,
          var hide: Boolean = false,
          var colorMap: String = ColorMaps.Grey,
          var invertMap: Boolean = false
)

case class MarkerBorder3DConfig(
          var color: String = Colors.Foreground,
          var width: Double = 1.0,
          var style: String = LineStyle.Solid,
          var transparency: Int = 0,
          var scale: Boolean = true,
          var reflectivity: Int = 0,
          var hide: Boolean = false
)

case class ErrorBar3DConfig(
          var color: String = Colors.Foreground,
          var width: Double = 1.0,
          var style: String = LineStyle.Solid,
          var transparency: Int = 0,
          var reflectivity: Int = 0,
          var hide: Boolean = false
)
