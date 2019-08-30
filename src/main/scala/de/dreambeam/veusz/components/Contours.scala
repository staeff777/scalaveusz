package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

case class Contours(dataset: String = "",
                    min: Option[Double] = None,
                    max: Option[Double] = None,
                    numberLevels: Int = 5,
                    scaling: Scaling.Value = Scaling.Linear,
                    manualLevels: Option[Int] = None,
                    levelsInKey: Boolean = false,
                    xAxis: String = "x",
                    yAxis: String = "y",
                    name: String = "contour"
                   )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "contours"
  var config = ContoursConfig()
}

case class ContoursConfig(var hide: Boolean = false,
                          var labels: ContourLabelsConfig = ContourLabelsConfig(),
                          var lines: ContourLinesConfig = ContourLinesConfig(),
                          var sublines: ContourSubLinesConfig = ContourSubLinesConfig(),
                          var fill: ContourFillConfig = ContourFillConfig())
