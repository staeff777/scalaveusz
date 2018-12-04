package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object Rectangle {
  val defaultName = "rect"

  def apply(xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            widths: Vector[Double] = Vector(1.0),
            heights: Vector[Double] = Vector(1.0),
            rotate: Double = 0.0,
            positionMode: PositionMode.Value = PositionMode.Relative,
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new Rectangle(defaultName, None, xPositions, yPositions, widths, heights, rotate, positionMode, xAxis, yAxis)
}

class Rectangle private (var name: String = Function.defaultName,
                         val children: Option[Children] = None,
                         val xPositions: Vector[Double],
                         val yPositions: Vector[Double],
                         val widths: Vector[Double],
                         val heights: Vector[Double],
                         val rotate: Double,
                         val positionMode: PositionMode.Value,
                         val xAxis: String,
                         val yAxis: String
                        )
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "rect"

  object config {
    val main = RectangleMainConfig()
    val fill = BackgroundConfig(hide=true)
    val border = BorderConfig()
  }
}