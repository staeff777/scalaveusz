package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.{BarChartData, Numerical}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object Barchart {

  def fromSimpleLengthsVector(lengths: Vector[Double],
                              positions: Vector[Double],
                              direction: Direction.Value = Direction.Vertical,
                              mode: BarchartMode.Value = BarchartMode.Grouped,
                              keys: Vector[String] = Vector.empty,
                              xAxis: String = "x",
                              yAxis: String = "y",
                              name: String = "bar",
                              config: BarchartConfig = BarchartConfig()) = {
    val numLengths = Vector(Numerical(lengths))
    new Barchart(numLengths, Numerical(positions), direction, mode, keys, xAxis, yAxis, name, config)
  }

  def apply(lengths: Vector[Vector[Double]],
            positions: Vector[Double],
            direction: Direction.Value = Direction.Vertical,
            mode: BarchartMode.Value = BarchartMode.Grouped,
            keys: Vector[String] = Vector.empty,
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "bar",
            config: BarchartConfig = BarchartConfig()): Barchart = {
    val numLengths = lengths.map(l => Numerical(l))
    new Barchart(numLengths, Numerical(positions), direction, mode, keys, xAxis, yAxis, name, config)
  }

  def apply(lengths: Vector[Numerical],
            positions: BarChartData,
            direction: Direction.Value,
            mode: BarchartMode.Value,
            keys: Vector[String],
            xAxis: String,
            yAxis: String,
            name: String): Barchart = new Barchart(lengths, positions, direction, mode, keys, xAxis, yAxis, name, BarchartConfig())

  def apply(lengths: Vector[Numerical],
            positions: BarChartData,
            direction: Direction.Value,
            mode: BarchartMode.Value,
            keys: Vector[String],
            xAxis: String,
            yAxis: String): Barchart = new Barchart(lengths, positions, direction, mode, keys, xAxis, yAxis, "bar", BarchartConfig())

  def apply(lengths: Vector[Numerical],
            positions: BarChartData,
            direction: Direction.Value,
            mode: BarchartMode.Value,
            keys: Vector[String],
           ): Barchart = new Barchart(lengths, positions, direction, mode, keys, "x", "y", "bar", BarchartConfig())

  def apply(lengths: Vector[Numerical],
            positions: BarChartData,
            direction: Direction.Value,
            mode: BarchartMode.Value
           ): Barchart = new Barchart(lengths, positions, direction, mode, Vector.empty, "x", "y", "bar", BarchartConfig())

  def apply(lengths: Vector[Numerical],
            positions: BarChartData,
            direction: Direction.Value
           ): Barchart = new Barchart(lengths, positions, direction, BarchartMode.Grouped, Vector.empty, "x", "y", "bar", BarchartConfig())

  def apply(lengths: Vector[Numerical],
            positions: BarChartData
           ): Barchart = new Barchart(lengths, positions, Direction.Vertical, BarchartMode.Grouped, Vector.empty, "x", "y", "bar", BarchartConfig())
}

case class Barchart(var lengths: Vector[Numerical],
                    var positions: BarChartData,
                    var direction: Direction.Value,
                    var mode: BarchartMode.Value,
                    var keys: Vector[String],
                    var xAxis: String,
                    var yAxis: String,
                    var name: String,
                    var config: BarchartConfig)
    extends GraphItem with Configurable with Executable {
  val group = "bar"
}

case class BarchartConfig(var main: BarchartMainConfig = BarchartMainConfig(),
                          var fill: Vector[BarchartFillConfig] = Vector(BarchartFillConfig()),
                          var line: Vector[BarchartLineConfig] = Vector(BarchartLineConfig()),
                          var errorBarLine: BarchartErrorBarLineConfig = BarchartErrorBarLineConfig())
