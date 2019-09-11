package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object Rectangle {
  def apply(xPosition: Double, yPosition: Double): Rectangle = new Rectangle(Vector(xPosition), Vector(yPosition))
  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double): Rectangle = new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height))

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, config = config)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), config = config)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate = rotate, config = config)

  def apply(xPosition: Double, yPosition: Double, name: String): Rectangle = new Rectangle(Vector(xPosition), Vector(yPosition))

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, name: String): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, name: String): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positionMode: Positioning.Value, name: String): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positionMode, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, name: String): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, name = name)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, positionMode: Positioning.Value, name: String, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positionMode = positionMode, name = name, config = config)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, name: String, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), name = name, config = config)

  def apply(xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, name: String, config: RectangleConfig): Rectangle =
    new Rectangle(Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate = rotate, name = name, config = config)

}

case class Rectangle(xPositions: Vector[Double] = Vector(0.5),
                     yPositions: Vector[Double] = Vector(0.5),
                     widths: Vector[Double] = Vector(1.0),
                     heights: Vector[Double] = Vector(1.0),
                     rotate: Double = 0.0,
                     positionMode: Positioning.Value = Positioning.Relative,
                     xAxis: String = "x",
                     yAxis: String = "y",
                     var name: String = "rect",
                     var config: RectangleConfig = RectangleConfig())
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "rect"
}

case class RectangleConfig(main: RectangleMainConfig = RectangleMainConfig(), fill: BackgroundConfig = BackgroundConfig(hide = true), border: BorderConfig = BorderConfig())
