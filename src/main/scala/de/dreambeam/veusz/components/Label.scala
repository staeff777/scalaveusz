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

  def apply(text: String,
            xPositions: Vector[Double] = Vector(0.5),
            yPositions: Vector[Double] = Vector(0.5),
            positionMode: Positioning.Value = Positioning.Relative,
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "label",
            config: LabelConfig = LabelConfig()): Label = new Label(text, xPositions, yPositions, positionMode, xAxis, yAxis, name, LabelConfig())
}

case class Label(text: String,
                 xPositions: Vector[Double],
                 yPositions: Vector[Double],
                 positionMode: Positioning.Value,
                 xAxis: String,
                 yAxis: String,
                 var name: String = "label",
                 var config: LabelConfig)
    extends GraphItem with PageItem with Configurable with Executable {
  val group = "label"

}

case class LabelConfig(alignment: LabelMainConfig = LabelMainConfig(),
                       text: TextConfig = TextConfig(),
                       background: BackgroundConfig = BackgroundConfig(),
                       border: BorderConfig = BorderConfig())
