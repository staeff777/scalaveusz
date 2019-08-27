package de.dreambeam.veusz.format

object FillStyle extends Enumeration {
  val Solid = Value("solid")
  val Horizontal = Value("horizontal")
  val Vertical = Value("vertical")
  val Cross = Value("cross")
  val Forwarddiagonals = Value("forward diagonals")
  val Backwarddiagonals = Value("backward diagonals")
  val Percent94 = Value("94% dense")
  val Percent88 = Value("88% dense")
  val Percent63 = Value("63% dense")
  val Percent50 = Value("50% dense")
  val Percent37 = Value("37% dense")
  val Percent12 = Value("12% dense")
  val Percent6 = Value("6% dense")
}

object FillTo extends Enumeration {
  val top = Value("top")
  val bottom = Value("bottom")
  val left = Value("left")
  val right = Value("right")
}