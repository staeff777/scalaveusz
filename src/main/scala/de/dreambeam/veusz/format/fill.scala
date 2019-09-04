package de.dreambeam.veusz.format

object FillStyle {
  val Solid = "solid"
  val Horizontal = "horizontal"
  val Vertical = "vertical"
  val Cross = "cross"
  val Forwarddiagonals = "forward diagonals"
  val Backwarddiagonals = "backward diagonals"
  val Percent94 = "94% dense"
  val Percent88 = "88% dense"
  val Percent63 = "63% dense"
  val Percent50 = "50% dense"
  val Percent37 = "37% dense"
  val Percent12 = "12% dense"
  val Percent6 = "6% dense"
}

object FillTo extends Enumeration {
  val top = Value("top")
  val bottom = Value("bottom")
  val left = Value("left")
  val right = Value("right")
}