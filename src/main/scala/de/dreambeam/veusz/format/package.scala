package de.dreambeam.veusz.format

import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._

object AutoRange extends Enumeration {
  val Exact = Value("exact")
  val NextTick = Value("next-tick")
  val Plus2percent = Value("+2%")
  val Plus5percent = Value("+5%")
  val Plus10percent = Value("+10%")
  val Plus15percent = Value("+15%")
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

object LabelPosition extends Enumeration {
  val Centre = Value("centre")
  val AtMinimum = Value("at-minimum")
  val AtMaximum = Value("at-maximum")
}

object RenderMethod extends Enumeration {
  val Fast = Value("painters")
  val Accurate = Value("bsp")
}

object WhiskerMode extends Enumeration {
  val minmax = Value("min/max")
  val IQP15 = Value("1.5IQR")
  val stddev = Value("stddev")
  val percentile991 = Value("9/91 percentile")
  val percentile998 = Value("2/98 percentile")
}

object Direction extends Enumeration {
  val Vertical = Value("vertical")
  val Horizontal = Value("horizontal")
}

object Scaling extends Enumeration {
  val Sqrt = Value("sqrt")
  val Linear = Value("linear")
  val Log = Value("log")
  val Squared = Value("squared")
}

case class GraphMainConfig(
                    var hide: Boolean = false,
                    var leftMargin: SizeUnit = 1.7 cm,
                    var rightMargin: SizeUnit = 0.2 cm,
                    var topMargin: SizeUnit = 0.2 cm,
                    var bottomMargin: SizeUnit = 1.7 cm
                    )

case class BackgroundConfig(
                            var color: String = Colors.Background,
                            var style: FillStyle.Value = FillStyle.Solid,
                            var hide: Boolean = false,
                            var transparency: Int = 0
                            )

case class BorderConfig(
                        var color: String = Colors.Foreground,
                        var width: SizeUnit = 0.5 pt,
                        var style: LineStyle.Value = LineStyle.Solid,
                        var transparency: Int = 0,
                        var hide: Boolean = false
                        )

case class AxisMainConfig(
                         var hide: Boolean = false,
                         var autoRange: AutoRange.Value = AutoRange.NextTick,
                         var autoMirror: Boolean = true,
                         var reflect: Boolean = false,
                         var outerTicks: Boolean = false
                         )

case class LineConfig(
                         var color: String = Colors.Foreground,
                         var width: SizeUnit = 0.5 pt,
                         var style: LineStyle.Value = LineStyle.Solid,
                         var transparency: Int = 0,
                         var hide: Boolean = false
                         )

case class LabelConfig(
                        var font: String = "Times New Roman",
                        var size: SizeUnit = 14 pt,
                        var color: String = Colors.Foreground,
                        var italic: Boolean = false,
                        var bold: Boolean = false,
                        var underline: Boolean = false,
                        var hide: Boolean = false,
                        var atEdge: Boolean = false,
                        var rotate: Rotation.Value = Rotation.Zero,
                        var offset: SizeUnit = 0 pt,
                        var position: LabelPosition.Value = LabelPosition.Centre
                          )

case class TickLabelsConfig(
                               var font: String = "Times New Roman",
                               var size: SizeUnit = 14 pt,
                               var color: String = Colors.Foreground,
                               var italic: Boolean = false,
                               var bold: Boolean = false,
                               var underline: Boolean = false,
                               var hide: Boolean = false,
                               var rotate: Rotation.Value = Rotation.Zero,
                               var format: TickLabelFormat.Value = TickLabelFormat.Auto,
                               var scale: Double = 1.0,
                               var offset: SizeUnit = 0 pt
                               )

case class MajorTicksConfig(
                               var color: String = Colors.Foreground,
                               var width: SizeUnit = 0.5 pt,
                               var style: LineStyle.Value = LineStyle.Solid,
                               var transparency: Int = 0,
                               var hide: Boolean = false,
                               var length: SizeUnit = 6 pt,
                               var number: Int = 6,
                               var manualTicks: String = ""
                               )

case class MinorTicksConfig(
                               var color: String = Colors.Foreground,
                               var width: SizeUnit = 0.5 pt,
                               var style: LineStyle.Value = LineStyle.Solid,
                               var transparency: Int = 0,
                               var hide: Boolean = false,
                               var length: SizeUnit = 3 pt,
                               var number: Int = 20
                               )

case class MajorGridLinesConfig(
                                    var color: String = Colors.Grey,
                                    var width: SizeUnit = 0.5 pt,
                                    var style: LineStyle.Value = LineStyle.Dotted,
                                    var transparency: Int = 0,
                                    var hide: Boolean = true,
                                    var onTop: Boolean = false
                                    )

case class MinorGridLinesConfig(
                                   var color: String = Colors.LightGrey,
                                   var width: SizeUnit = 0.5 pt,
                                   var style: LineStyle.Value = LineStyle.Dotted,
                                   var transparency: Int = 0,
                                   var hide: Boolean = true
                                   )

case class XYMainConfig(
                       var markerType: MarkerType.Value = MarkerType.Circle,
                       var markerSize: SizeUnit = 3 pt,
                       var color: String = Colors.Auto,
                       var thinMarkers: Int = 1,
                       var thinErrors: Int = 1,
                       var hide: Boolean = false,
                       var errorStyle: ErrorType.Value = ErrorType.Bar
                       )

case class XYPlotLineConfig(
                           var steps: LineSteps.Value = LineSteps.Off,
                           var bezierJoin: Boolean = false,
                           var color: String = Colors.Auto,
                           var width: SizeUnit = 0.5 pt,
                           var style: LineStyle.Value = LineStyle.Solid,
                           var transparency: Int = 0,
                           var hide: Boolean = false
                           )

case class XYMarkerBorderConfig(
                               var color: String = Colors.Foreground,
                               var width: SizeUnit = 0.5 pt,
                               var style: LineStyle.Value = LineStyle.Solid,
                               var transparency: Int = 0,
                               var scale: Boolean = true,
                               var hide: Boolean = false
                               )

case class XYMarkerFillConfig(
                             var color: String = Colors.Auto,
                             var style: FillStyle.Value = FillStyle.Solid,
                             var transparency: Int = 0,
                             var hide: Boolean = false,
                             var colorMap: String  = ColorMaps.Grey,
                             var invertMap: Boolean = false
                             )

case class XYErrorBarLineConfig(
                               var color: String = Colors.Auto,
                               var width: SizeUnit = 0.5 pt,
                               var style: LineStyle.Value = LineStyle.Solid,
                               var transparency: Int = 0,
                               var hide: Boolean = false,
                               var endSize: Double = 1.0,
                               var hideHorz: Boolean = false,
                               var hideVert: Boolean = false
                               )

case class XYFillConfig(var fillTo: FillTo.Value,
                        var color: String = Colors.Grey,
                        var style: FillStyle.Value = FillStyle.Solid,
                        var hide: Boolean = true,
                        var transparency: Int = 0,
                        var hideError: Boolean = false)

case class XYLabelConfig(
                        var horzPosition: HorizontalPosition.Value = HorizontalPosition.Right,
                        var vertPosition: VerticalPosition.Value = VerticalPosition.Centre,
                        var angle: Double = 0.0,
                        var font: String = "Times New Roman",
                        var size: SizeUnit = 14 pt,
                        var color: String = Colors.Foreground,
                        var italic: Boolean = false,
                        var bold: Boolean = false,
                        var underline: Boolean = false,
                        var hide: Boolean = false
                        )

case class ColorConfig(var min:Double = 0.0, var max:Double = 1.0, var scaling: Scaling.Value = Scaling.Linear)

case class FunctionMainConfig(
                             var steps: Int = 50,
                             var hide: Boolean = false
                             )

case class FillConfig(
                      var color: String = Colors.Background,
                      var style: FillStyle.Value = FillStyle.Solid,
                      var hide: Boolean = false,
                      var transparency: Int = 0
                     )

case class BarchartMainConfig(
                              var hide: Boolean = false,
                              var barFill: Double = 0.75,
                              var groupFill: Double = 0.9,
                              var errorStyle: ErrorType.Value = ErrorType.Bar
                              )

case class BarchartFillConfig(
                        var fillStyles: Vector[(FillStyle.Value, String, Boolean)] = Vector((FillStyle.Solid, Colors.Grey, false)),
                        )

case class BarchartLineConfig(
                        var lineStyles: Vector[(LineStyle.Value, SizeUnit, String, Boolean)] = Vector((LineStyle.Solid, 0.5 pt, Colors.Black, false))
                        )

case class BarchartErrorBarLineConfig(
                                      var color: String = Colors.Foreground,
                                      var width: SizeUnit = 0.5 pt,
                                      var style: LineStyle.Value = LineStyle.Solid,
                                      var transparency: Int = 0,
                                      var hide: Boolean = false,
                                      var endSize: Double = 1.0,
                                      var hideHorz: Boolean = false,
                                      var hideVert: Boolean = false
                                      )

case class BoxplotMainConfig(
                            var hide: Boolean = false,
                            var fillFraction: Double = 0.75,
                            var outliers: BoxplotMarkerType.Value = BoxplotMarkerType.Circle,
                            var mean: BoxplotMarkerType.Value = BoxplotMarkerType.LineCross,
                            var markersSize: SizeUnit = 3 pt
                            )

case class BoxplotFillConfig(
                         var color: String = Colors.Background,
                         var style: FillStyle.Value = FillStyle.Solid,
                         var hide: Boolean = false,
                         var transparency: Int = 0,
                         var advanced: AdvancedFillStyleConfig = null
                         )

case class AdvancedFillStyleConfig(
                                  var lineWidth: SizeUnit = 0.5 pt,
                                  var lineStyle: LineStyle.Value = LineStyle.Solid,
                                  var spacing: SizeUnit = 5 pt,
                                  var backColor: String = Colors.Background,
                                  var backTrans: Int = 0,
                                  var backHide: Boolean = true
                                  )

// 3D Configs

case class Scene3DMainConfig(
                             var hide: Boolean = false,
                             var leftMargin: SizeUnit = 1.7 cm,
                             var rightMargin: SizeUnit = 0.2 cm,
                             var topMargin: SizeUnit = 0.2 cm,
                             var bottomMargin: SizeUnit = 1.7 cm
                             )

case class Scene3DLightingConfig(
                                 var enable: Boolean,
                                 var color: String,
                                 var intensity: Int = 100,
                                 var xPosition: Int,
                                 var yPosition: Int,
                                 var zPosition: Int
                                 )

case class Graph3DMainConfig(
                      var hide: Boolean = false
                      )

case class Graph3DBorderConfig(
                              var color: String = Colors.Foreground,
                              var width: Double = 1.0,
                              var style: LineStyle.Value = LineStyle.Solid,
                              var transparency: Int = 0,
                              var reflectivity: Int = 0,
                              var hide: Boolean = false
                              )

case class Graph3DBackConfig(
                            var color: String = Colors.White,
                            var transparency: Int = 0,
                            var reflectivity: Int = 0,
                            var hide: Boolean = true
                            )

case class Axis3DMainConfig(
                           var hide: Boolean = false,
                           var autoRange: AutoRange.Value = AutoRange.NextTick,
                           var autoMirror: Boolean = true
                           )

case class Axis3DLineConfig(
                           var color: String = Colors.Foreground,
                           var width: Double = 1.0,
                           var style: LineStyle.Value = LineStyle.Solid,
                           var transparency: Int = 0,
                           var reflectivity: Int = 0,
                           var hide: Boolean = false
                           )

case class Axis3DLabelConfig(
                            var font: String = "Times New Roman",
                            var size: SizeUnit = 14 pt,
                            var color: String = Colors.Foreground,
                            var italic: Boolean = false,
                            var bold: Boolean = false,
                            var underline: Boolean = false,
                            var hide: Boolean = false,
                            var position: LabelPosition.Value = LabelPosition.Centre
                            )

case class Axis3DTickLabelsConfig(
                                 var font: String = "Times New Roman",
                                 var size: SizeUnit = 14 pt,
                                 var color: String = Colors.Foreground,
                                 var italic: Boolean = false,
                                 var bold: Boolean = false,
                                 var underline: Boolean = false,
                                 var hide: Boolean = false,
                                 var format: TickLabelFormat.Value = TickLabelFormat.Auto,
                                 var scale: Double = 1.0
                                 )

case class Axis3DMajorTicksConfig(
                                 var color: String = Colors.Grey,
                                 var width: Double = 1.0,
                                 var style: LineStyle.Value = LineStyle.Solid,
                                 var transparency: Int = 0,
                                 var reflectivity: Int = 0,
                                 var hide: Boolean = false,
                                 var length: Double = 20.0,
                                 var number: Int = 6,
                                 var manualTicks: String = ""
                                 )

case class Axis3DMinorTicksConfig(
                                   var color: String = Colors.Grey,
                                   var width: Double = 1.0,
                                   var style: LineStyle.Value = LineStyle.Solid,
                                   var transparency: Int = 0,
                                   var reflectivity: Int = 0,
                                   var hide: Boolean = false,
                                   var length: Double = 20.0,
                                   var number: Int = 6,
                                 )

case class Axis3DMajorGridLinesConfig(
                                      var color: String = Colors.Grey,
                                      var width: Double = 1.0,
                                      var style: LineStyle.Value = LineStyle.Solid,
                                      var transparency: Int = 0,
                                      var reflectivity: Int = 0,
                                      var hide: Boolean = true,
                                      )

case class Axis3DMinorGridLinesConfig(
                                      var color: String = Colors.LightGrey,
                                      var width: Double = 1.0,
                                      var style: LineStyle.Value = LineStyle.Solid,
                                      var transparency: Int = 0,
                                      var reflectivity: Int = 0,
                                      var hide: Boolean = true,
                                      )

case class KeyMainConfig(var hide: Boolean = false,
                         var horizontalPosition: HorizontalPosition.Value = HorizontalPosition.Right,
                         var verticalPosition: VerticalPosition.Value = VerticalPosition.Top,
                         var keyLength: SizeUnit = 8 percent,
                         var keyAlignment: KeyAlignment.Value = KeyAlignment.top,
                         var horizontalManual: Option[Double] = None,
                         var verticalManual: Option[Double] = None,
                         var marginSize: Int = 1,
                         var columns: Int = 1,
                         var swapSymbol: Boolean = false)

case class TextConfig(
                         var font: String = "Times New Roman",
                         var size: SizeUnit = 14 pt,
                         var color: String = "black",
                         var italic: Boolean = false,
                         var bold: Boolean = false,
                         var underline: Boolean = false,
                         var hide: Boolean = false)

case class LabelMainConfig(
                          var hide: Boolean = false,
                          var horizontal: HorizontalPosition.Value = HorizontalPosition.Left,
                          var vertical: VerticalPosition.Value = VerticalPosition.Bottom,
                          var angle: Double = 1.0,
                          var margin: SizeUnit = 4 pt,
                          var clip: Boolean = false
                          )

case class ColorBarMainConfig(
                             var hide: Boolean = false,
                             var autoRange: AutoRange.Value = AutoRange.NextTick,
                             var autoMirror: Boolean = true,
                             var reflect: Boolean = false,
                             var outerTicks: Boolean = false,
                             var horzPosition: HorizontalPosition.Value = HorizontalPosition.Right,
                             var vertPosition: VerticalPosition.Value = VerticalPosition.Bottom,
                             var width: Option[SizeUnit] = None,
                             var height: Option[SizeUnit] = None,
                             var horzManual: Option[Double] = None,
                             var vertManual: Option[Double] = None
                             )

case class ImageFileMainConfig(
                              var preserveAspect: Boolean = true,
                              var hide: Boolean = false,
                              var clip: Boolean = false
                              )

case class RectangleMainConfig(
                              var hide: Boolean = false,
                              var clip: Boolean = false,
                              var roundingCorners: Int = 0
                              )

case class EllipseMainConfig(
                            var hide: Boolean = false,
                            var clip: Boolean = false
                            )

case class LineMainConfig(
                          var arrowLeft: ArrowType.Value = ArrowType.None,
                          var arrowRight: ArrowType.Value = ArrowType.None,
                          var arrowSize: SizeUnit = 5 pt,
                          var clip: Boolean = false,
                          var hide: Boolean = false
                         )

case class ContourLabelsConfig(
                               var font: String = "MS Shell Dlg 2",
                               var size: SizeUnit = 14 pt,
                               var color: String = Colors.Foreground,
                               var italic: Boolean = false,
                               var bold: Boolean = false,
                               var underline: Boolean = false,
                               var hide: Boolean = false,
                               var format: TickLabelFormat.Value = TickLabelFormat.Vg,
                               var scale: Double = 1.0,
                               var rotate: Boolean = true
                              )

case class ContourLinesConfig(
                             var lineStyles: Vector[(LineStyle.Value, SizeUnit, String, Boolean)] = Vector((LineStyle.Solid, 0.5 pt, Colors.Black, false)),
                             var hide: Boolean = false
                             )

case class ContourFillConfig(
                             var fillStyles: Vector[(FillStyle.Value, String, Boolean)] = Vector((FillStyle.Solid, Colors.Black, false)),
                             var hide: Boolean = false
                             )

case class ContourSubLinesConfig(
                                var lineStyle: Vector[(LineStyle.Value, SizeUnit, String, Boolean)] = Vector((LineStyle.Solid, 0.5 pt, Colors.Black, false)),
                                var levels: Int = 5,
                                var hide: Boolean = true
                                )

case class VectorfieldMainConfig(
                                var baseLength: SizeUnit = 10 pt,
                                var arrowSize: SizeUnit = 3 pt,
                                var scaleArrow: Boolean = true,
                                var arrowFront: ArrowType.Value = ArrowType.None,
                                var arrowBack: ArrowType.Value = ArrowType.None,
                                var hide: Boolean = false
                                )

case class ContourEllipseLineConfig(
                                   var color: String = Colors.Auto,
                                   var width: SizeUnit = 0.5 pt,
                                   var style: LineStyle.Value = LineStyle.Solid,
                                   var transparency: Int = 0,
                                   var hide: Boolean = false,
                                   var steps: Int = 25
                                   )




