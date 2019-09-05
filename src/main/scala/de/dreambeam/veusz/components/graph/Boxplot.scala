package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.BoxplotData
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}


object Boxplot {

  def apply(data: BoxplotData,
            whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
            fillFraction: Double = 0.75,
            name: String = "boxplot"
            ): Boxplot = {

   new Boxplot(data, whiskerMode, fillFraction, name)
  }

}

case class Boxplot(data: BoxplotData,
                   whiskerMode: WhiskerMode.Value,
                   fillFraction: Double,
                   name: String
                   )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "boxplot"
  var config = BoxplotConfig()
}

case class BoxplotConfig(main: BoxplotMainConfig = BoxplotMainConfig(),
                         fill: BoxplotFillConfig = BoxplotFillConfig(),
                         border: BorderConfig = BorderConfig(),
                         whiskerLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(),
                         markersBorder: BorderConfig = BorderConfig(),
                         markersFill: BackgroundConfig = BackgroundConfig()
                         )
