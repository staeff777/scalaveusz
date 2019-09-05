package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, PageItem}

case class Ellipse (xPositions: Vector[Double] = Vector(0.5),
                    yPositions: Vector[Double] = Vector(0.5),
                    widths: Vector[Double] = Vector(1.0),
                    heights: Vector[Double] = Vector(1.0),
                    rotate: Double = 0.0,
                    positionMode: Positioning.Value = Positioning.Relative,
                    xAxis: String = "x",
                    yAxis: String = "y",
                    var name: String = "ellipse"
                   )
    extends PageItem
      with Configurable
      with Executable
{
  val group = "ellipse"
  var config = EllipseConfig()
}

case class EllipseConfig(main: EllipseMainConfig = EllipseMainConfig(),
                         fill: BackgroundConfig = BackgroundConfig(hide=true),
                         border: BorderConfig = BorderConfig())