package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._

object Document
{

  val defaultName = "document"

  def apply(name: String = defaultName) = new Document(name, None)


  def apply(name: String, children: Vector[DocumentItem]) = {
     new Document(name, Some(children))
  }

  def apply(name: String, children: DocumentItem*) = {
      new Document(name, Some(children.toVector))
  }

  def apply(children: DocumentItem*) = {
       new Document(defaultName, Some(children.toVector))
  }

  /* Children */
  val $Page = Page
}


class Document private (val name: String, val children: Option[Vector[DocumentItem]])
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