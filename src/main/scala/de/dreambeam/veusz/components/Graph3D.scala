package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Scene3DItem, Graph3DItem, Executable}
import de.dreambeam.veusz.format._

object Graph3D {
  val defaultName = "graph3d"

  def apply = new Graph3D
  def apply(children: Vector[Graph3DItem]) = new Graph3D(children = Some(children))
  def apply(name: String, children: Vector[Graph3DItem]) = new Graph3D(name, Some(children))
  def apply(name: String, children: Graph3DItem*) = new Graph3D(name, Some(children.toVector))
  def apply(children: Graph3DItem*) = new Graph3D(children=Some(children.toVector))
  def apply(name: String = defaultName,
            children: Vector[Graph3DItem],
            xSize: Int = 1,
            ySize: Int = 1,
            zSize: Int = 1,
            xPosition: Int = 0,
            yPosition: Int = 0,
            zPosition: Int = 0) =
    new Graph3D(name, Some(children), xSize, ySize, zSize, xPosition, yPosition)

  /* Children */

  val $Axis3D = Axis3D
}

class Graph3D private (val name: String = Graph3D.defaultName,
                       var children: Option[Vector[Graph3DItem]] = None,
                       var xSize: Int = 1,
                       var ySize: Int = 1,
                       var zSize: Int = 1,
                       var xPosition: Int = 0,
                       var yPosition: Int = 0,
                       var zPosition: Int = 0)
  extends Scene3DItem
    with Executable
{
  val group = "graph3d"

  object config {
    /* Properties */
    var notes: String = _

    val main = Graph3DMainConfig()
    val border = Graph3DBorderConfig()
    val back = Graph3DBackConfig()

  }


}