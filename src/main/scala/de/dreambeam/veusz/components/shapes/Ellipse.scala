package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, PageItem}

object Ellipse {
  def apply(xPosition: Double, yPosition: Double): Ellipse = Ellipse(Vector(xPosition), Vector(yPosition))

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double): Ellipse = Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height))

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode = positionMode)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode = positionMode, xAxis = xAxis, yAxis = yAxis)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, xAxis = xAxis, yAxis = yAxis)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, xAxis: String, yAxis: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), xAxis = xAxis, yAxis = yAxis)


  def apply(xPosition: Double, yPosition: Double, name: String): Ellipse = Ellipse(Vector(xPosition), Vector(yPosition), name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, name: String): Ellipse = Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode = positionMode, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode = positionMode, xAxis = xAxis, yAxis = yAxis, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, xAxis = xAxis, yAxis = yAxis, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, xAxis: String, yAxis: String, name: String): Ellipse =
    Ellipse(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), xAxis = xAxis, yAxis = yAxis, name = name)


}

case class Ellipse(var xPositions: Vector[Double] = Vector(0.5),
                   var yPositions: Vector[Double] = Vector(0.5),
                   var widths: Vector[Double] = Vector(1.0),
                   var heights: Vector[Double] = Vector(1.0),
                   var rotate: Double = 0.0,
                   var positionMode: Positioning.Value = Positioning.Relative,
                   var xAxis: String = "x",
                   var yAxis: String = "y",
                   var name: String = "ellipse",
                   var config: EllipseConfig = EllipseConfig())
    extends PageItem with Configurable with Executable {
  val group = "ellipse"

}

case class EllipseConfig(main: EllipseMainConfig = EllipseMainConfig(), fill: BackgroundConfig = BackgroundConfig(hide = true), border: BorderConfig = BorderConfig())
