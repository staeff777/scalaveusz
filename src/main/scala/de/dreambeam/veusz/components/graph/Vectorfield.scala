package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.NumericalImage
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object Vectorfield {
  def apply(dxOrR: Map[(Double, Double), Double],
            dyOrTheta: Map[(Double, Double), Double],
            mode: VectorfieldMode.Value = VectorfieldMode.Cartesian,
            rotate: Double = 0.0,
            reflectX: Boolean = false,
            reflectY: Boolean = false,
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "vectorfield",
   config: VectorfieldConfig = VectorfieldConfig()): Vectorfield = {

    new Vectorfield(NumericalImage(dxOrR), NumericalImage(dyOrTheta), mode, rotate, reflectX, reflectY, keyText, xAxis, yAxis, name, config)
  }

}

case class Vectorfield (var dxOrR: NumericalImage,
                        var dyOrTheta: NumericalImage,
                        var mode: VectorfieldMode.Value,
                        var rotate: Double,
                        var reflectX: Boolean,
                        var reflectY: Boolean,
                        var keyText: String,
                        var xAxis: String,
                        var yAxis: String,
                        var name: String,
                        var config: VectorfieldConfig)
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "vectorfield"

}

case class VectorfieldConfig(main: VectorfieldMainConfig = VectorfieldMainConfig(),
                             line: de.dreambeam.veusz.format.LineStyleConfig =  de.dreambeam.veusz.format.LineStyleConfig(),
                             fill: FillConfig = FillConfig())