package de.dreambeam

import de.dreambeam.veusz.format.{Centimeter, Inches, Millimeter, Percent, Point}
import de.dreambeam.veusz.util._

package object veusz {
  import scala.language.implicitConversions
  implicit class DoubleWithUnits(v: Double) {
    def cm() = Centimeter(v)
    def pt() = Point(v)
    def mm() = Millimeter(v)
    def in() = Inches(v)
    def percent() = Percent(v)

  }

  //Convert Double to Point on Default
  implicit def double2Point(x: Double) = Point(x)
  val Colors = de.dreambeam.veusz.format.Colors
  val ColorMaps = de.dreambeam.veusz.format.ColorMaps

  // provide quick access to enums and prefined values
  val FillStyle = de.dreambeam.veusz.format.FillStyle
  val FillTo = de.dreambeam.veusz.format.FillTo

  val LineSteps = de.dreambeam.veusz.format.LineSteps
  val LineStyle = de.dreambeam.veusz.format.LineStyle

  val BarchartMode = de.dreambeam.veusz.format.BarchartMode
  val Positioning = de.dreambeam.veusz.format.Positioning
  val VectorfieldMode = de.dreambeam.veusz.format.VectorfieldMode
  val AxisMode = de.dreambeam.veusz.format.AxisMode

  val MarkerType = de.dreambeam.veusz.format.MarkerType
  val ErrorType = de.dreambeam.veusz.format.ErrorType
  val BarchartErrorType = de.dreambeam.veusz.format.BarchartErrorType
  val BoxplotMarkerType = de.dreambeam.veusz.format.BoxplotMarkerType
  val ArrowType = de.dreambeam.veusz.format.ArrowType
  val NonOrthFillType = de.dreambeam.veusz.format.NonOrthFillType

  //todo
  object GraphItems {

  }


}
