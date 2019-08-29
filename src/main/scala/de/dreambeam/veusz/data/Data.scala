package de.dreambeam.veusz.data

trait Data {
  val name: String
  def isEmpty: Boolean
}

trait BarChartData

object Data {

  /* Children */
  val $Numerical = Numerical
  val $DateTime = DateTimeConstructor
  val $Text = Text
  val $NumericalImage = NumericalImage
}


object BarChartData{
  val $Numerical = Numerical
  val $DateTime = DateTimeConstructor
}