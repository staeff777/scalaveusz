package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.BoxplotData
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}



case class Boxplot(var data: BoxplotData,
                   var whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
                   var fillFraction: Double = 0.75,
                   var name: String = "boxplot",
                   var config: BoxplotConfig = BoxplotConfig()
                   )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "boxplot"

}

case class BoxplotConfig(main: BoxplotMainConfig = BoxplotMainConfig(),
                         fill: BoxplotFillConfig = BoxplotFillConfig(),
                         border: BorderConfig = BorderConfig(),
                         whiskerLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(),
                         markersBorder: BorderConfig = BorderConfig(),
                         markersFill: BackgroundConfig = BackgroundConfig()
                         )
