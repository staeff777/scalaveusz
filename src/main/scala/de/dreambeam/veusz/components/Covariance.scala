package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children


object Covariance {
  val defaultName = "covariance"

  def apply(xData: String = "",
            yData: String = "",
            name: String = defaultName,
            covXX: String = "",
            covYX: String = "",
            covXY: String = "",
            covYY: String = "",
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new Covariance(name, None, xData, yData, covXX, covYX, covXY, covYY, keyText, xAxis, yAxis)

}

class Covariance(val name: String,
                 val children: Option[Children] = None,
                 val xData: String,
                 val yData: String,
                 val covXX: String,
                 val covYX: String,
                 val covXY: String,
                 val covYY: String,
                 val keyText: String,
                 val xAxis: String,
                 val yAxis: String
                )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "covariance"

  object config {
    var hide: Boolean = false
    val line = ContourEllipseLineConfig()
    val fill = FillConfig(color=Colors.Foreground, hide=true)
  }
}

