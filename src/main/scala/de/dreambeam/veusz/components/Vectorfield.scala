package de.dreambeam.veusz.components

import de.dreambeam.veusz.data.NumericalImage
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

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
            name: String = "vectorfield"): Vectorfield = {

    new Vectorfield(NumericalImage(dxOrR), NumericalImage(dyOrTheta), mode, rotate, reflectX, reflectY, keyText, xAxis, yAxis, name)
  }

}

case class Vectorfield (dxOrR: NumericalImage,
                        dyOrTheta: NumericalImage,
                        mode: VectorfieldMode.Value,
                        rotate: Double,
                        reflectX: Boolean,
                        reflectY: Boolean,
                        keyText: String,
                        xAxis: String,
                        yAxis: String,
                        name: String )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "vectorfield"
  var config: VectorfieldConfig = VectorfieldConfig()
}

case class VectorfieldConfig(main: VectorfieldMainConfig = VectorfieldMainConfig(),
                             line: de.dreambeam.veusz.format.LineStyleConfig =  de.dreambeam.veusz.format.LineStyleConfig(),
                             fill: FillConfig = FillConfig())