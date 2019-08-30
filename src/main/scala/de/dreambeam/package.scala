package de.dreambeam

import de.dreambeam.veusz.components.ZAxis3D
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


  object Veusz {
    val Document = veusz.components.Document
    val DocumentItems = veusz.DocumentItems
    val PageItems = veusz.PageItems
    val GridItems = veusz.GridItems
    val GraphItems = veusz.GraphItems
    val PolarItems = veusz.PolarItems
    val Scene3DItems = veusz.Scene3DItems
    val graph3DItems = veusz.Graph3DItems
  }

  object DocumentItems{
    val Page = veusz.components.Page
  }

  object PageItems {
    val Grid = veusz.components.Grid
    val Graph = veusz.components.Graph
    val PolarGraph = veusz.components.PolarGraph
    val Scene3D = veusz.components.Scene3D


    val Label = veusz.components.Label

    object Shapes{
      val ImageFile = veusz.components.ImageFile
      val Line = veusz.components.Line
      val Rectangle = veusz.components.Rectangle
      val Ellipse = veusz.components.Ellipse
      val Polygon= veusz.components.Polygon
    }
  }

  object GridItems {
    val Grid = veusz.components.Grid
    val Graph = veusz.components.Graph
    val PolarGraph = veusz.components.PolarGraph
    val Scene3D = veusz.components.Scene3D

    val Colorbar = veusz.components.Colorbar
  }


  object GraphItems {
    val Label = veusz.components.Label
    val Colorbar = veusz.components.Colorbar
    val Shapes = PageItems.Shapes

    val XY = veusz.components.XY
    val BarChart = veusz.components.Barchart
    val Function = veusz.components.Function
    //TODO val Fit = veusz.components.Fit
    val BoxPlot = veusz.components.Boxplot
    val Image = veusz.components.Image
    val Contours = veusz.components.Contours
    val VectorField = veusz.components.Vectorfield
    val Key = veusz.components.Key

    val Axis = veusz.components.Axis
    val XAxis = veusz.components.XAxis
    val YAxis = veusz.components.XAxis
  }

  object PolarItems {
    val Colorbar = veusz.components.Colorbar
    val NonOrthPoint = veusz.components.NonOrthPoint
    //TODO val NonOrthFunction = veusz.components.NonOrthFunction
  }

  object Scene3DItems {
    val Graph3D = veusz.components.Graph3D
  }

  object Graph3DItems {

    //Todo Graph3D items

    val Axis3D = veusz.components.Axis3D
    val YAxis3D = veusz.components.YAxis3D
    val XAxis3D = veusz.components.XAxis3D
    val ZAxis3D = veusz.components.ZAxis3D
  }
}
