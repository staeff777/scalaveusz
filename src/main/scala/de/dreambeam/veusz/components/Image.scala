package de.dreambeam.veusz.components

import de.dreambeam.veusz.data.NumericalImage
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format.{ColorMaps, Scaling}


object Image {
  def apply(data: Map[(Double, Double), Double],
            min: Option[Double] = None,
            max: Option[Double] = None,
            scaling: Scaling.Value = Scaling.Linear,
            transData: Option[Vector[Vector[Double]]] = None,
            keyText: String = "",
            xAxis: String = "x",
            yAxis: String = "y",
            name: String = "image") =  new Image(new NumericalImage(data), min, max, scaling, transData, keyText, xAxis, yAxis, name)

}

case class Image(dataset: NumericalImage,
                 min: Option[Double],
                 max: Option[Double],
                 scaling: Scaling.Value,
                 transData: Option[Vector[Vector[Double]]],
                 keyText: String,
                 xAxis: String,
                 yAxis: String,
                 name: String,
                )
  extends GraphItem
    with Configurable
    with Executable {
  val group = "image"
  var config = ImageConfig()
}

case class ImageConfig(var colorMap: String = ColorMaps.Grey,
                       var invertColormap: Boolean = false,
                       var transparency: Int = 0,
                       var hide: Boolean = false,
                       var smooth: Boolean = true)
