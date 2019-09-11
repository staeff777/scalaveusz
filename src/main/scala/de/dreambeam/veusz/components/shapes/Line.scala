package de.dreambeam.veusz.components.shapes

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

case class Line(xPositions: Vector[Double] = Vector(0.5),
                yPositions: Vector[Double] = Vector(0.5),
                lengths: Vector[Double] = Vector(0.2),
                angles: Vector[Double] = Vector(0.0),
                positionMode: Positioning.Value = Positioning.Relative,
                xAxis: String = "x",
                yAxis: String = "y",
                var name: String = "line")
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "line"
  var config: LineConfig = LineConfig()
}

case class LineConfig(main: LineMainConfig = LineMainConfig(),
                      line: BorderConfig = BorderConfig(color = Colors.Foreground),
                      arrowFill: SimpleFill = SimpleFill())