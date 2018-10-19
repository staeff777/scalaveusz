package de.dreambeam.veusz.model

import SizeUnits._

/**
  * Created by Kaufmann on 22.06.2017.
  */
sealed trait GraphItem

case class XYDataEntry(
          data: Vector[Double] = Vector.empty,
          name: String = "",
          symErrors: Option[Vector[Double]] = None,
          negErrors: Option[Vector[Double]] = None,
          posErrors: Option[Vector[Double]] = None
)

object XYData {

  def fromDataTuples(xyDoubles: Vector[(Double, Double)],
                     xName: String = "x",
                     yName: String = "y",
                     scaleMarkers: XYDataEntry = XYDataEntry(),
                     colorMarkes: XYDataEntry = XYDataEntry(),
                     labels: Vector[String] = Vector.empty): XYData = {
    val x = new XYDataEntry(xyDoubles.map(_._1), xName)
    val y = new XYDataEntry(xyDoubles.map(_._2), yName)
    new XYData(x, y)
  }
}

sealed trait VeuszData

//check if Data is really required
case class XYData(x: XYDataEntry, y: XYDataEntry, scaleMarkers: XYDataEntry = XYDataEntry(), colorMarkes: XYDataEntry = XYDataEntry(), labels: Vector[String] = Vector.empty)
    extends VeuszData

case class XYZData(dataset: Map[(Double, Double), Double], name: String = "XYZData") extends VeuszData

object GraphItems {

  implicit def toXYDataEntry(v: Vector[Double]) = new XYDataEntry(v)

  object XyzImage {
    def apply(xyzData: XYZData)(implicit config: XYZConfig) = new XyzImage(xyzData, config = config)

    def apply(xyzData: XYZData, name: String)(implicit config: XYZConfig) = new XyzImage(xyzData, name = name, config = config)

    def apply(xyzData: XYZData, min: Option[Double], max: Option[Double])(implicit config: XYZConfig) = new XyzImage(xyzData, min = min, max = max, config = config)
  }

  case class XyzImage(data: XYZData,
                      name: String = "XYZ",
                      min: Option[Double] = None,
                      max: Option[Double] = None,
                      scaling: ColorScaling.Value = ColorScaling.linear,
                      keyText: String = "",
                      config: XYZConfig,
                      xAxis: Axis = XAxis(),
                      yAxis: Axis = YAxis())
      extends GraphItem

  case class XYZConfig(colorMap: String = "grey", invertColormap: Boolean = false, transparency: Int = 0, hide: Boolean = false, smooth: Boolean = true) extends Config

  object ColorScaling extends Enumeration {
    val linear = Value("linear")
    val sqrt = Value("sqrt")
    val log = Value("log")
    val squared = Value("squared")
  }

  object XY {
    def apply(xYData: XYData) = new XY(xYData)

    def apply(xYData: XYData, name: String) = new XY(xYData, name = name)
  }

  case class XY(xYData: XYData, name: String = "XY", keyText: String = "", var config: XYConfig = new XYConfig(), xAxis: String = "x", yAxis: String = "y") extends GraphItem

  case class XYConfig(mainStyle: XYMainStyle = XYMainStyle(),
                      lineStyle: LineStyle = LineStyle(),
                      markerBorder: MarkerBorder = MarkerBorder(),
                      markerFill: MarkerFill = MarkerFill(),
                      errorBarLine: ErrorBarLine = ErrorBarLine(),
                      fillBelow: Fill = Fill(),
                      fillAbove: Fill = Fill(fillTo = FillTo.top))
      extends Config

  case class BoxPlotData(values: Vector[XYDataEntry], labels: Vector[String], positions: Option[XYDataEntry] = None) extends VeuszData

  case class BoxPlot(data: BoxPlotData,
                     name: String = "boxplot",
                     whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
                     fillFraction: Double = 0.75,
                     config: BoxPlotConfig = BoxPlotConfig())
      extends GraphItem

