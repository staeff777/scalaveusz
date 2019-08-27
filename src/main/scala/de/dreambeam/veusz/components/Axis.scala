package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Item, GraphItem, Executable, Configurable}
import de.dreambeam.veusz.format._

case class Axis (var label: String = "",
                 var min: Option[Double] = None,
                 var max: Option[Double] = None,
                 var log: Boolean = false,
                 var mode: AxisMode.Value = AxisMode.Numeric,
                 var scale: Double = 1,
                 var minPos: Double = 0,
                 var maxPos: Double = 1,
                 var direction: String = "horizontal",
                 var axisPosition: Double = 0,
                 var name: String = ""
                )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "axis"
  var config: AxisConfig = AxisConfig()
}

case class AxisConfig(main: AxisMainConfig = AxisMainConfig(),
                      axisLine: de.dreambeam.veusz.format.LineConfig = de.dreambeam.veusz.format.LineConfig(),
                      axisLabel: de.dreambeam.veusz.format.LabelConfig = de.dreambeam.veusz.format.LabelConfig(),
                      tickLabels: TickLabelsConfig = TickLabelsConfig(),
                      majorTicks: MajorTicksConfig = MajorTicksConfig(),
                      minorTicks: MinorTicksConfig = MinorTicksConfig(),
                      majorGridLines: MajorGridLinesConfig = MajorGridLinesConfig(),
                      minorGridLines: MinorGridLinesConfig = MinorGridLinesConfig()
                     )
object XAxis {

  def apply(label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition: Double = 0,
            name: String = "x") =
    Axis (label, min, max, log, mode, scale, minPos, maxPos, "horizontal", axisPosition, name)
}

object YAxis {

  def apply(label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.Numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxPos: Double = 1,
            axisPosition: Double = 0,
            name: String = "y") =
    Axis(label, min, max, log, mode, scale, minPos, maxPos, "vertical", axisPosition, name)
}

