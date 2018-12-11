package de.dreambeam.veusz.components

import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._
import de.dreambeam.veusz._

object Page {

  val defaultName = "page"

  def apply(name: String = defaultName): Page =
    Page(name, None)

  def apply(name: String, children: Vector[PageItem]): Page =
    Page(name, Some(children))

  def apply(name: String, children: PageItem*): Page =
    Page(name, Some(children.toVector))

  def apply(children: PageItem*): Page =
    Page(defaultName, Some(children.toVector))

  /* Children */
  val $Grid = Grid
  val $Graph = Graph
  val $Shape = Shape
}

case class Page private (name: String, children: Option[Vector[PageItem]])
  extends DocumentItem
    with Configurable
    with Executable
    with Parent
{
  val group = "page"
  var config: PageConfig = PageConfig()
}

case class PageConfig(var notes: String = "",
                      var width: SizeUnit = 15 cm,
                      var height: SizeUnit = 15 cm)