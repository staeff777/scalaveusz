package de.dreambeam.veusz.components

import de.dreambeam.veusz.components.graph.Graph
import de.dreambeam.veusz.{Configurable, DocumentItem, Executable, PageItem, Parent}
import de.dreambeam.veusz.format.SizeUnits._
import de.dreambeam.veusz.format.SizeUnit

object Page {

  def apply(name: String, children: PageItem*): Page =
    Page(children.toVector, name = name)

  def apply(children: PageItem*): Page =
    Page(children.toVector, name = "page")

}

case class Page (var children: Vector[PageItem] = Vector.empty, var name: String, var config: PageConfig = PageConfig())
  extends DocumentItem
    with Configurable
    with Executable
    with Parent
{
  val group = "page"

}

case class PageConfig(var notes: String = "",
                      var width: SizeUnit = 15 cm,
                      var height: SizeUnit = 15 cm)