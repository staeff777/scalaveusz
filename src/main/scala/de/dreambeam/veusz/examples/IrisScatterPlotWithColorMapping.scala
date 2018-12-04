package de.dreambeam.veusz.examples

import java.io.File

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

import de.dreambeam.veusz.components._
import de.dreambeam.veusz.format._

object IrisScatterPlotWithColorMapping extends App {
  // Read CSV
  val reader = new File("data/iris/iris.data").asUnsafeCsvReader[IrisRow](rfc.withoutHeader).toVector
  val petalLengthGrouped = reader.groupBy(_.name).mapValues(_.map(_.petalLength)) // Petal Length grouped by category
  val petalWidthGrouped = reader.groupBy(_.name).mapValues(_.map(_.petalWidth)) // Petal Width grouped by category

  // unpack the x and y values for each category
  val xSetosa = petalLengthGrouped("Iris-setosa")
  val ySetosa = petalWidthGrouped("Iris-setosa")

  val xVersicolor = petalLengthGrouped("Iris-versicolor")
  val yVersicolor = petalWidthGrouped("Iris-versicolor")

  val xVirginica = petalLengthGrouped("Iris-virginica")
  val yVirginica = petalWidthGrouped("Iris-virginica")


  val xyPlotSetosa = XY(xSetosa, ySetosa, keyText="Setosa")
  val xyPlotVersicolor = XY(xVersicolor, yVersicolor, keyText="Versicolor")
  val xyPlotVirginica = XY(xVirginica, yVirginica, keyText="Virginica")

  // Some Configurations
  // for scatter plots we don't need connected lines
  xyPlotSetosa.config.plotLine.hide = true
  xyPlotVersicolor.config.plotLine.hide = true
  xyPlotVirginica.config.plotLine.hide = true

  // different colors for different categories
  xyPlotSetosa.config.markerFill.color = "#f91820"
  xyPlotVersicolor.config.markerFill.color = "#64e02f"
  xyPlotVirginica.config.markerFill.color = "#432fe0"

  // this is the legend
  val key = Graph.$Key()
  // we can adjust the position horizontally and vertically
  key.config.main.verticalPosition = VerticalPosition.Bottom
  // key.config.mainStyle.horizontalPosition = HorizontalPosition.
  val graph = Graph(xyPlotSetosa, xyPlotVersicolor, xyPlotVirginica, key)
  graph.axis(0).label = "Petal length [cm]"
  graph.axis(1).label = "Petal width [cm]"

  Page(graph).show("IrisScatterPlotWithColorMapping")
}
