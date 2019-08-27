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

object LineStyle  {
  val Solid = "solid"
  val Dashed = "dashed"
  val Dotted = "dotted"
  val DashDot =  "dash-dot"
  val DashDotDot = "dash-dot-dot"
  val DottedFine = "dotted-fine"
  val DashedFine = "dashed-fine"
  val DashedDotFine = "dash-dot-fine"
  val Dot1 = "dot1"
  val Dot2 = "dot2"
  val Dot3 = "dot3"
  val Dash1 = "dash1"
  val Dash2 =  "dash2"
  val Dash3 = "dash3"
}
