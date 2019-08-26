package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

case class ImageFile (filename: String,
                      xPositions: Vector[Double] = Vector(0.5),
                      yPositions: Vector[Double] = Vector(0.5),
                      widths: Vector[Double] = Vector(1.0),
                      heights: Vector[Double] = Vector(1.0),
                      rotate: Double = 0.0,
                      positioning: Positioning.Value = Positioning.Relative,
                      xAxis: String = "x",
                      yAxis: String = "y",
                      var name: String = "imagefile",
                     )
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "imagefile"
  var config: ImageFileConfig = ImageFileConfig()
}

case class ImageFileConfig(main: ImageFileMainConfig = ImageFileMainConfig(),
                           fill: BackgroundConfig = BackgroundConfig(hide=true),
                           border: BorderConfig = BorderConfig(hide=true))