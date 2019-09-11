package de.dreambeam.veusz

import java.io.File

import de.dreambeam.veusz._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class GridTest extends FlatSpec with Matchers{

  "scalaveusz" should "render a Grid with multiple GridItems" in {

    val nonOrthFunction = PolarItems.NonOrthFunction("1000 * sin(a)")
    nonOrthFunction.config.main.steps = 1000
    val polarGraph = PageItems.PolarGraph( nonOrthFunction)

    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val graph = PageItems.Graph(xy1)

    val (a,b,s) = (1 to 100).map(_ => (Random.nextDouble() * 100.0, Random.nextDouble() * 100.0, Random.nextDouble() * 2)).toVector.unzip3
    val p = TernaryItems.NonOrthPoint(a,b,s)
    val ternaryGraph = PageItems.TernaryGraph( p)

    val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield (x.toDouble, y.toDouble) -> { (Math.sin(0.1 * (x + y))) + 0.5 }).toMap
    val s3d = Graph3DItems.Surface3D(dataset, dataset)
    val graph3D = Scene3DItems.Graph3D(s3d)
    val scene3D = PageItems.Scene3D(graph3D)

    val grid = PageItems.Grid(2,2, Vector(1.1, 1), Vector(1.2,2), polarGraph, graph, ternaryGraph, scene3D)
    grid.config.leftMargin = 0.0 cm()
    grid.config.bottomMargin = 0.5 cm()

    val file = new File("veusz/grid.png")
    grid.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  "scalaveusz" should "render a Grid with multiple WrappedGridItems" in {

    val nonOrthFunction = PolarItems.NonOrthFunction("1000 * sin(a)")
    nonOrthFunction.config.main.steps = 1000


    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)

    val (a,b,s) = (1 to 100).map(_ => (Random.nextDouble() * 100.0, Random.nextDouble() * 100.0, Random.nextDouble() * 2)).toVector.unzip3
    val p = TernaryItems.NonOrthPoint(a,b,s)

    val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield (x.toDouble, y.toDouble) -> { (Math.sin(0.1 * (x + y))) + 0.5 }).toMap
    val s3d = Graph3DItems.Surface3D(dataset, dataset)

    val grid = PageItems.Grid(2,2, Vector(1.1, 1), Vector(1.2,2), nonOrthFunction, xy1, p, s3d)
    grid.config.leftMargin = 0.0 cm()
    grid.config.bottomMargin = 0.5 cm()

    val file = new File("veusz/wrappedgrid.png")
    grid.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }
}
