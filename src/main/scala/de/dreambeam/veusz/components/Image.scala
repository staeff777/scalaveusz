package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GraphItem}
import de.dreambeam.veusz.format.{ColorMaps, ImageScaling}


case class Image( dataset: Vector[Vector[Double]],
                 min: Option[Double] = None,
                 max: Option[Double] = None,
                 scaling: ImageScaling.Value = ImageScaling.Linear,
                 transData: Option[Vector[Vector[Double]]] = None,
                 keyText: String  = "",
                 xAxis: String = "x",
                 yAxis: String = "y",
                 name: String = "image",
                )
  extends GraphItem
    with Configurable
    with Executable {
  val group = "image"
  var config = ImageConfig()
}

case class ImageConfig(colorMap: String = ColorMaps.Grey,
                       invertColormap: Boolean = false,
                       transparency: Int = 0,
                       hide: Boolean = false,
                       smooth: Boolean = true)
