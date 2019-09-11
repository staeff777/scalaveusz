package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

case class Contours(var dataset: String = "",
                    var min: Option[Double] = None,
                    var max: Option[Double] = None,
                    var numberLevels: Int = 5,
                    var scaling: Scaling.Value = Scaling.Linear,
                    var manualLevels: Option[Int] = None,
                    var levelsInKey: Boolean = false,
                    var xAxis: String = "x",
                    var yAxis: String = "y",
                    var name: String = "contour",
                    var config: ContoursConfig = ContoursConfig()
                   )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "contours"
}

case class ContoursConfig(var hide: Boolean = false,
                          var labels: ContourLabelsConfig = ContourLabelsConfig(),
                          var lines: ContourLinesConfig = ContourLinesConfig(),
                          var sublines: ContourSubLinesConfig = ContourSubLinesConfig(),
                          var fill: ContourFillConfig = ContourFillConfig())
