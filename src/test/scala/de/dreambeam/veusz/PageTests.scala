package de.dreambeam.veusz

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

class PageTests extends FlatSpec with Matchers {

  "scalaveusz" should "render a Page with a Graph" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val graph = PageItems.Graph(xy1)
    val p = DocumentItems.Page(graph)
    p.config.width = 20 cm ()
    p.config.height = 30 cm ()

    val file = new File("veusz/pagetest.svg")
    graph.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  "scalaveusz" should "render a Page with PageItems Graph" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val graph = PageItems.Graph(xy1)

    val label = PageItems.Label("Hello Veusz", 0.2, 0.9)
    label.config.text.font = "Verdana"
    label.config.text.color = Colors.White
    label.config.background.color = Colors.Black
    label.config.border.color = Colors.DarkMagenta

    val rect = PageItems.Shapes.Rectangle(0.35, 0.65, 0.3, 0.3, rotate = 45)
    rect.config.border.style = LineStyle.Dotted

    val ellipse = PageItems.Shapes.Ellipse(Vector(0.7), Vector(0.9), Vector(0.2), Vector(0.1))
    ellipse.config.fill.color = Colors.DarkGreen
    ellipse.config.fill.fillStyle = FillStyle.DoubleDiagonalCross
    ellipse.config.fill.hide = false

    val line = PageItems.Shapes.LineLengthAngle(Vector(0.4),Vector(0.24), Vector(0.2))
    line.config.main.arrowLeft = ArrowType.ArrowReverse
    line.config.main.arrowRight = ArrowType.Arrow
    line.config.arrowFill.color = Colors.DarkRed
    val imageFile = PageItems.Shapes.ImageFile("src/test/resources/logo.png", xPosition = 0.8, yPosition = 0.23, width = 0.3, height = 0.2)

    val polygon = PageItems.Shapes.Polygon(Vector(
      (0.7,0.5),
      (0.7,0.6),
      (0.8,0.6),
      (0.9,0.6),
      (0.9,0.4),
      (0.8,0.55),
    ))
    polygon.config.fill.color = Colors.DarkMagenta

    val p = DocumentItems.Page(label, imageFile, rect,ellipse,line,polygon, graph)

    val file = new File("veusz/pagetest_withMore.svg")
    p.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Page with a WrappedPageItem" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)

    val p1 = DocumentItems.Page(xy1)

    val file = new File("veusz/documenttest.pdf")
    p1.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }
}
