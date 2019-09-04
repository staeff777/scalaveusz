package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import de.dreambeam.veusz.data.{BoxplotData, Numerical}
import de.dreambeam.veusz.format.BarchartFillConfig
import org.scalatest._

import scala.util.Random

class GraphItemTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a XY" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val yScale = xData.map(_ / 1.25)
    val yColor = xData.map(_ => Random.nextDouble())
    val xy = GraphItems.XY(xData, yData, yScale, yColor)
    xy.config.markerFill.colorMap = ColorMaps.Heat
    val file = new File("veusz/xy.svg")
    xy.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Bar Chart" in {
    val positions = (1 to 10).map(_.toDouble).toVector
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble() * 10).toVector)

    val barchart = GraphItems.BarChart(lengthData, positions)
    barchart.config.fill = Vector("green", "blue", "red", "cyan", "magenta", "yellow", "darkred", "darkgreen", "darkblue", "darkmagenta").map(c => BarchartFillConfig(color = c))
    val file = new File("veusz/barchart.svg")
    barchart.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Function" in {
    val positions = (1 to 10).map(_.toDouble).toVector
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble() * 10).toVector)
    val function = components.Function("sin(x)")
    function.config.plotLine.color = Colors.Blue
    val file = new File("veusz/function.svg")
    function.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Boxplot" in {
    val d = BoxplotData(Vector(Numerical(Vector(1,2,3)), Numerical(Vector(1,2,4,5))), Vector("A","B"), Numerical(Vector(2,4,8)))
    val boxPlot = GraphItems.Boxplot(d)
    boxPlot.config.fill.color = Colors.DarkMagenta
    val file = new File("veusz/boxplot.svg")
    boxPlot.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Vectorfield" in {
    val dataset = (for (x <- 0 until 10; y <- 0 until 10) yield
      (x.toDouble, y.toDouble) -> ((x + y).toDouble/10)
      ).toMap

    val dataset2 = (for (x <- 0 until 10; y <- 0 until 10) yield
      (x.toDouble, y.toDouble) -> {((x + y)/10).toDouble + Random.nextDouble()}
      ).toMap

    val vectorfield = GraphItems.VectorField(dataset, dataset2)
    vectorfield.config.main.arrowFront = ArrowType.ArrowNarrow
    vectorfield.config.main.arrowBack = ArrowType.Bar
    val file = new File("veusz/vectorfield.svg")
    vectorfield.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

}
