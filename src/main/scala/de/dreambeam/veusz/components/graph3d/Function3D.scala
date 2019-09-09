package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.{Configurable, Executable, Graph3DItem}
import de.dreambeam.veusz.format.{Colors, Function3DMode}

case class Function3D(x_func: String = "",
                      y_func: String = "",
                      z_func: String = "",
                      mode: Function3DMode.Value = Function3DMode.XYZ_FNS_T,
                      color_func: String = "",
                      xAxis: String = "x",
                      yAxis: String = "y",
                      zAxis: String = "z",
                      name: String = "function3d",
                      config: Function3DConfig = Function3DConfig())
    extends Graph3DItem with Configurable with Executable {
  override def group: String = "function3d"
}

case class Function3DConfig(var main: Function3DMainConfig = Function3DMainConfig(),
                            var plotLine: PlotLine3DConfig = PlotLine3DConfig(),
                            var gridLine: Surface3DGridLineConfig = Surface3DGridLineConfig(),
                            var surface: Surface3DSurfaceConfig = Surface3DSurfaceConfig())

case class Function3DMainConfig(var color: String = Colors.Auto, var lineSteps: Int = 50, var surfaceSteps: Int = 20,  var hide: Boolean = false)
