package de.dreambeam.veusz.format

object FillStyle {
  val Solid = "solid"
  val Horizontal = "horizontal"
  val Vertical = "vertical"
  val Cross = "cross"
  val Forwarddiagonals = "forward diagonals"
  val Backwarddiagonals = "backward diagonals"
  val DiagonalCross = "diagonal cross"
  val Forward2 = "forward 2"
  val Backward2 = "backward 2"
  val Forward3 = "forward 3"
  val Backward3 = "backward 3"
  val Forward4 = "forward 4"
  val Backward4 = "backward 4"
  val Forward5 = "forward 5"
  val Backward5= "backward 5"
  val DiagonalCross2 = "diagonal cross 2"
  val DiagonalCross3 = "diagonal cross 3"
  val DiagonalCross4 = "diagonal cross 4"
  val DiagonalCross5 = "diagonal cross 5"
  val VerticalForward = "vertical forward"
  val VerticalBackward= "vertical backward"
  val HorizontalForward= "horizontal forward"
  val HorizontalBackward= "horizontal backward"
  val Star = "star"
  val Triangles1 = "triangles1"
  val Triangles2 = "triangles2"
  val Triangles3 = "triangles3"
  val Triangles4 = "triangles4"
  val HorizontalDouble = "horizontal double"
  val VerticalDouble = "vertical double"
  val ForwardlDouble = "forward double"
  val BackwardDouble = "backward double"
  val DoubleCross = "double cross"
  val DoubleDiagonalCross = "double diagonal cross"
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