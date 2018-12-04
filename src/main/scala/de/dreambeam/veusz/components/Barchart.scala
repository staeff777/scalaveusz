package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.types.Children
import de.dreambeam.veusz.format._

object Barchart {
  val defaultName = "bar"

  def apply(
            name: String = defaultName,
            lengths: Vector[String] = Vector("y"),
            direction: Direction.Value = Direction.Vertical,
            mode: BarchartMode.Value = BarchartMode.Stacked,
            keys: Vector[String] = Vector(""),
            xAxis: String = "x",
            yAxis: String = "y"
            ) =
    new Barchart(name, None, lengths, direction, mode, keys, xAxis, yAxis)
}

class Barchart private (val name: String = Barchart.defaultName,
                        val children: Option[Children] = None,
                        var lengths: Vector[String] = Vector("y"),
                        var direction: Direction.Value = Direction.Vertical,
                        var mode: BarchartMode.Value = BarchartMode.Stacked,
                        var keys: Vector[String] = Vector(""),
                        var xAxis: String = "x",
                        var yAxis: String = "y") // TODO add labels, (and perhaps positions)
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "bar"

  object config {
    val main = BarchartMainConfig()
    val fill = BarchartFillConfig()
    val line = BarchartLineConfig()
    val errorBarLine = BarchartErrorBarLineConfig()
  }
}