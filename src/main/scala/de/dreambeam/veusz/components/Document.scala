package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.format.SizeUnit
import de.dreambeam.veusz._

object Document
{

  val defaultName = "document"


  def apply(name: String, children: DocumentItem*) = {
      new Document(name, children.toVector)
  }

  def apply(children: DocumentItem*) = {
       new Document(defaultName, children.toVector)
  }

  /* Children */
  val $Page = Page
}


class Document private (val name: String, val children: Vector[DocumentItem])
  extends Item
  with Configurable
  with Executable
  with Parent
{
  val group = ""

  var config: DocumentConfig = DocumentConfig()
}

case class DocumentConfig(var width: SizeUnit = 15 cm,
                          var height: SizeUnit = 15 cm,
                          var englishLocale: Boolean = false)