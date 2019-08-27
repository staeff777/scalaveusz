package de.dreambeam.veusz.util

import scala.collection.mutable

object StringTools {
  var itemNames = mutable.Map.empty[String, Int]

  def toInt(s: Char): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  def uniqueName(name: String): String = {
    if (itemNames contains name) {
      itemNames(name) += 1
      s"$name${itemNames(name)}"
    }
    else {
      itemNames.put(name, 1)
      name
    }
  }

  def noBlanks(name: String) =
    name.replace(" ", "_").replace(",", "")
}