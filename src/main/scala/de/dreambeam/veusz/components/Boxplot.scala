package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.data.{BoxplotData, Text}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.types.Children
import de.dreambeam.veusz.util.MemoryTools.uniqueReference
import de.dreambeam.veusz.util.SizeUnits._

object Boxplot {
  val defaultName = "boxplot"

  def apply(data: BoxplotData,
            name: String = defaultName,
            whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
            fillFraction: Double = 0.75
            ) = {
    val dataNames = data.data.map(uniqueReference(_, ""))
    val labelNames = uniqueReference(Text(data.labels), "labels")
    new Boxplot(name, None, dataNames, labelNames, whiskerMode, fillFraction)
  }

}

class Boxplot(val name: String,
              val children: Option[Children] = None,
              val dataNames: Vector[String],
              val labelNames: String,
              var whiskerMode: WhiskerMode.Value,
              var fillFraction: Double,
              )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "boxplot"

  object config {
    val main = BoxplotMainConfig()
    val fill = BoxplotBoxFillConfig()
    val border = BorderConfig()
    val whiskerLine = LineConfig()
    val markersBorder = BorderConfig()
    val markersFill = BackgroundConfig()
  }
}