  case class BoxPlotConfig(fill: SimpleFill = SimpleFill(color = "white"),
                           border: SimpleBorder = SimpleBorder(),
                           whiskerLine: LineStyle = LineStyle(),
                           markersFill: SimpleFill = SimpleFill(color = "white"),
                           markerBorder: SimpleBorder = SimpleBorder())
      extends Config

  case class SimpleBorder(var color: String = "black", var width: Double = 0.5, var style: LineStyles.Value = LineStyles.Solid, var hide: Boolean = false)

  object WhiskerMode extends Enumeration {
    val minmax = Value("min/max")
    val IQP15 = Value("1.5IQR")
    val stddev = Value("stddev")
    val percentile991 = Value("9/91 percentile")
    val percentile998 = Value("2/98 percentile")
  }

  /**
    * @param min double oder 'Auto'
    * @param max double oder 'Auto'
    */
  case class Function(function: String, name: String = "Func", key: String = "", min: Option[Double] = None, max: Option[Double] = None, config: FunctionConfig = FunctionConfig())
      extends GraphItem

  case class FunctionConfig(steps: Double = 50, plotLine: LineStyle = LineStyle()) extends Config

  case class Line(xPos: String, name: String = "Line", yPos: String, length: String, angle: String, config: LineConfig) extends GraphItem

  case class LineConfig(arrowStyle: ArrowStyle = ArrowStyle(), plotLine: LineStyle = LineStyle()) extends Config

  case class Polygon(xPositions: Vector[Double],
                     yPositions: Vector[Double],
                     name: String = "polygon",
                     positionMode: PositionMode.Value = PositionMode.relative,
                     xAxis: String = "x",
                     yAxis: String = "y",
                     config: PolygonConfig = PolygonConfig())
      extends GraphItem

  object PositionMode extends Enumeration {
    val relative = Value("relative")
    val axes = Value("axes")
  }

  case class PolygonConfig(lineStyle: LineStyle = LineStyle(), fill: SimpleFill = SimpleFill()) extends Config

  case class SimpleFill(var color: String = "grey", var style: FillStyle.Value = FillStyle.solid, hide: Boolean = true, transparency: Int = 0)

  object Label {

    def apply(label: String,
              xPosition: Double = 0.5,
              yPosition: Double = 0.5,
              positionMode: PositionMode.Value = PositionMode.relative,
              xAxis: String = "x",
              yAxis: String = "y",
              labelConfig: LabelConfig = LabelConfig()) =
      new Label(label, Vector(xPosition), Vector(yPosition), positionMode, xAxis, yAxis, LabelConfig())
  }

  case class Label(label: String, xPositions: Vector[Double], yPositions: Vector[Double], positionMode: PositionMode.Value, xAxis: String, yAxis: String, config: LabelConfig)
      extends GraphItem

  case class LabelConfig(var alignment: Alignment = Alignment(),
                         var textConfig: TextConfig = TextConfig(),
                         var background: LabelBackground = LabelBackground(),
                         var border: LabelBorder = LabelBorder())

  case class Alignment(var horizontal: HorizontalAlignment.Value = HorizontalAlignment.left,
                       var vertical: VerticalAlignment.Value = VerticalAlignment.bottom,
                       var angle: Double = 0,
                       var margin: SizeUnit = 4 pt,
                       var clip: Boolean = false)

  case class TextConfig(var font: String = "Times New Roman",
                        var size: SizeUnit = 14 pt,
                        var color: String = "black",
                        var italic: Boolean = false,
                        var bold: Boolean = false,
                        var underline: Boolean = false,
                        var hide: Boolean = false)

  case class LabelBackground(var color: String = "White", var style: FillStyle.Value = FillStyle.solid, var hide: Boolean = false, var transparency: Int = 0)

  case class LabelBorder(var color: String = "black", var width: Double = 0.5, var style: LineStyles.Value = LineStyles.Solid, var transparency: Int = 0, var hide: Boolean = false)

  object HorizontalAlignment extends Enumeration {
    val left = Value("left")
    val center = Value("center")
    val right = Value("right")
  }

  object VerticalAlignment extends Enumeration {
    val top = Value("top")
    val center = Value("center")
    val bottom = Value("bottom")
  }

}
