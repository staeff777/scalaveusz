package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children

object Vectorfield {
  val defaultName = "vectorfield"

  def apply(dxOrR: String = "",
            dyOrTheta: String = "",
            name: String = defaultName,
            mode: VectorfieldMode.Value = VectorfieldMode.Cartesian,
            rotate: Double = 0.0,
            reflectX: Boolean = false,
            reflectY: Boolean = false,
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y") =
    new Vectorfield(name, None, dxOrR, dyOrTheta, mode, rotate, reflectX, reflectY, xAxis, yAxis)
}

class Vectorfield private (val name: String,
                           val children: Option[Children] = None,
                           val dxOrR: String,
                           val dyOrTheta: String,
                           val mode: VectorfieldMode.Value,
                           val rotate: Double,
                           val reflectX: Boolean,
                           val reflectY: Boolean,
                           val keyText: String = "",
                           val xAxis: String = "x",
                           val yAxis: String = "y")
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "vectorfield"

  object config {
    val main = VectorfieldMainConfig()
    val line = LineConfig()
    val fill = FillConfig()
  }
}