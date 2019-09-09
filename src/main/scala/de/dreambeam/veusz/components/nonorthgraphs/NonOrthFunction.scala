package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.format.{FunctionMainConfig, LineStyleConfig, NonOrthFillConfig}
import de.dreambeam.veusz.{Configurable, Executable, NonOrthGraphItem}

case class NonOrthFunction(function: String,
                           variable: String = "a",
                           min: Option[Double] = None,
                           max: Option[Double] = None,
                           var name: String = "nonorthfunc",
                           var config: NonOrthFunctionConfig = NonOrthFunctionConfig())
    extends NonOrthGraphItem with Configurable with Executable {
  override def group: String = "nonorthfunc"
}



case class NonOrthFunctionConfig(
          var main: FunctionMainConfig = FunctionMainConfig(),
          var plotLine: LineStyleConfig = LineStyleConfig(),
          var areaFill1: NonOrthFillConfig = NonOrthFillConfig(),
          var areaFill2: NonOrthFillConfig = NonOrthFillConfig()
)
