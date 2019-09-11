package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

case class Covariance(var xData: String = "",
                      var yData: String = "",
                      var covXX: String = "",
                      var covYX: String = "",
                      var covXY: String = "",
                      var covYY: String = "",
                      var keyText: String = "",
                      var xAxis: String = "x",
                      var yAxis: String = "y",
                      var name: String = "covariance",
                      var config: CovarianceConfig = CovarianceConfig())
    extends GraphItem with Configurable with Executable {
  val group = "covariance"
}

case class CovarianceConfig(var hide: Boolean = false,
                            var line: ContourEllipseLineConfig = ContourEllipseLineConfig(),
                            var fill: FillConfig = FillConfig(color = Colors.Foreground, hide = true))
