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

object Function3DMode extends Enumeration {
  val X_FNS_YZ = Value("x=fn(y,z)")
  val Y_FNS_XZ = Value("y=fn(x,z)")
  val Z_FNS_XY = Value("z=fn(x,y)")
  val XYZ_FNS_T =Value( "x,y,z=fns(t)")
  val XY_FNS_Z = Value("x,y=fns(z)")
  val YZ_FNS_X = Value("y,z=fns(x)")
  val XZ_FNS_Y = Value("x,z=fns(y)")
}