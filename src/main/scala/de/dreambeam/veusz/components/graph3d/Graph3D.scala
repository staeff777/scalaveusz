package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz._
import de.dreambeam.veusz.format._

object Graph3D {
  val defaultName = "graph3d"



  def apply(name: String, children: Vector[Graph3DItem]): Graph3D = Graph3D(name,children)

  def apply(name: String, children: Graph3DItem*): Graph3D = Graph3D(name, children.toVector)

  def apply(children: Graph3DItem*): Graph3D = Graph3D(children = children.toVector)


  def apply(children: Vector[Graph3DItem],
            axis: Vector[Axis3D] = Vector(XAxis3D(), YAxis3D(), ZAxis3D()),
            xSize: Int = 1,
            ySize: Int = 1,
            zSize: Int = 1,
            xPosition: Int = 0,
            yPosition: Int = 0,
            zPosition: Int = 0,
            name: String = defaultName): Graph3D =
    new Graph3D(name, children, axis, xSize, ySize, zSize, xPosition, yPosition, zPosition)


}

case class Graph3D private (var name: String,
                            children: Vector[Graph3DItem],
                            axis: Vector[Axis3D],
                            xSize: Int,
                            ySize: Int,
                            zSize: Int,
                            xPosition: Int,
                            yPosition: Int,
                            zPosition: Int)
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