package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.types._
import de.dreambeam.veusz.format._

object Function {
  val defaultName = "function"

  def apply(name: String = Function.defaultName,
            function: String,
            key: String = "",
            min: Option[Double] = None,
            max: Option[Double] = None) =
    new Function(name, None, function, key, min, max)
}

class Function private (val name: String = Function.defaultName,
                        var children: Option[Children] = None,
                        val function: String,
                        val key: String = "",
                        val min: Option[Double] = None,
                        val max: Option[Double] = None
                        )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "function"

  object config {
    /* Properties */
    var notes: String = _

    val main = FunctionMainConfig()
    val plotLine = LineConfig(Colors.Auto)
    val fillBelow = FillConfig(hide=true)
    val fillAbove = FillConfig(hide=true)
  }
}