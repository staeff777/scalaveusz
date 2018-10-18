package de.dreambeam.veusz.model

import SizeUnits._

trait Config
trait ConfigureableItem { var config: Config }

sealed trait PageItem

object Document {

  def apply(pages: Vector[Page]) = new Document(pages)
  def apply(pages: Page*) = new Document(pages.toVector)
}
case class Document(var pages: Vector[Page], var config: DocumentConfig = DocumentConfig(), var none: Int = 0)
case class DocumentConfig(var width: SizeUnit = 15 cm, var height: SizeUnit = 15 cm, var englishLocal: Boolean = false) extends Config

object Page {
  def apply(pageItems: Vector[PageItem]) = new Page(pageItems.toVector)
  def apply(pageItems: PageItem*) = new Page(pageItems.toVector)
  def apply(name: String, pageItems: Vector[PageItem]) = new Page(pageItems.toVector, name)
  def apply(name: String, pageItems: PageItem*) = new Page(pageItems.toVector, name)
}
case class Page(var pageItems: Vector[PageItem], var name: String = "page", var config: PageConfig = PageConfig()) {}
case class PageConfig(var width: SizeUnit = 15 cm, var height: SizeUnit = 15 cm) extends Config

object Grid {
  def apply(pageItems: Vector[PageItem])(implicit config: GridConfig) = new Grid(pageItems, config)
}

case class Grid(var pageItems: Vector[PageItem],
                var config: GridConfig = new GridConfig(),
                var name: String = "grid",
                var rows: Int = 2,
                var columns: Int = 2,
                var scaleRows: String = "",
                var scaleCols: String = "")
    extends PageItem

case class GridConfig(var leftMargin: SizeUnit = 1.7 cm,
                      var rightMargin: SizeUnit = 0 cm,
                      var topMargin: SizeUnit = 0.2 cm,
                      var bottomMargin: SizeUnit = 1.7 cm,
                      var internalMargin: SizeUnit = 0 cm)
    extends Config

case class Text(var config: TextConfig) extends PageItem

case class TextConfig() extends Config

object Graph {

  def apply(graphItems: GraphItem*) = new Graph(graphItems.toVector)
  def apply(graphItems: Vector[GraphItem]) = new Graph(graphItems)

  def apply(axis: Vector[Axis], graphItems: GraphItem*) = new Graph(graphItems.toVector, axis = axis)
  def apply(axis: Vector[Axis], graphItems: Vector[GraphItem]) = new Graph(graphItems, axis = axis)

  def apply(name: String, graphItems: GraphItem*) = new Graph(graphItems.toVector, name)
  def apply(name: String, graphItems: Vector[GraphItem]) = new Graph(graphItems, name)

  def apply(name: String, axis: Vector[Axis], graphItems: GraphItem*) = new Graph(graphItems.toVector, name, axis)
  def apply(name: String, axis: Vector[Axis], graphItems: Vector[GraphItem]) = new Graph(graphItems, name, axis)

}
case class Graph(graphItems: Vector[GraphItem], name: String = "graph", axis: Vector[Axis] = Vector(XAxis(), YAxis()), var config: GraphConfig = new GraphConfig()) extends PageItem

case class GraphConfig(var border: GraphBorder = GraphBorder()) extends Config //axis:Vector[Axis]

case class GraphBorder(var leftMargin: SizeUnit = 1.7 cm,
                       var rightMargin: SizeUnit = 0.2 cm,
                       var topMargin: SizeUnit = 0.2 cm,
                       var bottomMargin: SizeUnit = 1.7 cm,
                       var aspectRatio: Option[Double] = None,
                       var width: SizeUnit = 0.5 pt)

object XAxis {

  def apply(name: String = "x",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxpos: Double = 1,
            axisPosition: Int = 0) =
    new Axis(name, label, min, max, log, mode, scale, minPos, maxpos, "horizontal", axisPosition)
}

object YAxis {

