package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem, PageItem, NonOrthGraphItem}
import de.dreambeam.veusz.format._

object Label {

  def apply(text: String, xPosition: Double, yPosition: Double): Label = Label(text, Vector(xPosition), Vector(yPosition))
  def apply(text: String, xPosition: Double, yPosition: Double, positionMode: Positioning.Value): Label = Label(text, Vector(xPosition), Vector(yPosition), positionMode)

  def apply(text: String, xPosition: Double, yPosition: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String): Label =
    Label(text, Vector(xPosition), Vector(yPosition), positionMode, xAxis, yAxis)

  def apply(text: String, xPosition: Double, yPosition: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String): Label =
    Label(text, Vector(xPosition), Vector(yPosition), positionMode, xAxis, yAxis, name)

  def apply(text: String, xPosition: Double, yPosition: Double, positionMode: Positioning.Value, xAxis: String, yAxis: String, name: String, config: LabelConfig): Label =
    Label(text, Vector(xPosition), Vector(yPosition), positionMode, xAxis, yAxis, name, config)

}

case class Label(var text: String,
                 var xPositions: Vector[Double] = Vector(0.5),
                 var yPositions: Vector[Double] = Vector(0.5),
                 var positionMode: Positioning.Value = Positioning.Relative,
                 var xAxis: String = "x",
                 var yAxis: String = "y",
                 var name: String = "label",
                 var config: LabelConfig = LabelConfig())
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "label"

}

case class LabelConfig(alignment: LabelMainConfig = LabelMainConfig(),
                       text: TextConfig = TextConfig(),
                       background: BackgroundConfig = BackgroundConfig(),
                       border: BorderConfig = BorderConfig())
