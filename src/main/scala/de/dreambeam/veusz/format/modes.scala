package de.dreambeam.veusz.format

object BarchartMode extends Enumeration {
  val Stacked = Value("stacked")
  val StackedArea = Value("stacked-area")
  val Grouped = Value("grouped")
}

object Positioning extends Enumeration {
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

object Surface3DMode extends Enumeration {
  val X_YZ = Value("x(y,z)")
  val X_ZY = Value("x(z,y)")
  val Y_XZ = Value("y(x,z)")
  val Y_ZX = Value("y(z,x)")
  val Z_XY = Value("z(x,y)")
  val Z_YX = Value("z(y,x)")
}
