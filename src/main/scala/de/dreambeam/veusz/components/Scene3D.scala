package de.dreambeam.veusz.components

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Executable, GridItem, PageItem, Scene3DItem}

object Scene3D {
  val defaultName = "scene3d"

  def apply = new Scene3D
  def apply(children: Vector[Scene3DItem]) = new Scene3D(children = Some(children))
  def apply(name: String, children: Vector[Scene3DItem]) = new Scene3D(name, Some(children))
  def apply(name: String, children: Scene3DItem*) = new Scene3D(name, Some(children.toVector))
  def apply(children: Scene3DItem*) = new Scene3D(children=Some(children.toVector))
  def apply(name: String = Scene3D.defaultName,
            children: Vector[Scene3DItem],
            xRotation: Int = 0,
            yRotation: Int = 35,
            zRotation: Int = 0,
            distance: Int = 5,
            renderMethod: RenderMethod.Value = RenderMethod.Fast) =
    new Scene3D(name, Some(children), xRotation, yRotation, zRotation, distance, renderMethod)
  /* Children */
  val $Graph3D = Graph3D
}

class Scene3D private (val name: String = Scene3D.defaultName,
                       var children: Option[Vector[Scene3DItem]] = None,
                       var xRotation: Int = 0,
                       var yRotation: Int = 35,
                       var zRotation: Int = 0,
                       var distance: Int = 5,
                       var renderMethod: RenderMethod.Value = RenderMethod.Fast)
  extends PageItem
    with GridItem
    with Executable
{
  val group = "scene3d"

  object config {
    val main = Scene3DMainConfig()
    val lighting1 = Scene3DLightingConfig(true, Colors.White, 100, 0, 0, 0)
    val lighting2 = Scene3DLightingConfig(false, Colors.Red, 100, 2, 0, 0)
    val lighting3 = Scene3DLightingConfig(false, Colors.Blue, 100, -2, 0, 0)

  }
}