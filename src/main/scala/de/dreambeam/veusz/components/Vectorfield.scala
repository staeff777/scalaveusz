package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.format._


case class Vectorfield (dxOrR: String = "",
                        dyOrTheta: String = "",
                        mode: VectorfieldMode.Value = VectorfieldMode.Cartesian,
                        rotate: Double = 0.0,
                        reflectX: Boolean = false,
                        reflectY: Boolean = false,
                        keyText: String = "",
                        xAxis: String = "x",
                        yAxis: String = "y",
                        name: String = "vectorfield")
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "vectorfield"
  var config: VectorfieldConfig = VectorfieldConfig()
}

case class VectorfieldConfig(main: VectorfieldMainConfig = VectorfieldMainConfig(),
                             line: de.dreambeam.veusz.format.LineConfig =  de.dreambeam.veusz.format.LineConfig(),
                             fill: FillConfig = FillConfig())