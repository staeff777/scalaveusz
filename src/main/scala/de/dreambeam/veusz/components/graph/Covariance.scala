package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

case class Covariance(xData: String = "",
                      yData: String = "",
                      covXX: String = "",
                      covYX: String = "",
                      covXY: String = "",
                      covYY: String = "",
                      keyText: String = "",
                      xAxis: String = "x",
                      yAxis: String = "y",
                      name: String = "covariance")
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "covariance"
  var config = CovarianceConfig()
}

case class CovarianceConfig(var hide: Boolean = false,
                            var line: ContourEllipseLineConfig = ContourEllipseLineConfig(),
                            var fill: FillConfig = FillConfig(color=Colors.Foreground, hide=true))