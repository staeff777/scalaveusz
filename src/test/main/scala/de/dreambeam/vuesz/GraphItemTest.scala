package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import de.dreambeam.veusz.format.BarchartFillConfig
import org.scalatest._

import scala.util.Random
class GraphItemTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a XY" in {
    val file = File.createTempFile("scalavuesz_", ".svg")
    file.deleteOnExit()

    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val yScale = xData.map(_ / 1.25)
    val yColor = xData.map( _ => Random.nextDouble())
    val xy = GraphItems.XY(xData, yData, yScale, yColor)
    xy.config.markerFill.colorMap= ColorMaps.Heat
    xy.export(file.getAbsolutePath)
    file should exist
  }

  it should "render a Bar Chart" in {
    val file = File.createTempFile("scalavuesz_", ".png")
   // file.deleteOnExit()
    val positions = (1 to 10).map(_.toDouble).toVector
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble()*10).toVector)

    val barchart = GraphItems.BarChart(lengthData, positions)
    barchart.config.fill = Vector("green","blue","red","cyan","magenta","yellow","darkred","darkgreen","darkblue","darkmagenta").map(c => BarchartFillConfig(color = c))
    barchart.export(file.getAbsolutePath)
    barchart.export("veusz/barchart.svg")
    println(file)
    file should exist
  }


}
