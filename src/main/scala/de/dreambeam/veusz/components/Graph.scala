package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.format._

object Graph {
  val defaultName = "graph"

  def apply(children: GraphItem*): Graph = Graph(children = Some(children.toVector))
  def apply(children: Vector[GraphItem]): Graph = Graph(children = Some(children))

  def apply(name: String, children: GraphItem*): Graph = Graph(name, children = Some(children.toVector))
  def apply(name: String, children: Vector[GraphItem]): Graph = Graph(name, children = Some(children))

  def apply(axis: Vector[Axis], children: GraphItem*): Graph = Graph(children = Some(children.toVector), axis = axis)
  def apply(axis: Vector[Axis], children: Vector[GraphItem]): Graph = Graph(children = Some(children), axis = axis)

  def apply(name: String, axis: Vector[Axis], children: GraphItem*): Graph = Graph(name, axis, Some(children.toVector))
  def apply(name: String, axis: Vector[Axis], children: Vector[GraphItem]): Graph = Graph(name, axis, Some(children))

  /* Children */

  val $Axis = Axis
  val $XY = XY
  val $Key = Key
  val $Barchart = Barchart
  //lazy val $Fit = Fit
  val $Function = Function
  val $Boxplot = Boxplot
  val $Image = Image
  val $Contour = Contour
  val $Vectorfield = Vectorfield
  val $Label = Label
  val $Colorbar = Colorbar
  val $Covariance = Covariance
  val $Shape = Shape
}

case class Graph (var name: String = Graph.defaultName,
                  axis: Vector[Axis] = Vector(XAxis(), YAxis()),
                  children: Option[Vector[GraphItem]] = None)
  extends PageItem
    with GridItem
    with Configurable
    with Executable
    with Parent
{
  val group = "graph"
  var config: GraphConfig = GraphConfig()
}

case class GraphConfig(var notes: String = "",
                       var main: GraphMainConfig = GraphMainConfig(),
                       var background: BackgroundConfig = BackgroundConfig(),
                       var border: BorderConfig = BorderConfig())