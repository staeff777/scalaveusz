package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.format._


case class Function (function: String,
                     key: String = "",
                     min: Option[Double] = None,
                     max: Option[Double] = None,
                     var name: String = "function",
                     )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "function"
  var config = FunctionConfig()
}

case class FunctionConfig(var notes: String = "",
                          main: FunctionMainConfig = FunctionMainConfig(),
                          plotLine: de.dreambeam.veusz.format.LineConfig = de.dreambeam.veusz.format.LineConfig(Colors.Auto),
                          fillBelow: FillConfig = FillConfig(hide=true),
                          fillAbove: FillConfig = FillConfig(hide=true)
                         )