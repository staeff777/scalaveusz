package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, NonOrthGraphItem}


case class Function (function: String,
                     key: String = "",
                     min: Option[Double] = None,
                     max: Option[Double] = None,
                     xAxis: String = "x",
                     yAxis: String = "y",
                     var name: String = "function"
                     )
  extends GraphItem
    with NonOrthGraphItem
    with Configurable
    with Executable
{
  val group = "function"
  var config = FunctionConfig()
}

case class FunctionConfig(var notes: String = "",
                          main: FunctionMainConfig = FunctionMainConfig(),
                          plotLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(Colors.Auto),
                          fillBelow: FillConfig = FillConfig(hide=true),
                          fillAbove: FillConfig = FillConfig(hide=true)
                         )