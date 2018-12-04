package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children

object Colorbar {
  val defaultName = "colorbar"

  // TODO (perhaps insert match)
  def apply(widget: String = "",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1.0,
            direction: Direction.Value = Direction.Horizontal,
            minPosition: Double = 0.0,
            maxPosition: Double = 1.0,
            axisPosition: Int = 0
           ) =
    new Colorbar(defaultName, None, widget, label, min, max, log, mode, scale, direction, minPosition, maxPosition, axisPosition)

}

class Colorbar(val name: String,
               val children: Option[Children] = None,
               val widget: String,
               val label: String,
               val min: Option[Double],
               val max: Option[Double],
               val log: Boolean,
               val mode: AxisMode.Value,
               val scale: Double,
               val direction: Direction.Value,
               val minPosition: Double,
               val maxPosition: Double,
               val axisPosition: Int
               )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "colorbar"

  object config {
    val main = ColorBarMainConfig()
    val axisLine = LineConfig()
    val axisLabel = LabelConfig()
    val tickLabels = TickLabelsConfig()
    val majorTicks = MajorTicksConfig()
    val minorTicks = MinorTicksConfig()
    val majorGridLines = MajorGridLinesConfig()
    val minorGridLines = MinorGridLinesConfig()
    val border = BorderConfig()
    val whiskerLine = LineConfig()
    val markersBorder = BorderConfig()
    val markersFill = BackgroundConfig()
  }
}
