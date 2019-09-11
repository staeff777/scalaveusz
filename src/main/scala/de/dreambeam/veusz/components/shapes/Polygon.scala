package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object Polygon {


  def apply(traverse: Vector[(Double, Double)]): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode)
  }

  def apply(traverse: Vector[(Double, Double)], name: String): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, name = name)
  }

  def apply(traverse: Vector[(Double, Double)], xAxis: String, yAxis: String): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, xAxis = xAxis, yAxis = yAxis)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value, xAxis: String, yAxis: String): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode, xAxis, yAxis)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode, xAxis, yAxis, name)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String, config: PolygonConfig): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode, xAxis, yAxis, name, config)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value, xAxis: String, yAxis: String, config: PolygonConfig): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode, xAxis, yAxis, config = config)
  }

  def apply(traverse: Vector[(Double, Double)], positionMode: Positioning.Value, config: PolygonConfig): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, positionMode, config = config)
  }

  def apply(traverse: Vector[(Double, Double)], config: PolygonConfig): Polygon = {
    val (x, y) = traverse.unzip
    Polygon(x, y, config = config)
  }
}

case class Polygon(var xPositions: Vector[Double] = Vector(0.5),
                   var yPositions: Vector[Double] = Vector(0.5),
                   var positionMode: Positioning.Value = Positioning.Relative,
                   var xAxis: String = "x",
                   var yAxis: String = "y",
                   var name: String = "polygon",
                   var config: PolygonConfig = PolygonConfig())
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "polygon"

}

case class PolygonConfig(var hide: Boolean = false, line: BorderConfig = BorderConfig(), fill: BackgroundConfig = BackgroundConfig(color = Colors.Foreground))
