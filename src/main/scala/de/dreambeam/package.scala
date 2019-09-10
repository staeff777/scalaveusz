package de.dreambeam

import de.dreambeam.veusz.components.{graph, graph3d, nonorthgraphs}
import de.dreambeam.veusz.components.graph.{Axis, Barchart, Boxplot, Contours, Function, Graph, Image, Vectorfield, XAxis, XY}
import de.dreambeam.veusz.components.graph3d.{Axis3D, Graph3D, Scene3D, XAxis3D, YAxis3D, ZAxis3D}
import de.dreambeam.veusz.components.nonorthgraphs.{NonOrthFunction, NonOrthPoint, PolarGraph}
import de.dreambeam.veusz.components.shapes.Ellipse
import de.dreambeam.veusz.format.{Centimeter, Inches, Millimeter, Percent, Point, PolarPositionOf0}
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

  val BarchartFillConfig = de.dreambeam.veusz.format.BarchartFillConfig
  val BarchartMode = de.dreambeam.veusz.format.BarchartMode
  val Positioning = de.dreambeam.veusz.format.Positioning
  val VectorfieldMode = de.dreambeam.veusz.format.VectorfieldMode
  val AxisMode = de.dreambeam.veusz.format.AxisMode
  val Surface3DMode = de.dreambeam.veusz.format.Surface3DMode
  val Function3DMode = de.dreambeam.veusz.format.Function3DMode


  val MarkerType = de.dreambeam.veusz.format.MarkerType
  val ErrorType = de.dreambeam.veusz.format.ErrorType
  val BarchartErrorType = de.dreambeam.veusz.format.BarchartErrorType
  val BoxplotMarkerType = de.dreambeam.veusz.format.BoxplotMarkerType
  val ArrowType = de.dreambeam.veusz.format.ArrowType
  val NonOrthFillType = de.dreambeam.veusz.format.NonOrthFillType

  val PolarDirection = de.dreambeam.veusz.format.PolarDirection
  val PolarPositionOf0 = de.dreambeam.veusz.format.PolarPositionOf0

  object Veusz {
    val Document = veusz.components.Document
    val DocumentItems = veusz.DocumentItems
    val PageItems = veusz.PageItems
    val GridItems = veusz.GridItems
    val GraphItems = veusz.GraphItems
    val PolarItems = veusz.PolarItems
    val Scene3DItems = veusz.Scene3DItems
    val Graph3DItems = veusz.Graph3DItems
  }

  object DocumentItems{
    val Page = veusz.components.Page
  }

  object PageItems {
    val Grid = veusz.components.Grid
    val Graph = components.graph.Graph
    val PolarGraph = components.nonorthgraphs.PolarGraph
    val TernaryGraph = components.nonorthgraphs.TernaryGraph
    val Scene3D = components.graph3d.Scene3D
    val Label = veusz.components.Label

    object Shapes{
      val ImageFile = veusz.components.shapes.ImageFile
      val Line = veusz.components.shapes.Line
      val Rectangle = veusz.components.shapes.Rectangle
      val Ellipse = veusz.components.shapes.Ellipse
      val Polygon= veusz.components.shapes.Polygon
    }
  }

  object GridItems {
    val Grid = veusz.components.Grid
    val Graph = graph.Graph
    val PolarGraph = nonorthgraphs.PolarGraph
    val Scene3D = graph3d.Scene3D

    val Colorbar = veusz.components.Colorbar
  }


  object GraphItems {
    val Label = veusz.components.Label
    val Colorbar = veusz.components.Colorbar
    val Shapes = PageItems.Shapes

    val XY = components.graph.XY
    val Barchart = veusz.components.graph.Barchart
    val Fit = veusz.components.graph.Fit
    val Function = veusz.components.graph.Function
     //val Fit = veusz.components.Fit
    val Boxplot = veusz.components.graph.Boxplot
    val Image = veusz.components.graph.Image
    val Contours = veusz.components.graph.Contours
    val VectorField = veusz.components.graph.Vectorfield
    val Key = veusz.components.Key

    val Axis = veusz.components.graph.Axis
    val XAxis = veusz.components.graph.XAxis
    val YAxis = veusz.components.graph.YAxis
  }

  val BoxplotData = data.BoxplotData

  object PolarItems {
    val Colorbar = veusz.components.Colorbar
    val NonOrthPoint = veusz.components.nonorthgraphs.NonOrthPoint
    val NonOrthFunction = veusz.components.nonorthgraphs.NonOrthFunction

  }

  val TernaryItems = PolarItems
  val NonOrthItems = PolarItems

  object Scene3DItems {
    val Graph3D = veusz.components.graph3d.Graph3D
  }

  object Graph3DItems {

    val Point3D = veusz.components.graph3d.Point3D
    val Function3D = veusz.components.graph3d.Function3D
    val Surface3D = veusz.components.graph3d.Surface3D
    val Volume3D = veusz.components.graph3d.Volume3D

    val Axis3D = veusz.components.graph3d.Axis3D
    val YAxis3D = veusz.components.graph3d.YAxis3D
    val XAxis3D = veusz.components.graph3d.XAxis3D
    val ZAxis3D = veusz.components.graph3d.ZAxis3D
  }
}
