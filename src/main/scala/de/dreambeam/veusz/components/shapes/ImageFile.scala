package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object ImageFile {
  val defaultName = "imagefile"

  def apply(filename: String,
            xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            widths: Vector[Double] = Vector(1.0),
            heights: Vector[Double] = Vector(1.0),
            rotate: Double = 0.0,
            positionMode: PositionMode.Value = PositionMode.Relative,
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new ImageFile(defaultName, None, filename, xPositions, yPositions, widths, heights, rotate, positionMode, xAxis, yAxis)
}

class ImageFile private (var name: String = Function.defaultName,
                         val children: Option[Children] = None,
                         val filename: String,
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
  val group = "imagefile"

  object config {
    val main = ImageFileMainConfig()
    val fill = BackgroundConfig(hide=true)
    val border = BorderConfig(hide=true)
  }
}