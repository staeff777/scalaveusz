package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children

object Label {
  val defaultName = "label"

  def apply(
           label: String,
           xPositions: Vector[Double] = Vector(0.5),
           yPositions: Vector[Double] = Vector(0.5),
           positionMode: PositionMode.Value = PositionMode.Relative,
           xAxis: String = "x",
           yAxis: String = "y"
           ) =
    new Label(defaultName, None, label, xPositions, yPositions, positionMode, xAxis, yAxis)

}

class Label private (val name: String = Label.defaultName,
                     var children: Option[Children] = None,
                     val label: String,
                     val xPositions: Vector[Double],
                     val yPositions: Vector[Double],
                     val positionMode: PositionMode.Value = PositionMode.Relative,
                     val xAxis: String = "x",
                     val yAxis: String = "y"
                     )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "label"

  object config {
    val alignment = LabelMainConfig()
    val text = TextConfig()
    val background = BackgroundConfig()
    val border = BorderConfig()
  }
}