package de.dreambeam.veusz.components.graph3d

import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent, Scene3DItem}

object Scene3D {
  def apply: Scene3D = Scene3D()
  def apply(children: Vector[Scene3DItem]): Scene3D = Scene3D(children = Some(children))
  def apply(name: String, children: Vector[Scene3DItem]): Scene3D = Scene3D(name, Some(children))
  def apply(name: String, children: Scene3DItem*): Scene3D = Scene3D(name, Some(children.toVector))
  def apply(children: Scene3DItem*): Scene3D = Scene3D(children=Some(children.toVector))

  /* Children */
  val $Graph3D = Graph3D
}

case class Scene3D (var name: String = "scene3d",
                    children: Option[Vector[Scene3DItem]] = None,
                    xRotation: Int = 0,
                    yRotation: Int = 35,
                    zRotation: Int = 0,
                    distance: Int = 5,
                    renderMethod: RenderMethod.Value = RenderMethod.Fast)
  extends PageItem
    with GridItem
    with Configurable
    with Executable
    with Parent
{
  val group = "scene3d"
  var config = Scene3DConfig()
}

case class Scene3DConfig(main: Scene3DMainConfig = Scene3DMainConfig(),
                         lighting1: Scene3DLightingConfig = Scene3DLightingConfig(true, Colors.White, 100, 0, 0, 0),
                         lighting2: Scene3DLightingConfig = Scene3DLightingConfig(false, Colors.Red, 100, 2, 0, 0),
                         lighting3: Scene3DLightingConfig = Scene3DLightingConfig(false, Colors.Blue, 100, -2, 0, 0)
                        )