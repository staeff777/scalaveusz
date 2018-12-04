package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object Line {
  val defaultName = "line"

  def apply(xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            lengths: Vector[Double] = Vector(0.2),
            angles: Vector[Double] = Vector(0.0),
            positionMode: PositionMode.Value = PositionMode.Relative,
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new Line(defaultName, None, xPositions, yPositions, lengths, angles, positionMode, xAxis, yAxis)
}

class Line private (var name: String = Function.defaultName,
                    val children: Option[Children] = None,
                    val xPositions: Vector[Double],
                    val yPositions: Vector[Double],
                    val lengths: Vector[Double],
                    val angles: Vector[Double],
                    val positionMode: PositionMode.Value,
                    val xAxis: String,
                    val yAxis: String
                    )
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "line"

  object config {
    val main = LineMainConfig()
    val line = BorderConfig(color = Colors.Foreground)
    val arrowFill = BackgroundConfig()
  }
}