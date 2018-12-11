package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.format._

object Graph3D {
  val defaultName = "graph3d"

  def apply: Graph3D = Graph3D(defaultName)

  def apply(children: Vector[Graph3DItem]): Graph3D = Graph3D(children = Some(children))

  def apply(name: String, children: Vector[Graph3DItem]): Graph3D = Graph3D(name, Some(children))

  def apply(name: String, children: Graph3DItem*): Graph3D = Graph3D(name, Some(children.toVector))

  def apply(children: Graph3DItem*): Graph3D = Graph3D(children = Some(children.toVector))

  /*
  def apply(name: String = defaultName,
            children: Vector[Graph3DItem],
            xSize: Int = 1,
            ySize: Int = 1,
            zSize: Int = 1,
            xPosition: Int = 0,
            yPosition: Int = 0,
            zPosition: Int = 0): Graph3D =
    Graph3D(name, Some(children), xSize, ySize, zSize, xPosition, yPosition, zPosition)
  */

  /* Children */

  val $Axis3D = Axis3D


}

case class Graph3D private (var name: String = Graph3D.defaultName,
                            children: Option[Vector[Graph3DItem]] = None,
                            xSize: Int = 1,
                            ySize: Int = 1,
                            zSize: Int = 1,
                            xPosition: Int = 0,
                            yPosition: Int = 0,
                            zPosition: Int = 0)
  extends Scene3DItem
    with Configurable
    with Executable
    with Parent
{
  val group = "graph3d"

  var config: Graph3DConfig = Graph3DConfig()
}

case class Graph3DConfig(var notes: String = "",
                         main: Graph3DMainConfig = Graph3DMainConfig(),
                         border: Graph3DBorderConfig = Graph3DBorderConfig(),
                         back: Graph3DBackConfig = Graph3DBackConfig())