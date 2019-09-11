package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

object ImageFile {

  def apply(fileName: String, xPosition: Double, yPosition: Double): ImageFile = ImageFile(fileName, Vector(xPosition), Vector(yPosition))

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height))

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate)

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, positioning: Positioning.Value): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), positioning = positioning)

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positioning: Positioning.Value): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positioning = positioning)

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, positioning: Positioning.Value, xAxis: String, yAxis: String)
    : ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positioning = positioning, xAxis, yAxis)

  def apply(fileName: String,
            xPosition: Double,
            yPosition: Double,
            width: Double,
            height: Double,
            rotate: Double,
            positioning: Positioning.Value,
            xAxis: String,
            yAxis: String,
            name: String): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positioning = positioning, xAxis, yAxis, name)

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, xAxis: String, yAxis: String): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, xAxis = xAxis, yAxis = yAxis)

  def apply(fileName: String, xPosition: Double, yPosition: Double, width: Double, height: Double, rotate: Double, xAxis: String, yAxis: String, name: String): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, xAxis = xAxis, yAxis = yAxis, name = name)

  def apply(fileName: String,
            xPosition: Double,
            yPosition: Double,
            width: Double,
            height: Double,
            rotate: Double,
            positioning: Positioning.Value,
            xAxis: String,
            yAxis: String,
            name: String,
            config: ImageFileConfig): ImageFile =
    ImageFile(fileName, Vector(xPosition), Vector(yPosition), Vector(width), Vector(height), rotate, positioning = positioning, xAxis, yAxis, name, config)

  def apply(filename: String,
            xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            widths: Vector[Double] = Vector(1.0),
            heights: Vector[Double] = Vector(1.0),
            rotate: Double = 0.0,
            positioning: Positioning.Value = Positioning.Relative,
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "imagefile",
            config: ImageFileConfig = ImageFileConfig()): ImageFile =
    new ImageFile(filename, xPositions, yPositions, widths, heights, rotate, positioning, xAxis, yAxis, name, config)
}

case class ImageFile(filename: String,
                     xPositions: Vector[Double],
                     yPositions: Vector[Double],
                     widths: Vector[Double],
                     heights: Vector[Double],
                     rotate: Double,
                     positioning: Positioning.Value,
                     xAxis: String,
                     yAxis: String,
                     var name: String,
                     var config: ImageFileConfig)
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "imagefile"

}

case class ImageFileConfig(main: ImageFileMainConfig = ImageFileMainConfig(),
                           fill: BackgroundConfig = BackgroundConfig(hide = true),
                           border: BorderConfig = BorderConfig(hide = true))
