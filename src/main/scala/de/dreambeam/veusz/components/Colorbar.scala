package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PolarGraphItem}
import de.dreambeam.veusz.format._

case class Colorbar(widget: String = "",
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
                    var name: String = "colorbar"
                   )
  extends GraphItem
    with PolarGraphItem
    with Configurable
    with Executable
{
  val group = "polargraph"
  var config: ColorbarConfig = ColorbarConfig()
}

case class ColorbarConfig(main: ColorBarMainConfig = ColorBarMainConfig(),
                          axisLine: de.dreambeam.veusz.format.LineConfig = de.dreambeam.veusz.format.LineConfig(),
                          axisLabel: de.dreambeam.veusz.format.LabelConfig = de.dreambeam.veusz.format.LabelConfig(),
                          tickLabels: TickLabelsConfig = TickLabelsConfig(),
                          majorTicks: MajorTicksConfig = MajorTicksConfig(),
                          minorTicks: MinorTicksConfig = MinorTicksConfig(),
                          majorGridLines: MajorGridLinesConfig = MajorGridLinesConfig(),
                          minorGridLines: MinorGridLinesConfig = MinorGridLinesConfig(),
                          border: BorderConfig = BorderConfig(),
                          whiskerLine: de.dreambeam.veusz.format.LineConfig = de.dreambeam.veusz.format.LineConfig(),
                          markersBorder: BorderConfig = BorderConfig(),
                          markersFill: BackgroundConfig = BackgroundConfig())