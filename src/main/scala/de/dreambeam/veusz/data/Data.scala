package de.dreambeam.veusz.data

trait Data {
  val name: String
  def isEmpty: Boolean
}

object Data {

  /* Children */
  val $Numerical = Numerical
  val $DateTime = DateTimeConstructor
  val $Text = Text
  val $NumericalImage = NumericalImage
}