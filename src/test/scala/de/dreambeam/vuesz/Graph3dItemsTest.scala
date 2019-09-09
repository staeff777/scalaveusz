package de.dreambeam.vuesz

import java.io.File

import de.dreambeam.veusz._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class Graph3dItemsTest extends FlatSpec with Matchers {

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
}
