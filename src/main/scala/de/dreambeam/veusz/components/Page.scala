package de.dreambeam.veusz.components

import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._
import de.dreambeam.veusz.{Configurable, DocumentItem, Executable, PageItem}

object Page {

  val defaultName = "page"

  def apply(name: String = defaultName) =
    new Page(name, None)

  def apply(name: String, children: Vector[PageItem]) =
    new Page(name, Some(children))

  def apply(name: String, children: PageItem*) =
    new Page(name, Some(children.toVector))

  def apply(children: PageItem*) =
    new Page(defaultName, Some(children.toVector))

  /* Children */
  val $Grid = Grid
  val $Graph = Graph
  val $Shape = Shape
}

class Page private (val name: String, val children: Option[Vector[PageItem]])
  extends DocumentItem
  with Configurable
  with Executable
{
  val group = "page"

  object config {
    var notes: String = _
    var width: SizeUnit = 15 cm
    var height: SizeUnit = 15 cm
  }
}