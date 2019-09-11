package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, NonOrthGraphItem}
import de.dreambeam.veusz.format._


case class Colorbar(var widget: String = "",
                    var label: String = "",
                    var min: Option[Double] = None,
                    var max: Option[Double] = None,
                    var log: Boolean = false,
                    var mode: AxisMode.Value = AxisMode.Numeric,
                    var scale: Double = 1.0,
                    var direction: Direction.Value = Direction.Horizontal,
                    var minPosition: Double = 0.0,
                    var maxPosition: Double = 1.0,
                    var axisPosition: Int = 0,
                    var name: String = "colorbar",
                    var config: ColorbarConfig = ColorbarConfig())
    extends GraphItem with NonOrthGraphItem with Configurable with Executable {
  val group = "colorbar"
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
