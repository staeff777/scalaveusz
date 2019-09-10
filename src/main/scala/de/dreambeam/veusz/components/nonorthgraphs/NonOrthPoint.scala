package de.dreambeam.veusz.components.nonorthgraphs

import de.dreambeam.veusz.data.Numerical
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, NonOrthGraphItem}

object NonOrthPoint {

  def apply(data1: Vector[Double], data2: Vector[Double], scaleMarkers: Vector[Double] = Vector.empty[Double], colorMarkers: Vector[Double] = Vector.empty[Double], keyText: String = "", name: String = "nonorthpoint") =
    new NonOrthPoint(Numerical(data1), Numerical(data2), Numerical(scaleMarkers), Numerical(colorMarkers), keyText, name)

  def apply(data1: Numerical, data2: Numerical, scaleMarkers: Numerical, colorMarkers: Numerical, keyText: String, name: String) =
    new NonOrthPoint(data1, data2, scaleMarkers, colorMarkers, keyText, name)

}

case class NonOrthPoint(data1: Numerical,
                        data2: Numerical,
                        scaleMarkers: Numerical,
                        colorMarkers: Numerical,
                        keyText: String,
                        var name: String) extends NonOrthGraphItem with Configurable with Executable {

  val group = "nonorthpoint"
  var config: NonOrthPointConfig = NonOrthPointConfig()
}

case class NonOrthPointConfig(main: NonOrthMainConfig = NonOrthMainConfig(),
                              plotLine: LineStyleConfig = LineStyleConfig(),
                              markerBorder: MarkerBorderConfig = MarkerBorderConfig(),
                              markerFill: MarkerFillConfig = MarkerFillConfig(),
                              areaFill1: NonOrthFillConfig = NonOrthFillConfig(),
                              areaFill2: NonOrthFillConfig = NonOrthFillConfig(),
                              label: XYLabelConfig = XYLabelConfig(),
                              colorConfig: ColorConfig = ColorConfig())
