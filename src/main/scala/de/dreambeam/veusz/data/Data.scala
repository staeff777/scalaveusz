package de.dreambeam.veusz.data

trait Data {
  val name: String
}

object Data {

  /* Children */
  lazy val $Numerical = Numerical
  lazy val $DateTime = DateTime
  lazy val $Text = Text
}