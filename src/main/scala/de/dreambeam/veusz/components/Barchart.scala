package de.dreambeam.veusz.components

import de.dreambeam.veusz.data.{BarChartData, Data, DateTime, Numerical}
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

object Barchart {

  def fromSimpleLengthsVector(lengths: Vector[Double],
                              positions: Vector[Double],
                              direction: Direction.Value = Direction.Vertical,
                              mode: BarchartMode.Value = BarchartMode.Grouped,
                              keys: Vector[String] = Vector.empty,
                              xAxis: String = "x",
                              yAxis: String = "y",
                              name: String = "bar") = {
    val numLengths = Vector(Numerical(lengths))
    new Barchart(numLengths, Numerical(positions), direction, mode, keys, xAxis, yAxis, name)
  }

  def apply(lengths: Vector[Vector[Double]],
            positions: Vector[Double],
            direction: Direction.Value = Direction.Vertical,
            mode: BarchartMode.Value = BarchartMode.Grouped,
            keys: Vector[String] = Vector.empty,
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "bar"
           ): Barchart = {
    val numLengths = lengths.map(l => Numerical(l))
    new Barchart(numLengths, Numerical(positions), direction, mode, keys, xAxis, yAxis, name)
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

case class BarchartConfig(var main: BarchartMainConfig = BarchartMainConfig(),
                          var fill: Vector[BarchartFillConfig] = Vector(BarchartFillConfig()),
                          var line: Vector[BarchartLineConfig] = Vector(BarchartLineConfig()),
                          var errorBarLine: BarchartErrorBarLineConfig = BarchartErrorBarLineConfig())