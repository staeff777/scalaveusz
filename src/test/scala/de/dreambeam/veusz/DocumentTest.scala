package de.dreambeam.veusz

import java.io.File

import org.scalatest.{FlatSpec, Matchers}
import de.dreambeam.veusz._
class DocumentTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a MultipageDocument" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val xy2 = GraphItems.XY(xData, yData.map(Math.sin(_)))

    val p1 = DocumentItems.Page(PageItems.Graph(xy1))
    val p2 = DocumentItems.Page(PageItems.Graph(xy2))

    val d = V.Document(p1,p2)
    val file = new File("veusz/documenttest.pdf")
    d.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a MultipageDocument using wrapped Items" in {
    val xData = (BigDecimal(1.0) to 10.0 by 0.5).map(_.toDouble).toVector
    val yData = xData.map(_ * 1.25)
    val xy1 = GraphItems.XY(xData, yData)
    val xy2 = GraphItems.XY(xData, yData.map(Math.sin(_)))

    val d = V.Document(xy1, xy2)
    val file = new File("veusz/documenttest.pdf")
    d.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

}
