package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object LineLengthAngle {

  def apply(xPosition: Double, yPosition: Double, length: Double, angle: Double): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angle))

  def apply(xPosition: Double, yPosition: Double, length: Double, angles: Double, positionMode: Positioning.Value): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angles), positionMode)

  def apply(xPosition: Double, yPosition: Double, length: Double, angles: Double, name: String): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angles), name = name)

  def apply(xPosition: Double, yPosition: Double, length: Double, angles: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angles), positionMode, xAxis, yAxis)

  def apply(xPosition: Double, yPosition: Double, length: Double, angles: Double, xAxis: String, yAxis: String): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angles), xAxis = xAxis, yAxis = yAxis)

  def apply(xPosition: Double, yPosition: Double, length: Double, angles: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String): LineLengthAngle =
    LineLengthAngle(Vector(xPosition), Vector(yPosition), Vector(length), Vector(angles), positionMode, xAxis, yAxis, name)

}

case class LineLengthAngle(var xPositions: Vector[Double] = Vector(0.5),
                           var yPositions: Vector[Double] = Vector(0.5),
                           var lengths: Vector[Double] = Vector(0.2),
                           var angles: Vector[Double] = Vector(0.0),
                           var positionMode: Positioning.Value = Positioning.Relative,
                           var xAxis: String = "x",
                           var yAxis: String = "y",
                           var name: String = "line")
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "line"
  var config: LineConfig = LineConfig()
}

case class LineConfig(main: LineMainConfig = LineMainConfig(), line: BorderConfig = BorderConfig(color = Colors.Foreground), arrowFill: SimpleFill = SimpleFill())
