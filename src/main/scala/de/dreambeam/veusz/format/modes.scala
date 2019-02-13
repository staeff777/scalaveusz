package de.dreambeam.veusz.format

object BarchartMode extends Enumeration {
  val Stacked = Value("stacked")
  val StackedArea = Value("stacked-area")
  val Grouped = Value("grouped")
}

object PositionMode extends Enumeration {
  val Relative = Value("relative")
  val Axes = Value("axes")
}

object VectorfieldMode extends Enumeration {
  val Cartesian = Value("cartesian")
  val Polar = Value("polar")
}

object AxisMode extends Enumeration {
  val Numeric = Value("numeric")
  val DateTime = Value("datetime")
  val Labels = Value("labels")
}