package de.dreambeam.veusz.examples
import java.io.File

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

import de.dreambeam.veusz.components.{Boxplot, Graph}
import de.dreambeam.veusz.data.{BoxplotData}
import de.dreambeam.veusz.format.{AxisMode}

object IrisBoxPlot extends App {

  // Read CSV
  val reader = new File("data/iris/iris.data").asUnsafeCsvReader[IrisRow](rfc.withoutHeader).toVector

  val sepalLength = reader map(_.sepalLength)
  val sepalWidth = reader map(_.sepalWidth)
  val petalLength = reader map(_.petalLength)
  val petalWidth = reader map(_.petalWidth)

  val boxPlotData = BoxplotData(
    data=Vector(sepalLength, sepalWidth, petalLength, petalWidth),
    labels=Vector("Sepal.Length", "Sepal.Width", "Petal.Length", "Petal.Width")
  )

  val boxPlot = Boxplot(boxPlotData)
  val graph = Graph(boxPlot)
  graph.axis(0).mode = AxisMode.Labels

  graph.show("IrisBoxPlot")
}