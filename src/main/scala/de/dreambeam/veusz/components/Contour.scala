package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children


object Contour {
  val defaultName = "contour"

  def apply(name: String = defaultName,
            dataset: String = "",
            minValue: Option[Double] = None,
            maxValue: Option[Double] = None,
            numberLevels: Int = 5,
            scaling: ImageScaling.Value = ImageScaling.Linear,
            manualLevels: Option[Int] = None,
            levelsInKey: Boolean = false,
            xAxis: String = "x",
            yAxis: String = "y"
           ) =
    new Contour(name, None, dataset, minValue, maxValue, numberLevels, scaling, manualLevels, levelsInKey, xAxis, yAxis)

}

class Contour(val name: String,
              val children: Option[Children] = None,
              val dataset: String,
              val min: Option[Double],
              val max: Option[Double],
              val numberLevels: Int,
              val scaling: ImageScaling.Value,
              val manualLevels: Option[Int],
              val levelsInKey: Boolean,
              val xAxis: String,
              val yAxis: String
              )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "contour"

  object config {
    var hide: Boolean = false
    val labels = ContourLabelsConfig()
    val lines = ContourLinesConfig()
    val sublines = ContourSubLinesConfig()
    val fill = ContourFillConfig()
  }
}

