package de.dreambeam.veusz.components

import de.dreambeam.veusz.data.{BarChartData, Data, DateTime, Numerical}
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

object Barchart {

  def apply(lengths: Vector[Numerical],
            positions: DateTime,
            direction: Direction.Value,
            mode: BarchartMode.Value,
            keys: Vector[String],
            xAxis: String,
            yAxis: String,
            name: String): Barchart = {
    Barchart(lengths, positions, direction, mode, keys, xAxis, yAxis, name)
  }

  def apply(lengths: Vector[Numerical],
            positions: Numerical,
            direction: Direction.Value = Direction.Vertical,
            mode: BarchartMode.Value = BarchartMode.Stacked,
            keys: Vector[String] = Vector(""),
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "bar"): Barchart = {

    Barchart(lengths, positions, direction, mode, keys, xAxis, yAxis, name)
  }
}

case class Barchart(lengths: Vector[Numerical],
                    positions: BarChartData,
                    direction: Direction.Value,
                    mode: BarchartMode.Value,
                    keys: Vector[String],
                    xAxis: String,
                    yAxis: String,
                    name: String)
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