package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class GraphsTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a configured Graph" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val xy2 = GraphItems.XY(xData, yData.map(Math.sin(_)))
    val graph = PageItems.Graph(xy1, xy2)
    graph.config.background.color = Colors.LightGrey
    graph.config.background.fillStyle = FillStyle.Cross
    graph.config.background.backColor = Colors.LightGrey
    graph.config.background.backTransparency = 80
    graph.config.background.hide = false

    val file = new File("veusz/graph.svg")
    graph.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

}
