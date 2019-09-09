package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.format.{
  AxisLabelConfig,
  BackgroundConfig,
  BorderConfig,
  MajorGridLinesConfig,
  MajorTicksConfig,
  MinorGridLinesConfig,
  MinorTicksConfig,
  PolarGraphMainConfig,
  TickLabelsConfig
}
import de.dreambeam.veusz.{Configurable, Executable, GridItem, NonOrthGraphItem, PageItem, Parent}

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
                        children: Vector[NonOrthGraphItem])
    extends PageItem with GridItem with Parent with Executable with Configurable {
  override def group: String = "ternary"
}

case class TernaryGraphConfig(var main: PolarGraphMainConfig = PolarGraphMainConfig(),
                              var background: BackgroundConfig = BackgroundConfig(),
                              var border: BorderConfig = BorderConfig(),
                              var axisLabel: AxisLabelConfig = AxisLabelConfig(),
                              var tickLabelsConfig: TickLabelsConfig = TickLabelsConfig(),
                              var majorTicks: MajorTicksConfig = MajorTicksConfig(),
                              var minorTicks: MinorTicksConfig = MinorTicksConfig(),
                              var majorGridLines: MajorGridLinesConfig = MajorGridLinesConfig(),
                              var minorGridLines: MinorGridLinesConfig = MinorGridLinesConfig())

object TernaryGraphMode extends Enumeration {
  val Percentage = Value("percentage")
  val Fraction = Value("fraction")
}

object TernaryCoordSystem extends Enumeration {
  val Bottom_Left = Value("bottom-left")
  val Bottom_Right = Value("bottom-right")
  val Left_Bottom = Value("left-bottom")
  val Left_Right = Value("left-right")
  val Right_Left = Value("right-left")
  val Right_Bottom = Value("right-bottom")
}
