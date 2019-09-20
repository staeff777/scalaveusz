package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.format.SizeUnit
import de.dreambeam.veusz._

object Document
{

  def apply(name: String, children: WrappedDocumentItem*) = {
      new Document( children.toVector, name = name)
  }

  def apply(children: WrappedDocumentItem*) = {
       new Document( children.toVector)
  }

}

case class Document (var children: Vector[WrappedDocumentItem], var config: DocumentConfig = DocumentConfig(), var name: String = "document")
  extends Item
  with Configurable
  with Executable
  with Parent
{
  val group = ""
}

case class DocumentConfig(var width: SizeUnit = 15 cm,
                          var height: SizeUnit = 15 cm,
                          var englishLocale: Boolean = false)