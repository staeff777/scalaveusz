package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem}
import de.dreambeam.veusz.data.{Data, Numerical}
import de.dreambeam.veusz.format.{ColorConfig, ColorMaps, Colors, LineStyle}

object Volume3D {

  def apply(x: Vector[Double],
            y: Vector[Double],
            z: Vector[Double],
            colorMarkers: Vector[Double] = Vector.empty[Double],
            transparency: Vector[Double] = Vector.empty[Double],
            xAxis: String = "x",
            yAxis: String = "y",
            zAxis: String = "z",
            name: String = "volume3d",
            config: Volume3DConfig = Volume3DConfig()): Volume3D =
    new Volume3D(Numerical(x), Numerical(y), Numerical(z), Numerical(colorMarkers), Numerical(transparency), xAxis, yAxis, zAxis, name, config)
}

case class Volume3D(var x: Data,
                    var y: Data,
                    var z: Data,
                    var colorMarkers: Numerical,
                    var transparency: Numerical,
                    var xAxis: String,
                    var yAxis: String,
                    var zAxis: String,
                    var name: String,
                    var config: Volume3DConfig) extends Graph3DItem with Configurable with Executable {
  override def group: String = "volume3d"
}

case class Volume3DConfig(main: Volume3DMainConfig = Volume3DMainConfig(), line: Volume3DBoxLineConfig = Volume3DBoxLineConfig(), colorConfig: ColorConfig = ColorConfig())

case class Volume3DMainConfig(var colorMap: String = ColorMaps.Grey,
                              var invertMap: Boolean = false,
                              var transparency: Int = 50,
                              var reflectivity: Int = 0,
                              var fillFactor: Double = 1,
                              var hide: Boolean = false
)

case class Volume3DBoxLineConfig(var color: String = Colors.Foreground,
                                 var width: Double = 1.0,
                                 var style: String = LineStyle.Solid,
                                 var transparency: Int = 0,
                                 var reflectivity: Int = 0,
                                 var hide: Boolean = false,
)
