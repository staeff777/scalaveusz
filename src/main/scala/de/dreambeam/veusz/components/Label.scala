package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format._

case class Label(label: String,
                 xPositions: Vector[Double] = Vector(0.5),
                 yPositions: Vector[Double] = Vector(0.5),
                 positionMode: Positioning.Value = Positioning.Relative,
                 xAxis: String = "x",
                 yAxis: String = "y",
                 var name: String = "label")
extends GraphItem
  with Configurable
  with Executable
{
  val group = "label"
  var config: LabelConfig = de.dreambeam.veusz.components.LabelConfig()
}

case class LabelConfig(alignment: LabelMainConfig = LabelMainConfig(),
                       text: TextConfig = TextConfig(),
                       background: BackgroundConfig = BackgroundConfig(),
                       border: BorderConfig = BorderConfig())