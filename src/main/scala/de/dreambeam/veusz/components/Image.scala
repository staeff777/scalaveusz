package de.dreambeam.veusz.components

import de.dreambeam.veusz.{GraphItem, Configurable, Executable}
import de.dreambeam.veusz.data.Numerical
import de.dreambeam.veusz.format._

object Image {
  val defaultName = "image"

  def apply(
           name: String = defaultName,
           dataset: Vector[Vector[Double]],
           minval: Option[Int] = None,
           maxval: Option[Int] = None,
           scaling: ImageScaling.Value = ImageScaling.Linear,
           transData: Option[Vector[Vector[Double]]] = None,
           keys: Vector[String] = Vector(""),
           xAxis: String = "x",
           yAxis: String = "y"
           ) =
    new Image(name, None, dataset, minval, maxval, scaling, transData, keys, xAxis, yAxis)

}

class Image private (val name: String = Graph.defaultName,
                     val children: Option[Vector[GraphItem]] = None,
                     val dataset: Vector[Vector[Double]],
                     val minval: Option[Int] = None,
                     val maxval: Option[Int] = None,
                     val scaling: ImageScaling.Value = ImageScaling.Linear,
                     val transData: Option[Vector[Vector[Double]]] = None,
                     val keys: Vector[String] = Vector(""),
                     val xAxis: String = "x",
                     val yAxis: String = "y"
                    )
  extends GraphItem
    with Configurable
    with Executable
{
  val group = "image"

  object config {
    var colorMap: ColorMapType.Value = ColorMapType.Heat
    var invertColorMap: Boolean = false
    var transparency: Int = 0
    var hide: Boolean = false
    var smooth: Boolean = false
  }
}