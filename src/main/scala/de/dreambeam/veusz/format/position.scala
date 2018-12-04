package de.dreambeam.veusz.format

object Rotation extends Enumeration {
  val Zero = Value("0")
  val Neg45 = Value("-45")
  val Neg90 = Value("-90")
  val Neg135 = Value("-135")
  val Neg180 = Value("-180")
  val Pos45 = Value("45")
  val Pos90 = Value("90")
  val Pos135 = Value("135")
  val Pos180 = Value("180")
}

object HorizontalPosition extends Enumeration {
  val Left = Value("left")
  val Centre = Value("centre")
  val Right = Value("right")
}

object VerticalPosition extends Enumeration {
  val Top = Value("top")
  val Centre = Value("centre")
  val Bottom = Value("bottom")
}

object KeyAlignment extends Enumeration {
  val top = Value("top")
  val centre = Value("centre")
  val bottom = Value("bottom")
}