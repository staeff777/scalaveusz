package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem}

case class Polygon(xPositions: Vector[Double] = Vector(0.5),
                   yPositions: Vector[Double] = Vector(0.5),
                   positionMode: PositionMode.Value = PositionMode.Relative,
                   xAxis: String = "x",
                   yAxis: String = "y",
                   var name: String = "polygon")
  extends GraphItem
    with PageItem
    with Configurable
    with Executable
{
  val group = "polygon"
  var config: PolygonConfig = PolygonConfig()
}

case class PolygonConfig(var hide: Boolean = false,
                         line: BorderConfig = BorderConfig(),
                         fill: BackgroundConfig = BackgroundConfig(color = Colors.Foreground)
                        )