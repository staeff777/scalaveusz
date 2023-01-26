package de.dreambeam.veusz

import java.io.File

import de.dreambeam.veusz._
import de.dreambeam.veusz.data.Numerical

import org.scalatest._

import scala.util.Random

class GraphItemTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a XY" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val yScale = xData.map(_ / 1.25)
    val yColor = xData.map(_ => Random.nextDouble())
    val xy = GraphItems.XY(xData, yData, yScale, yColor)
    xy.config.markerFill.colorMap = ColorMaps.Heat
    val file = new File("veusz/xy.svg")
    xy.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  "scalaveusz" should "render a XY with Labels" in {
    val xData = (BigDecimal(1.0) to 5 by 1).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val labels = Vector("one", "two", "three", "four")
    val xy1 = GraphItems.XY(xData, yData, labels = labels)

    val graph = PageItems.Graph(xy1)
    graph.config.background.color = Colors.LightGrey
    graph.config.background.fillStyle = FillStyle.Cross
    graph.config.background.backColor = Colors.LightGrey
    graph.config.background.backTransparency = 80
    graph.config.background.hide = false

    graph.axis(0).mode = AxisMode.Labels

    val file = new File("veusz/xy_labelx.svg")
    graph.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Bar Chart" in {
    val positions = (1 to 10).map(_.toDouble).toVector
    val labels = Vector("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble() * 10).toVector)

    val barchart = GraphItems.Barchart(lengthData, positions, labels = labels)
    barchart.config.fill = Vector("green", "blue", "red", "cyan", "magenta", "yellow", "darkred", "darkgreen", "darkblue", "darkmagenta").map(c => BarchartFillConfig(color = c))
    val file = new File("veusz/barchart.svg")
    barchart.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  //text not visible on x axis, x axis should be set to 'labels'
  it should "render a Bar Chart with text" in {
    val positions = (1 to 10).map(_.toDouble).toVector
    val labels = Vector("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble() * 10).toVector)

    val barchart = GraphItems.Barchart(Vector(positions), positions, labels = labels)
    barchart.config.fill = Vector("green", "blue", "red", "cyan", "magenta", "yellow", "darkred", "darkgreen", "darkblue", "darkmagenta").map(c => BarchartFillConfig(color = c))
    val file = new File("veusz/barchart.svg")
    barchart.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Fit Function" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25 * (Random.nextDouble() + 0.5))
    val fit = GraphItems.Fit(xData, yData, "c+b*x+a*x*x", Map("a" -> 1.0, "b" -> 1.0, "c" -> 1.0))
    fit.config.plotLine.color = Colors.DarkGreen
    fit.saveAsVeusz("fit")
    //TODO export hangs with Action Fit, but only when started from within Java
    /* val file = new File("veusz/fit.svg")
    fit.export(file.getAbsolutePath)
    file should exist
    file.delete()*/
  }

  it should "render a Function" in {
    val positions = (1 to 10).map(_.toDouble).toVector
    val lengthData = positions.map(v => (1 to 10).map(_ * Random.nextDouble() * 10).toVector)
    val function = GraphItems.Function("sin(x)")
    function.config.plotLine.color = Colors.Blue
    val file = new File("veusz/function.svg")
    function.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Boxplot" in {
    val d = BoxplotData(Vector(Numerical(Vector(1, 2, 3)), Numerical(Vector(1, 2, 4, 5))), Vector("A", "B"), Numerical(Vector(2, 4, 8)))
    val boxPlot = GraphItems.Boxplot(d)
    boxPlot.config.fill.color = Colors.DarkMagenta
    val file = new File("veusz/boxplot.svg")
    boxPlot.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render an Image" in {
    val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield (x.toDouble, y.toDouble) -> (x + y).toDouble).toMap

    val img2d = GraphItems.Image(dataset)
    img2d.config.colorMap = "heat"
    img2d.config.invertColormap = true
    val file = new File("veusz/image.svg")
    img2d.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a colorbar" in {
    val colorbar = GraphItems.Colorbar(direction = Direction.Vertical)
    val file = new File("veusz/image.svg")
    colorbar.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Vectorfield" in {
    val dataset = (for (x <- 0 until 10; y <- 0 until 10) yield (x.toDouble, y.toDouble) -> ((x + y).toDouble / 10)).toMap

    val dataset2 = (for (x <- 0 until 10; y <- 0 until 10) yield (x.toDouble, y.toDouble) -> { ((x + y) / 10).toDouble + Random.nextDouble() }).toMap

    val vectorfield = GraphItems.VectorField(dataset, dataset2)
    vectorfield.config.main.arrowFront = ArrowType.ArrowNarrow
    vectorfield.config.main.arrowBack = ArrowType.Bar
    val file = new File("veusz/vectorfield.svg")
    vectorfield.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

}
