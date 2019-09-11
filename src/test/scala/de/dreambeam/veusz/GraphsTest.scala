package de.dreambeam.veusz

import java.io.File
import de.dreambeam.veusz._
import org.scalatest.{FlatSpec, Matchers}
import scala.util.Random

class GraphsTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a configured Graph with multiple Children" in {
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

    val polarGraph = PageItems.PolarGraph(nonOrthPoint, nonOrthFunction)
    polarGraph.minRadius = Some(0)
    polarGraph.maxRadius = Some(3)
    polarGraph.direction = PolarDirection.anticlockwise
    polarGraph.positionOf0 = PolarPositionOf0.bottom
    polarGraph.config.background.color = Colors.LightGrey
    polarGraph.config.border.width = 1 pt()
    polarGraph.config.tickLabels.italic = true
    polarGraph.config.spokeLine.lineStyle = LineStyle.Dotted
    polarGraph.config.radiiLine.lineStyle = LineStyle.Dash1

    val file = new File("veusz/polarGraph.svg")
    polarGraph.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a configured TeraryGraph with multiple Children" in {
    val f1 = TernaryItems.NonOrthFunction("30","a")
    val f2 = TernaryItems.NonOrthFunction("30","b")
    val (a,b,s) = (1 to 100).map(_ => (Random.nextDouble() * 100.0, Random.nextDouble() * 100.0, Random.nextDouble() * 2)).toVector.unzip3
    val p = TernaryItems.NonOrthPoint(a,b,s)
    p.config.plotLine.hide = true
    p.config.markerFill.color = Colors.Magenta
    p.config.markerBorder.hide = true
    val ternaryGraph = PageItems.TernaryGraph(f1, f2, p)

    val file = new File("veusz/ternaryGraph.svg")
    ternaryGraph.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a configured Graph3D with multiple Children" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.1).map(_.toDouble).toVector
    val yData = xData.map(d => Math.sin(d) * 1.25)
    val zData = xData.map(_ * 1.25)
    val p3d = Graph3DItems.Point3D(xData, yData, zData)

    val f3d = Graph3DItems.Function3D(
      x_func = "t*t*t-t*t",
      y_func = "t*t-t",
      z_func = "t")

    val graph3D = Scene3DItems.Graph3D(f3d, p3d)
    graph3D.config.border.color = Colors.DarkGreen
    graph3D.config.back.color = Colors.LightGrey
    graph3D.config.back.hide = false

    val file = new File("veusz/graph3D.png")
    graph3D.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }


  it should "render a configured Scene3D with a Graph3D and one Item" in {
    val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield (x.toDouble, y.toDouble) -> { (Math.sin(0.1 * (x + y))) + 0.5 }).toMap

    val s3d = Graph3DItems.Surface3D(dataset, dataset)
    s3d.config.gridLine.reflectivity = 50

    val graph3D = Scene3DItems.Graph3D(s3d)
    graph3D.config.border.color = Colors.DarkGreen
    graph3D.config.back.color = Colors.LightGrey
    graph3D.config.back.hide = false

    val scene3D = PageItems.Scene3D(graph3D)
    scene3D.xRotation = -30
    scene3D.yRotation = 2
    scene3D.config.lighting1.color = Colors.Red
    scene3D.config.lighting1.intensity = 150

    scene3D.config.lighting2.color = Colors.Yellow
    scene3D.config.lighting2.intensity = 140
    scene3D.config.lighting2.enable = true
    scene3D.config.lighting2.yPosition = -5

    scene3D.config.lighting3.enable = true
    scene3D.config.lighting3.xPosition = -7.8
    scene3D.config.lighting3.yPosition = 8.7
    scene3D.config.lighting3.zPosition = -36.4

    val file = new File("veusz/scene.png")
    scene3D.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }
}
