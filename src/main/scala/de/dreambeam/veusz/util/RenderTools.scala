package de.dreambeam.veusz.util

import de.dreambeam.veusz.format.SizeUnit

import scala.collection.mutable

object RenderTools {
  def newLine = "\n"

  def getBool(b: Boolean) = if (b) "True" else "False"

  def getOption[A](value: Option[A]) = value match {
    case Some(v) => v.toString
    case None => "u'Auto'"
  }

  def renderNotes(note: String) = {
    if (note == null) ""
    else s"""Set('notes', u'$note')"""
  }

  /*
  val itemNames = mutable.HashSet.empty[String]

  def uniqueName(name: String, index: Int): String = {
    if (itemNames contains name) s"${name}_[$index]"
    else {
      itemNames.add(name)
      name
    }
  }
  */

  def render(field: String, value: Any): String =
    render(null)(field, value)

  def render(prefix: String)(field: String, value: Any) = {
    val pre = {
      if (prefix == null || prefix == "") ""
      else s"$prefix/"
    }
    value match {
      case b: Boolean => s"Set('$pre$field', ${getBool(b)})"
      case i: Int => s"Set('$pre$field', $i)"
      case d: Double => s"Set('$pre$field', $d)"
      case vs: Vector[_] => s"Set('$pre$field', (${vs.map(e => s"'$e',").mkString}))"
      case m: Map[_,_] => s"Set('$pre$field', {${m.toVector.map{ case (k, v) => s"'$k': $v" }.mkString(",")}})" // supports only String->Double Maps
      case x => s"Set('$pre$field', u'$x')"
    }
  }


  def renderOption(field: String, value: Option[Double], noneCase: String): String =
    renderOption(null)(field, value, noneCase)

  def renderOption(prefix: String)(field: String, value: Option[Double], noneCase: String) = {
    val pre = {
      if (prefix == null || prefix == "") ""
      else s"$prefix/"
    }

    value match {
      case Some(value) => s"Set('$pre$field', $value)"
      case None => noneCase
    }
  }


  def renderSizeOption(field: String, value: Option[SizeUnit], noneCase: String): String =
    renderSizeOption(null)(field, value, noneCase)

  def renderSizeOption(prefix: String)(field: String, value: Option[SizeUnit], noneCase: String) = {
    val pre = {
      if (prefix == null || prefix == "") ""
      else s"$prefix/"
    }

    value match {
      case Some(value) => s"Set('$pre$field', u'$value')"
      case None => noneCase
    }
  }
}