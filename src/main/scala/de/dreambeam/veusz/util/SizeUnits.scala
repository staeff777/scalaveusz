package de.dreambeam.veusz.util

import scala.language.implicitConversions

trait SizeUnit {
  def value: Double
  def unit: String
  def getValue: String = value + unit

  override def toString(): String = getValue
}

case class Point(value: Double) extends SizeUnit {
  override def unit: String = "pt"
}

case class Centimeter(value: Double) extends SizeUnit {
  override def unit: String = "cm"
}

case class Millimeter(value: Double) extends SizeUnit {
  override def unit: String = "mm"
}

case class Inches(value: Double) extends SizeUnit {
  override def unit: String = "in"
}

case class Percent(value: Double) extends SizeUnit {
  override def unit: String = "%"
}

object SizeUnits {
  implicit class DoubleWithUnits(v: Double) {
    def cm() = Centimeter(v)
    def pt() = Point(v)
    def mm() = Millimeter(v)
    def in() = Inches(v)
    def percent() = Percent(v)

  }

  //Convert Double to Point on Default
  implicit def double2Point(x: Double) = Point(x)

}