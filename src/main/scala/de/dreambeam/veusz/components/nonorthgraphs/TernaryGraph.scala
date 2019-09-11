package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.format.{AxisLabelConfig, BackgroundConfig, BorderConfig, MajorGridLinesConfig, MajorTicksConfig, MinorGridLinesConfig, MinorTicksConfig, PolarGraphMainConfig, TernaryCoordSystem, TernaryGraphMode, TernaryTickLabelsConfig, TickLabelsConfig}
import de.dreambeam.veusz.{Configurable, Executable, GridItem, NonOrthGraphItem, PageItem, Parent}

object TernaryGraph {

  def apply(children: NonOrthGraphItem*): TernaryGraph = TernaryGraph(children = children.toVector)
  def apply(name: String, children: NonOrthGraphItem*): TernaryGraph = TernaryGraph(name = name, children = children.toVector)
  def apply(label_bottom: String, label_left: String, label_right: String, children: NonOrthGraphItem*): TernaryGraph = TernaryGraph(label_bottom = label_bottom, label_left = label_left, label_right = label_right,  children = children.toVector)
  def apply(label_bottom: String, label_left: String, label_right: String, name: String, children: NonOrthGraphItem*): TernaryGraph = TernaryGraph(label_bottom = label_bottom, label_left = label_left, label_right = label_right, name = name,  children = children.toVector)

}

case class TernaryGraph(var mode: TernaryGraphMode.Value = TernaryGraphMode.Percentage,
                        var coord_System: TernaryCoordSystem.Value = TernaryCoordSystem.Bottom_Left,
                        var label_bottom: String = "",
                        var label_left: String = "",
                        var label_right: String = "",
                        var left_origin: Double = 0.0,
                        var bottom_origin: Double = 0.0,
                        var size: Double = 1.0,
                        var reverse: Boolean = false,
                        var name: String = "ternary",
                        var children: Vector[NonOrthGraphItem] = Vector.empty,
                        var config: TernaryGraphConfig = TernaryGraphConfig()
                        )
    extends PageItem with GridItem with Parent with Executable with Configurable {
  override def group: String = "ternary"
}

case class TernaryGraphConfig(var main: PolarGraphMainConfig = PolarGraphMainConfig(),
                              var background: BackgroundConfig = BackgroundConfig(),
                              var border: BorderConfig = BorderConfig(),
                              var axisLabel: AxisLabelConfig = AxisLabelConfig(),
                              var tickLabelsConfig: TernaryTickLabelsConfig = TernaryTickLabelsConfig(),
                              var majorTicks: MajorTicksConfig = MajorTicksConfig(number = 10),
                              var minorTicks: MinorTicksConfig = MinorTicksConfig(number = 50),
                              var majorGridLines: MajorGridLinesConfig = MajorGridLinesConfig(hide = false),
                              var minorGridLines: MinorGridLinesConfig = MinorGridLinesConfig())
