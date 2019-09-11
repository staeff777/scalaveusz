package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent, Scene3DItem}

object Scene3D {

  def apply(children: Scene3DItem*): Scene3D = new Scene3D(children = children.toVector)

  def apply(name: String, children: Scene3DItem*): Scene3D = new Scene3D(children.toVector, name = name)

  def apply(xRotation: Int, yRotation: Int, zRotation: Int, children: Scene3DItem*): Scene3D = new Scene3D(children.toVector, xRotation, yRotation, zRotation)

  def apply(xRotation: Int, yRotation: Int, zRotation: Int, distance: Int, children: Scene3DItem*): Scene3D =
    new Scene3D(children.toVector, xRotation, yRotation, zRotation, distance)

  def apply(xRotation: Int, yRotation: Int, zRotation: Int, distance: Int, renderMethod: RenderMethod.Value, children: Scene3DItem*): Scene3D =
    new Scene3D(children.toVector, xRotation, yRotation, zRotation, distance, renderMethod)

  def apply( renderMethod: RenderMethod.Value, children: Scene3DItem*): Scene3D =
    new Scene3D(children.toVector, renderMethod = renderMethod)

  def apply(xRotation: Int, yRotation: Int, zRotation: Int, renderMethod: RenderMethod.Value, children: Scene3DItem*): Scene3D = new Scene3D(children.toVector, xRotation, yRotation, zRotation, renderMethod = renderMethod)

}

case class Scene3D(var children: Vector[Scene3DItem],
                   var xRotation: Int = 0,
                   var yRotation: Int = 35,
                   var zRotation: Int = 0,
                   var distance: Int = 5,
                   var renderMethod: RenderMethod.Value = RenderMethod.Fast,
                   var name: String = "scene3d",
                   var config: Scene3DConfig = Scene3DConfig())
    extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "scene3d"

}

case class Scene3DConfig(main: Scene3DMainConfig = Scene3DMainConfig(),
                         lighting1: Scene3DLightingConfig = Scene3DLightingConfig(true, Colors.White, 100, 0, 0, 0),
                         lighting2: Scene3DLightingConfig = Scene3DLightingConfig(false, Colors.Red, 100, 2, 0, 0),
                         lighting3: Scene3DLightingConfig = Scene3DLightingConfig(false, Colors.Blue, 100, -2, 0, 0))
