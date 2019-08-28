package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, PolarGraphItem}
import de.dreambeam.veusz.data.{Data, Numerical}
import de.dreambeam.veusz.format.{ColorConfig, MarkerBorderConfig, MarkerFillConfig, NonOrthFillConfig, NonOrthMainConfig, PlotLineConfig, XYLabelConfig}

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
                        var name: String) extends PolarGraphItem with Configurable with Executable {

  val group = "nonorthpoint"
  var config: NonOrthPointConfig = NonOrthPointConfig()
}

case class NonOrthPointConfig(main: NonOrthMainConfig = NonOrthMainConfig(),
                              plotLine: PlotLineConfig = PlotLineConfig(),
                              markerBorder: MarkerBorderConfig = MarkerBorderConfig(),
                              markerFill: MarkerFillConfig = MarkerFillConfig(),
                              areaFill1: NonOrthFillConfig = NonOrthFillConfig(),
                              areaFill2: NonOrthFillConfig = NonOrthFillConfig(),
                              label: XYLabelConfig = XYLabelConfig(),
                              colorConfig: ColorConfig = ColorConfig())