  def apply(name: String = "y",
            label: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None,
            log: Boolean = false,
            mode: AxisMode.Value = AxisMode.numeric,
            scale: Double = 1,
            minPos: Double = 0,
            maxpos: Double = 1,
            axisPosition: Int = 0) =
    new Axis(name, label, min, max, log, mode, scale, minPos, maxpos, "vertical", axisPosition)
}
case class Axis(var name: String,
                var label: String = "",
                var min: Option[Double] = None,
                var max: Option[Double] = None,
                var Log: Boolean = false,
                var mode: AxisMode.Value = AxisMode.numeric,
                var scale: Double = 1,
                var minPos: Double = 0,
                var maxPos: Double = 1,
                var direction: String = "horizontal",
                axisPosition: Int = 0,
                var config: AxisConfig = AxisConfig())

object AxisMode extends Enumeration {
  val numeric, datetime, labels = Value
}

case class AxisConfig(var autoRange: AutoRange.Value = AutoRange.nextTick,
                      var autoMirror: Boolean = false,
                      var reflect: Boolean = false,
                      var outerticks: Boolean = false,
                      var axisLine: LineStyle = LineStyle(width = 0.5),
                      var labelStyle: AxisLabelStyle = AxisLabelStyle(),
                      var tickLabelStyle: TickLabelStyle = TickLabelStyle(),
                      var majorTickStyle: MajorTickStyle = MajorTickStyle(),
                      var minorTickStyle: MinorTickStyle = MinorTickStyle(),
                      var majorGridLines: MajorGridLines = MajorGridLines(),
                      var minorGridLines: MinorGridLines = MinorGridLines())

object AutoRange extends Enumeration {
  val exact = Value("exact")
  val nextTick = Value("next-tick")
  val plus2percent = Value("+2%")
  val plus5percent = Value("+5%")
  val plus10percent = Value("+10%")
  val plus15percent = Value("+15%")

}

case class ArrowStyle(var arrowleft: Arrows.Value = Arrows.none, var arrowright: Arrows.Value = Arrows.none, var color: String = "black")

object Arrows extends Enumeration {
  val none, arrow, arrownarrow, arrowtriangle, arrowreverse, linearrow, linearrowreverse, bar, linecross, asterisk = Value
}

case class XYMainStyle(var markerType: MarkerType.Value = MarkerType.circle,
                       var size: Double = 3,
                       var color: String = "black",
                       var thinMarkers: Int = 1,
                       thinErrors: Int = 1,
                       var errorType: ErrorType.Value = ErrorType.bar,
                       var hide: Boolean = false)

object MarkerType extends Enumeration {
  val none, circle, diamond, square, cross, plus, star, dot, linevert, linehorz, linedown, lineup, lineright, lineleft = Value
}

object ErrorType extends Enumeration {
  val bar, barends, box, diamond, curve, barbox, bardiamand, barcurve, boxfill, diamondfill, curvefill, fillvert, fillhorz, linevert, linehorz, linevertbar, linehorzbar = Value
}

case class MarkerBorder(var color: String = "black",
                        var width: Double = 0.5,
                        var style: LineStyles.Value = LineStyles.Solid,
                        var hide: Boolean = false,
                        var colorMap: ColorMapType.Value = ColorMapType.grey,
                        var scale: Boolean = false)
case class MarkerFill(var color: String = "black", var hide: Boolean = false, var colorMap: ColorMapType.Value = ColorMapType.grey, var invertmap: Boolean = false)

case class ErrorBarLine(var color: String = "black",
                        var width: Double = 0.5,
                        var style: LineStyles.Value = LineStyles.Solid,
                        var transparency: Int = 0,
                        var hide: Boolean = false,
                        var endSize: Double = 1.0,
                        var hideHorz: Boolean = false,
                        var hideVert: Boolean = false)

object ColorMapType extends Enumeration {
  val grey = Value("grey")
  val traffic = Value("traffic-7-25-sync@60")
  val trafficWithNone = Value("traffic-7-25-sync@60_None@-10")
}

case class Fill(var fillTo: FillTo.Value = FillTo.bottom,
                var color: String = "grey",
                var style: FillStyle.Value = FillStyle.solid,
                var hide: Boolean = true,
                var transparency: Int = 0,
                var hideErrorFill: Boolean = false)

object FillTo extends Enumeration {
  val top = Value("top")
  val bottom = Value("bottom")
  val left = Value("left")
  val right = Value("right")
}

