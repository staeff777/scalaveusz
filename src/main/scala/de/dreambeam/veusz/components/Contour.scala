package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

case class Contour(dataset: String = "",
                   min: Option[Double] = None,
                   max: Option[Double] = None,
                   numberLevels: Int = 5,
                   scaling: ImageScaling.Value = ImageScaling.Linear,
                   manualLevels: Option[Int] = None,
                   levelsInKey: Boolean = false,
                   xAxis: String = "x",
                   yAxis: String = "y",
                   name: String = "contour",
                   )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "contour"
  var config = ContourConfig()
}

case class ContourConfig(var hide: Boolean = false,
                         var labels: ContourLabelsConfig = ContourLabelsConfig(),
                         var lines: ContourLinesConfig = ContourLinesConfig(),
                         var sublines: ContourSubLinesConfig = ContourSubLinesConfig(),
                         var fill: ContourFillConfig = ContourFillConfig())
