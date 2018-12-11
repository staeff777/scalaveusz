package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.format._

case class Barchart (lengths: Vector[String] = Vector("y"),
                     direction: Direction.Value = Direction.Vertical,
                     mode: BarchartMode.Value = BarchartMode.Stacked,
                     keys: Vector[String] = Vector(""),
                     xAxis: String = "x",
                     yAxis: String = "y",
                     name: String = "bar")
    extends GraphItem
      with Configurable
      with Executable
{
  val group = "bar"
  var config = BarchartConfig()
}

case class BarchartConfig(main: BarchartMainConfig = BarchartMainConfig(),
                          fill: BarchartFillConfig = BarchartFillConfig(),
                          line: BarchartLineConfig = BarchartLineConfig(),
                          errorBarLine: BarchartErrorBarLineConfig = BarchartErrorBarLineConfig())