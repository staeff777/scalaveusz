package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Item, GraphItem}
import de.dreambeam.veusz.format._

object Key {
  def apply() = new Key
}

class Key private (var title: String = "",
                   val name: String = "key",
                   val children: Option[Vector[Item]] = None)
  extends GraphItem
{
  val group = "key"

  object config {
    val main = KeyMainConfig()
    val text = TextConfig()
    val background = BackgroundConfig()
    val border = BorderConfig()
  }
}