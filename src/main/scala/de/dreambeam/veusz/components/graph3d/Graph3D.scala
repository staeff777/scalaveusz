package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz._
import de.dreambeam.veusz.format._

object Graph3D {

  def apply(children: Graph3DItem*): Graph3D = Graph3D(children.toVector)

  def apply(axis: Vector[Axis3D], children: Graph3DItem*): Graph3D = new Graph3D(children.toVector, axis = axis)
  def apply(xSize: Int, ySize: Int, zSize: Int, children: Graph3DItem*): Graph3D = new Graph3D(children.toVector, xSize = xSize, ySize = ySize, zSize = zSize)

  def apply(axis: Vector[Axis3D], xSize: Int, ySize: Int, zSize: Int, children: Graph3DItem*): Graph3D =
    new Graph3D(children.toVector, axis = axis, xSize = xSize, ySize = ySize, zSize = zSize)

  def apply(xSize: Int, ySize: Int, zSize: Int, xPosition: Int, yPosition: Int, zPosition: Int, children: Graph3DItem*): Graph3D =
    new Graph3D(children.toVector, xSize = xSize, ySize = ySize, zSize = zSize, xPosition = xPosition, yPosition = yPosition, zPosition = zPosition)
  
}

case class Graph3D private (var children: Vector[Graph3DItem],
                            var axis: Vector[Axis3D] = Vector(XAxis3D(), YAxis3D(), ZAxis3D()),
                            var xSize: Int = 1,
                            var ySize: Int = 1,
                            var zSize: Int = 1,
                            var xPosition: Int = 0,
                            var yPosition: Int = 0,
                            var zPosition: Int = 0,
                            var name: String = "graph3d",
                            var config: Graph3DConfig = Graph3DConfig())
    extends Scene3DItem with Configurable with Executable with Parent {
  val group = "graph3d"
}

case class Graph3DConfig(var notes: String = "",
                         main: Graph3DMainConfig = Graph3DMainConfig(),
                         border: Graph3DBorderConfig = Graph3DBorderConfig(),
                         back: Graph3DBackConfig = Graph3DBackConfig())
