package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, DocumentItem, Executable, Item}
import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._

object Document
{
  var instance: Document = null
  var OutPath = "veusz"
  val defaultName = "document"

  def apply(name: String = defaultName) = {
    if (instance == null) {
      instance = new Document(name, None)
    }
    instance
  }

  def apply(name: String, children: Vector[DocumentItem]) = {
    if (instance == null) {
      instance = new Document(name, Some(children))
    }
    instance
  }

  def apply(name: String, children: DocumentItem*) = {
    if (instance == null) {
      instance = new Document(name, Some(children.toVector))
    }
    instance
  }

  def apply(children: DocumentItem*) = {
    if (instance == null) {
      instance = new Document(defaultName, Some(children.toVector))
    }
    instance
  }

  /* Children */
  val $Page = Page
}


class Document private (val name: String, val children: Option[Vector[DocumentItem]])
  extends Item
  with Configurable
  with Executable
{
  val group = ""

  object config {
    var width: SizeUnit = 15 cm
    var height: SizeUnit = 15 cm
    var englishLocale: Boolean = false
  }
}