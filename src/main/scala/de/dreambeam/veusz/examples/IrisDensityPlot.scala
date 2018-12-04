package de.dreambeam.veusz.examples

import java.io.File
import de.dreambeam.veusz.components._
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.util.SizeUnits._
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import smile.stat.distribution._

object IrisDensityPlot extends App {

  implicit def vectorToArray(vector: Vector[Double]) = vector.toArray

  /**
    *
    *
    * @param over the x-values for which a probability value shall be estimated
    * @param xs the data points fed into Kernel Density Estimation
    * @return the predicated values
    */
  def kde(over: Vector[Double])(xs: Array[Double]) = {
    val kd = new KernelDensity(xs) // use rule of thumb
    over.map(kd.p)
  }
  // Read CSV
  val reader = new File("data/iris/iris.data").asUnsafeCsvReader[IrisRow](rfc.withoutHeader).toVector
  // Group by Sepal Length
  val sepalLengthGrouped = reader.groupBy(_.name).mapValues(_.map(_.sepalLength))

  // Compute minimum and maximum value for x-Axis
  val xMargin = 0.3 // with some extra margin on each side
  val minXValue = math.max(0.0, sepalLengthGrouped.values.flatten.min - xMargin)
  val maxXValue = sepalLengthGrouped.values.flatten.max + xMargin

  val granularity = .001 // the resolution of the model; it makes the curve look smooth
  val xData = minXValue to maxXValue by granularity toVector

  // partially apply kde with xData
  def kdeOverX = kde(xData)(_)

  /* Setosa */
  val xyPlotSetosa = XY(xData, kdeOverX(sepalLengthGrouped("Iris-setosa")), keyText="Setosa")
  // Config Setosa
  xyPlotSetosa.config.main.markerType = MarkerType.None
  xyPlotSetosa.config.fillBelow.fillTo = FillTo.bottom
  xyPlotSetosa.config.fillBelow.hide = false
  xyPlotSetosa.config.fillBelow.transparency = 60
  xyPlotSetosa.config.fillBelow.color = "#ef8188"

  /* Versicolor */
  val xyPlotVersicolor = XY(xData, kdeOverX(sepalLengthGrouped("Iris-versicolor")), keyText="Versicolor")
  // Config Versicolor
  xyPlotVersicolor.config.main.markerType = MarkerType.None
  xyPlotVersicolor.config.fillBelow.fillTo = FillTo.bottom
  xyPlotVersicolor.config.fillBelow.hide = false
  xyPlotVersicolor.config.fillBelow.transparency = 60
  xyPlotVersicolor.config.fillBelow.color = "#91e0a4"

  /* Virginica */
  val xyPlotVirginica = XY(xData, kdeOverX(sepalLengthGrouped("Iris-virginica")), keyText="Virginica")
  // Config Virginica
  xyPlotVirginica.config.main.markerType = MarkerType.None
  xyPlotVirginica.config.fillBelow.fillTo = FillTo.bottom
  xyPlotVirginica.config.fillBelow.hide = false
  xyPlotVirginica.config.fillBelow.transparency = 60
  xyPlotVirginica.config.fillBelow.color = "13b7d8"

  val graph = Graph(xyPlotSetosa, xyPlotVersicolor, xyPlotVirginica, Key())
  val page = Page(graph)
  page.config.height = 10 cm

  page.show("IrisDensityPlot")
}