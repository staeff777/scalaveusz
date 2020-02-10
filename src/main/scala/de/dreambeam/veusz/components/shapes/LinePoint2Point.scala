package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}
import de.dreambeam.veusz.format.{Positioning}

object LinePoint2Point {

  def apply(xPosition: Double, yPosition: Double, xPosition2: Double, yPosition2: Double): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2))

  def apply(xPosition: Double, yPosition: Double, xPosition2: Double, yPosition2: Double, name: String): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2), name = name)

  def createPoint2PointLine(xPosition: Double, yPosition: Double, xPosition2: Double, yPosition2: Double, positionMode: Positioning.Value): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2), positionMode)

  def createPoint2PointLine(xPosition: Double,
                            yPosition: Double,
                            xPosition2: Double,
                            yPosition2: Double,
                            positionMode: Positioning.Value,
                            xAxis: String,
                            yAxis: String): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2), positionMode, xAxis, yAxis)

  def apply(xPosition: Double, yPosition: Double, xPosition2: Double, yPosition2: Double, xAxis: String, yAxis: String): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2), xAxis = xAxis, yAxis = yAxis)

  def createPoint2PointLine(xPosition: Double,
                            yPosition: Double,
                            xPosition2: Double,
                            yPosition2: Double,
                            positionMode: Positioning.Value,
                            xAxis: String,
                            yAxis: String,
                            name: String): LinePoint2Point =
    LinePoint2Point(Vector(xPosition), Vector(yPosition), Vector(xPosition2), Vector(yPosition2), positionMode, xAxis, yAxis, name)

}

case class LinePoint2Point(var xPositions: Vector[Double] = Vector(0.5),
                           var yPositions: Vector[Double] = Vector(0.5),
                           var xPositions2: Vector[Double] = Vector.empty,
                           var yPositions2: Vector[Double] = Vector.empty,
                           var positionMode: Positioning.Value = Positioning.Relative,
                           var xAxis: String = "x",
                           var yAxis: String = "y",
                           var name: String = "line")
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "line"
  var config: LineConfig = LineConfig()
}
