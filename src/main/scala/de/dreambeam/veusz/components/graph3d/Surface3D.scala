package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem}
import de.dreambeam.veusz.data.{Numerical, NumericalImage}
import de.dreambeam.veusz.format.{ColorConfig, ColorMaps, Colors, LineStyle, Surface3DMode}

object Surface3D {

  def apply(data: Map[(Double, Double), Double],
            colorMarkers: Map[(Double, Double), Double]= Map.empty[(Double,Double), Double],
            mode: Surface3DMode.Value = Surface3DMode.Z_XY,
            xAxis: String = "x",
            yAxis: String = "y",
            zAxis: String = "z",
            name: String = "surface3d",
            config: Surface3DConfig = Surface3DConfig()): Surface3D = new Surface3D(NumericalImage(data), NumericalImage(colorMarkers), mode, xAxis, yAxis, zAxis, name, config)

  def apply(dataset: NumericalImage, colorMarkers: NumericalImage, mode: Surface3DMode.Value): Surface3D =
    new Surface3D(dataset, colorMarkers, mode, "x", "y", "z", "surface3d", Surface3DConfig())
}

case class Surface3D(dataset: NumericalImage,
                     colorMarkers: NumericalImage,
                     mode: Surface3DMode.Value,
                     xAxis: String,
                     yAxis: String,
                     zAxis: String,
                     name: String,
                     config: Surface3DConfig)
    extends Graph3DItem with Configurable with Executable {
  override def group: String = "surface3d"
}

case class Surface3DConfig(main: Surface3DMainConfig = Surface3DMainConfig(),
                           gridLine: Surface3DGridLineConfig = Surface3DGridLineConfig(),
                           surface: Surface3DSurfaceConfig = Surface3DSurfaceConfig(),
                           colorConfig: ColorConfig = ColorConfig())

case class Surface3DMainConfig(highres: Boolean = false, hide: Boolean = false)

case class Surface3DGridLineConfig(var color: String = Colors.Auto,
                                   var width: Double = 1.0,
                                   var style: String = LineStyle.Solid,
                                   var transparency: Int = 0,
                                   var reflectivity: Int = 0,
                                   var hideHorz: Boolean = false,
                                   var hideVert: Boolean = false,
                                   var hide: Boolean = false)

case class Surface3DSurfaceConfig(var color: String = Colors.Grey,
                                  var width: Double = 1.0,
                                  var transparency: Int = 0,
                                  var reflectivity: Int = 50,
                                  var colorMap: String = ColorMaps.Grey,
                                  var invertMap: Boolean = false,
                                  var hide: Boolean = false)
