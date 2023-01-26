package de.dreambeam.veusz
import org.scalatest._
import java.io.File

import de.dreambeam.veusz._

import scala.util.Random

class PolarItemsTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a Polar NonOrthPoint" in {
    val nonOrthPoint = PolarGraphItems.NonOrthPoint(Vector(1.0, 10.0), Vector(1.0, 1.0))

    val file = new File("veusz/nonorthpoint.svg")
    nonOrthPoint.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Polar NonOrthFunction" in {
    val nonOrthFunction = PolarGraphItems.NonOrthFunction("1000 * sin(a)")
    nonOrthFunction.config.main.steps = 100
    nonOrthFunction.config.plotLine.color = Colors.DarkMagenta
    nonOrthFunction.config.plotLine.width = 3 pt()

    val file = new File("veusz/nonorthfunc.svg")
    nonOrthFunction.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  "scalaveusz" should "render a Ternary NonOrthPoint" in {

    val (a,b,s) = (1 to 100).map(_ => (Random.nextDouble() * 100.0, Random.nextDouble() * 100.0, Random.nextDouble() * 2)).toVector.unzip3
    val p = TernaryGraphItems.NonOrthPoint(a,b,s)
    p.config.plotLine.hide = true
    p.config.markerFill.color = Colors.Magenta
    p.config.markerBorder.hide = true

    val file = new File("veusz/ternaryPoint.svg")
    p.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a Ternary NonOrthFunction" in {
    val f1 = TernaryGraphItems.NonOrthFunction("30","a")

    val file = new File("veusz/nonorthfunc.svg")
    f1.exportImage(file.getAbsolutePath)
    file should exist
    file.delete()
  }


}
