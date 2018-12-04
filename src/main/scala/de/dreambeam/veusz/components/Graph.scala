package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, GridItem, PageItem}
import de.dreambeam.veusz.format._

object Graph {
  val defaultName = "graph"

  def apply(children: GraphItem*) = new Graph(children = Some(children.toVector))
  def apply(children: Vector[GraphItem]) = new Graph(children = Some(children))

  def apply(name: String, children: GraphItem*) = new Graph(name, children = Some(children.toVector))
  def apply(name: String, children: Vector[GraphItem]) = new Graph(name, children = Some(children))

  def apply(axis: Vector[Axis], children: GraphItem*) = new Graph(children = Some(children.toVector), axis = axis)
  def apply(axis: Vector[Axis], children: Vector[GraphItem]) = new Graph(children = Some(children), axis = axis)

  def apply(name: String, axis: Vector[Axis], children: GraphItem*) = new Graph(name, axis, Some(children.toVector))
  def apply(name: String, axis: Vector[Axis], children: Vector[GraphItem]) = new Graph(name, axis, Some(children))

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
  //lazy val $Vectorfield = Vectorfield
  val $Label = Label
  val $Colorbar = Colorbar
  //lazy val $Covariance = Covariance
  val $Shape = Shape
}

class Graph private (val name: String = Graph.defaultName,
                     val axis: Vector[Axis] = Vector(XAxis(), YAxis()),
                     var children: Option[Vector[GraphItem]] = None)
  extends PageItem
    with GridItem
    with Configurable
    with Executable
{
  val group = "graph"

  object config {
    /* Properties */
    var notes: String = _

    val main = GraphMainConfig()
    val background = BackgroundConfig()
    val border = BorderConfig()
  }
}