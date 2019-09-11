package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz._
import de.dreambeam.veusz.components.nonorthgraphs.PolarGraphConfig
import de.dreambeam.veusz.components.{Colorbar, Key, Label}
import de.dreambeam.veusz.format._

object Graph {

  def apply(children: GraphItem*): Graph = Graph(children = children.toVector)

  def apply(name: String, children: GraphItem*): Graph = Graph(name = name, children = children.toVector)

  def apply(axis: Vector[Axis], children: GraphItem*): Graph = Graph(axis = axis, children = children.toVector)

  def apply(name: String, axis: Vector[Axis], children: GraphItem*): Graph = new Graph(axis, name, children.toVector, GraphConfig())

  def apply(children: Vector[GraphItem], name: String = "graph", axis: Vector[Axis] = Vector(XAxis(), YAxis())): Graph = new Graph(axis, name, children, GraphConfig())

}

case class Graph(axis: Vector[Axis],
                 var name: String,
                 var children: Vector[GraphItem],
                 var config: GraphConfig)
    extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "graph"
}

case class GraphConfig(var notes: String = "",
                       var main: GraphMainConfig = GraphMainConfig(),
                       var background: BackgroundConfig = BackgroundConfig(),
                       var border: BorderConfig = BorderConfig())
