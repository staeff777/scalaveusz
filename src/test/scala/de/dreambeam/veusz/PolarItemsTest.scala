package de.dreambeam.veusz
import org.scalatest._
import java.io.File
import de.dreambeam.veusz._

class PolarItemsTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a NonOrthPoint" in {
    val nonOrthPoint = PolarItems.NonOrthPoint(Vector(1.0, 10.0), Vector(1.0, 1.0))
    val file = new File("veusz/nonorthpoint.svg")
    nonOrthPoint.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

  it should "render a NonOrthFunction" in {
    val nonOrthFunction = PolarItems.NonOrthFunction("1000 * sin(a)")
    nonOrthFunction.config.main.steps = 100
    nonOrthFunction.config.plotLine.color = Colors.DarkMagenta
    nonOrthFunction.config.plotLine.width = 3 pt()
    val file = new File("veusz/nonorthfunc.svg")
    nonOrthFunction.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }




}