object FillStyle extends Enumeration {
  val solid = Value("solid")
  val horizontal = Value("horizontal")
  val vertical = Value("vertical")
  val cross = Value("cross")
  val forwarddiagonals = Value("forward diagonals")
  val backwarddiagonals = Value("backward diagonals")
  val percent94 = Value("94% dense")
  val percent88 = Value("88% dense")
  val percent63 = Value("63% dense")
  val percent50 = Value("50% dense")
  val percent37 = Value("37% dense")
  val percent12 = Value("12% dense")
  val percent6 = Value("6% dense")

}

case class LineStyle(var color: String = "black", var width: Double = 1, var style: LineStyles.Value = LineStyles.Solid, var hide: Boolean = false, var transparency: Int = 0)

case class AxisLabelStyle(var font: String = "Times New Roman",
                          var size: Double = 14,
                          var color: String = "black",
                          var italic: Boolean = false,
                          var bold: Boolean = false,
                          var underline: Boolean = false,
                          var atEdge: Boolean = false,
                          var rotate: Rotation.Value = Rotation.zero,
                          var labelOffset: Double = 0,
                          var position: LabelPosition.Value = LabelPosition.centre)
case class TickLabelStyle(var font: String = "Times New Roman",
                          var size: Double = 14,
                          var color: String = "black",
                          var italic: Boolean = false,
                          var bold: Boolean = false,
                          var underline: Boolean = false,
                          var rotate: Rotation.Value = Rotation.zero,
                          var tickOffset: Double = 0,
                          var format: TickLabelFormat.Value = TickLabelFormat.Auto,
                          var scale: Double = 1)

case class MajorTickStyle(var color: String = "black",
                          var width: Double = 0.5,
                          var style: LineStyles.Value = LineStyles.Solid,
                          var hide: Boolean = false,
                          var length: Double = 6,
                          var number: Int = 6,
                          var manualTicks: String = "")
case class MinorTickStyle(var color: String = "black",
                          var width: Double = 0.5,
                          var style: LineStyles.Value = LineStyles.Solid,
                          var hide: Boolean = false,
                          var length: Double = 3,
                          var number: Int = 6)

case class MajorGridLines(var color: String = "grey",
                          var width: Double = 0.5,
                          var style: LineStyles.Value = LineStyles.Dotted,
                          var transparency: Int = 0,
                          var hide: Boolean = true,
                          var onTop: Boolean = false)
case class MinorGridLines(var color: String = "lightgrey",
                          var width: Double = 0.5,
                          var style: LineStyles.Value = LineStyles.Dotted,
                          var transparency: Int = 0,
                          var hide: Boolean = true)

object LabelPosition extends Enumeration {
  val centre = Value("centre")
  val atMinimum = Value("at-minimum")
  val atMaximum = Value("at-maximum")
}

object Rotation extends Enumeration {
  val zero = Value("0")
  val neg45 = Value("-45")
  val neg90 = Value("-90")
  val neg135 = Value("-135")
  val neg180 = Value("-180")
  val pos45 = Value("45")
  val pos90 = Value("90")
  val pos135 = Value("135")
  val pos180 = Value("180")
}

object TickLabelFormat extends Enumeration {
  val Auto = Value("Auto")
  val Vg = Value("%Vg")
  val Ve = Value("%Ve")
  val VE = Value("%VE")
  val g = Value("%g")
  val e = Value("%e")
  val dot2f = Value("%.2f")
}

object LineStyles extends Enumeration {
  val Solid = Value("solid")
  val Dashed = Value("dashed")
  val Dotted = Value("dotted")
  val DashDot = Value("dash-dot")
  val DashDotDot = Value("dash-dot-dot")
  val DottedFine = Value("dotted-fine")
  val DashedFine = Value("dashed-fine")
  val DashedDotFine = Value("dash-dot-fine")
  val Dot1 = Value("dot1")
  val Dot2 = Value("dot2")
  val Dot3 = Value("dot3")
  val Dash1 = Value("dash1")
  val Dash2 = Value("dash2")
  val Dash3 = Value("dash3")
}
