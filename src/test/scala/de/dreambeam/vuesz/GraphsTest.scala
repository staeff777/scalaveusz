package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import de.dreambeam.veusz.components.nonorthgraphs.{PolarGraph, TernaryGraph}
import de.dreambeam.veusz.format.{PolarDirection, PolarPositionOf0}
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class GraphsTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a configured Graph with multiple Children" in {
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

  it should "render a configured PolarGraph with multiple Children" in {
    val nonOrthPoint = PolarItems.NonOrthPoint(Vector(1.0, 10.0), Vector(1.0, 1.0))
    val nonOrthFunction = PolarItems.NonOrthFunction("1000 * sin(a)")
    nonOrthFunction.config.main.steps = 1000

    val polarGraph = PolarGraph(nonOrthPoint, nonOrthFunction)
    polarGraph.minRadius = Some(0)
    polarGraph.maxRadius = Some(3)
    polarGraph.direction = PolarDirection.anticlockwise
    polarGraph.positionOf0 = PolarPositionOf0.bottom
    polarGraph.config.background.color = Colors.LightGrey
    polarGraph.config.border.width = 1 pt()
    polarGraph.config.tickLabels.italic = true  // TODO
    polarGraph.config.spokeLine.lineStyle = LineStyle.Dotted //TODO
    polarGraph.config.radiiLine.lineStyle = LineStyle.Dash1 //TODO
    polarGraph.openInVeusz("polargraph")
  }

  it should "render a configured TeraryGraph with multiple Children" in {

    val ternaryGraph = TernaryGraph()
    ternaryGraph.openInVeusz("ternaryGraph")
  }

}
