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
