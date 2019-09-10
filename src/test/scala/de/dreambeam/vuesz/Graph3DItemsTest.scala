package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class Graph3DItemsTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a Point3d" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.1).map(_.toDouble).toVector
    val yData = xData.map(d => Math.sin(d) * 1.25)
    val zData = xData.map(_ * 1.25)
    val yScale = yData.map(_ * 1.51)
    val yColor = xData.map(z => z * 0.1)
    val p3d = Graph3DItems.Point3D(xData, yData, zData, yScale, yColor)
    p3d.config.markerFill.colorMap = ColorMaps.Heat
    val file = new File("veusz/p3d.svg")
    p3d.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Function3D" in {

    val f3d = Graph3DItems.Function3D(
      x_func = "t*t*t-t*t",
      y_func = "t*t-t",
      z_func = "t",
      mode = Function3DMode.XYZ_FNS_T,
      color_func = "t")

    f3d.config.main.lineSteps = 80
    f3d.config.plotLine.colorMap = ColorMaps.Hot_Desaturated
    f3d.config.plotLine.width = 5
    f3d.config.gridLine.width = 2
    f3d.config.surface.hide = true
    val file = new File("veusz/f3d.svg")
    f3d.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Surface3d" in {

    val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield (x.toDouble, y.toDouble) -> { (Math.sin(0.1 * (x + y))) + 0.5 }).toMap

    val s3d = Graph3DItems.Surface3D(dataset, dataset)
    s3d.config.gridLine.reflectivity = 50
    s3d.config.surface.colorMap = ColorMaps.Blue_Darkred
    s3d.config.surface.transparency = 30
    val file = new File("veusz/s3d.svg")
    s3d.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  /**
    * this vis looks ugly since axis min max need to be set to -0,5 and 2,5
    */
  it should "render a Volume3D" in {

    val n = 10
    val gen_seq = for {
      x <- 1 to n
      y <- 1 to n
      z <- 1 to n
    } yield (x.toDouble, y.toDouble, z.toDouble)

    val (x, y, z) = gen_seq.toVector.unzip3
    val c = (0 to (n * n * n - 1)).map(v => v.toDouble / (n * n * n - 1).toDouble).toVector

    val s3d = Graph3DItems.Volume3D(x, y, z, c)
    s3d.config.main.colorMap = "yellow-green-blue"
    s3d.config.main.transparency = 0
    s3d.config.main.reflectivity = 10
    s3d.config.main.fillFactor = 0.7
    val file = new File("veusz/v3d.png")
    s3d.export(file.getAbsolutePath, dpi = 100)
    file should exist
    file.delete()
  }
}
