package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.NumericalImage
import de.dreambeam.veusz.format.{ColorMaps, Scaling}
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object Image {

  def apply(data: Map[(Double, Double), Double],
            min: Option[Double] = None,
            max: Option[Double] = None,
            scaling: Scaling.Value = Scaling.Linear,
            transData: Option[Vector[Vector[Double]]] = None,
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "image",
            config: ImageConfig = ImageConfig()) = new Image(NumericalImage(data), min, max, scaling, transData, keyText, xAxis, yAxis, name, config)

}

case class Image(var dataset: NumericalImage,
                 var min: Option[Double],
                 var max: Option[Double],
                 var scaling: Scaling.Value,
                 var transData: Option[Vector[Vector[Double]]],
                 var keyText: String,
                 var xAxis: String,
                 var yAxis: String,
                 var name: String,
                 var config: ImageConfig)
    extends GraphItem with Configurable with Executable {
  val group = "image"

}

case class ImageConfig(var colorMap: String = ColorMaps.Grey, var invertColormap: Boolean = false, var transparency: Int = 0, var hide: Boolean = false, var smooth: Boolean = true)
