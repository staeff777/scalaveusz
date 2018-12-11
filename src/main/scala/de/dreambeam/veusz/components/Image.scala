package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.format._

case class Image (children: Option[Vector[GraphItem]] = None,
                          dataset: Vector[Vector[Double]],
                          minval: Option[Int] = None,
                          maxval: Option[Int] = None,
                          scaling: ImageScaling.Value = ImageScaling.Linear,
                          transData: Option[Vector[Vector[Double]]] = None,
                          keys: Vector[String] = Vector(""),
                          xAxis: String = "x",
                          yAxis: String = "y",
                          var name: String = "image")
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "image"
  var config = ImageConfig()
}

case class ImageConfig(var colorMap: ColorMapType.Value = ColorMapType.Heat,
                       var invertColorMap: Boolean = false,
                       var transparency: Int = 0,
                       var hide: Boolean = false,
                       var smooth: Boolean = false)