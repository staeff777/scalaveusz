package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Item, GraphItem, Executable, Configurable}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.util.SizeUnits._

object Axis {
  def apply(name: String,
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            direction: String = "horizontal",
            axisPosition: Double = 0
            ) =
    new Axis(name, None, label, min, max, log, mode, scale, minPos, maxPos, direction, axisPosition)

}

class Axis private (val name: String,
                    val children: Option[Vector[Item]],
                    var label: String,
                    var min: Option[Double],
                    var max: Option[Double],
                    var log: Boolean,
                    var mode: AxisMode.Value,
                    var scale: Double,
                    var minPos: Double,
                    var maxPos: Double,
                    var direction: String,
                    var axisPosition: Double
                   )
    extends GraphItem
      with Configurable
      with Executable
{
  val group = "axis"

  object config {
    val main = AxisMainConfig()
    val axisLine = LineConfig()
    val axisLabel = LabelConfig()
    val tickLabels = TickLabelsConfig()
    val majorTicks = MajorTicksConfig()
    val minorTicks = MinorTicksConfig()
    val majorGridLines = MajorGridLinesConfig()
    val minorGridLines = MinorGridLinesConfig()
  }
}

object XAxis {

  def apply(name: String = "x",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition: Double = 0) =
    Axis(name, label, min, max, log, mode, scale, minPos, maxPos, "horizontal", axisPosition)
}

object YAxis {

  def apply(name: String = "y",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition: Double = 0) =
    Axis(name, label, min, max, log, mode, scale, minPos, maxPos, "vertical", axisPosition)
}

