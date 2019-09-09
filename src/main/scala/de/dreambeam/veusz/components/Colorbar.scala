package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, NonOrthGraphItem}
import de.dreambeam.veusz.format._

object Colorbar {

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
            axisPosition: Int = 0,
            name: String = "colorbar"): Colorbar = new Colorbar(widget, label, min, max, log, mode, scale, direction, minPosition, maxPosition, axisPosition, name)

}

case class Colorbar(widget: String,
                    label: String,
                    min: Option[Double],
                    max: Option[Double],
                    log: Boolean,
                    mode: AxisMode.Value,
                    scale: Double,
                    direction: Direction.Value,
                    minPosition: Double,
                    maxPosition: Double,
                    axisPosition: Int,
                    var name: String)
    extends GraphItem with NonOrthGraphItem with Configurable with Executable {
  val group = "colorbar"
  var config: ColorbarConfig = ColorbarConfig()
}

case class ColorbarConfig(main: ColorBarMainConfig = ColorBarMainConfig(),
                          axisLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(),
                          axisLabel: de.dreambeam.veusz.format.AxisLabelConfig = de.dreambeam.veusz.format.AxisLabelConfig(),
                          tickLabels: TickLabelsConfig = TickLabelsConfig(),
                          majorTicks: MajorTicksConfig = MajorTicksConfig(),
                          minorTicks: MinorTicksConfig = MinorTicksConfig(),
                          majorGridLines: MajorGridLinesConfig = MajorGridLinesConfig(),
                          minorGridLines: MinorGridLinesConfig = MinorGridLinesConfig(),
                          border: BorderConfig = BorderConfig(),
                          whiskerLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(),
                          markersBorder: BorderConfig = BorderConfig(),
                          markersFill: BackgroundConfig = BackgroundConfig())
