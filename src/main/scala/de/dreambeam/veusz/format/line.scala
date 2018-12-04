package de.dreambeam.veusz.format

object LineSteps extends Enumeration {
  val Off = Value("off")
  val Left = Value("left")
  val Centre = Value("centre")
  val Right = Value("right")
  val LeftShiftPoints = Value("left-shift-points")
  val RightShiftPoints = Value("right-shift-points")
  val VCentre = Value("vcentre")
}

object LineStyle extends Enumeration {
  val Solid = Value("solid")
  val Dashed = Value("dashed")
  val Dotted = Value("dotted")
  val DashDot = Value("dash-dot")
  val DashDotDot = Value("dash-dot-dot")
  val DottedFine = Value("dotted-fine")
  val DashedFine = Value("dashed-fine")
  val DashedDotFine = Value("dash-dot-fine")
  val Dot1 = Value("dot1")
  val Dot2 = Value("dot2")
  val Dot3 = Value("dot3")
  val Dash1 = Value("dash1")
  val Dash2 = Value("dash2")
  val Dash3 = Value("dash3")
}
