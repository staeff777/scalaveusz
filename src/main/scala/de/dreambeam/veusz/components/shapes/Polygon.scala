package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object Polygon {
  val defaultName = "polygon"

  def apply(xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            positionMode: PositionMode.Value = PositionMode.Relative,
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new Polygon(defaultName, None, xPositions, yPositions, positionMode, xAxis, yAxis)
}

class Polygon private (var name: String = Function.defaultName,
                       val children: Option[Children] = None,
                       val xPositions: Vector[Double],
                       val yPositions: Vector[Double],
                       val positionMode: PositionMode.Value,
                       val xAxis: String,
                       val yAxis: String
                      )
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "polygon"

  object config {
    /* Properties */
    var hide = false

    val line = BorderConfig()
    val fill = BackgroundConfig(color = Colors.Foreground)
  }
}