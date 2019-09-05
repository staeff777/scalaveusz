package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz._
import de.dreambeam.veusz.components.{Colorbar, Key, Label}
import de.dreambeam.veusz.format._

object Graph {
  val defaultName = "graph"

  def apply(children: GraphItem*): Graph = Graph(children = children.toVector)

  def apply(name: String, children: GraphItem*): Graph = Graph(name = name, children = children.toVector)

  def apply(axis: Vector[Axis], children: GraphItem*): Graph =  Graph(children = children.toVector, axis = axis)

  def apply(name: String, axis: Vector[Axis], children: GraphItem*): Graph = new Graph(children.toVector, axis, name)

  def apply(children: Vector[GraphItem], name: String = Graph.defaultName, axis: Vector[Axis] = Vector(XAxis(), YAxis())): Graph = new Graph(children, axis, name)

}

case class Graph(children: Vector[GraphItem], axis: Vector[Axis], var name: String) extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "graph"
  var config: GraphConfig = GraphConfig()
}

case class GraphConfig(var notes: String = "",
                       var main: GraphMainConfig = GraphMainConfig(),
                       var background: BackgroundConfig = BackgroundConfig(),
                       var border: BorderConfig = BorderConfig())
