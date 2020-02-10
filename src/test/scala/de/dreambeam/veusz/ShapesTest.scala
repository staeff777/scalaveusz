package de.dreambeam.veusz

import java.io.File
import de.dreambeam.veusz._
import de.dreambeam.veusz.data.Numerical
import org.scalatest._

import scala.util.Random

class ShapesTest extends FlatSpec with Matchers {

  "scalaveusz" should "render a Line" in {
    val line = LinePoint2Point(xPosition = 0, yPosition = 0, xPosition2 = 1, yPosition2 = 1)
    val file = new File("veusz/line.svg")
    line.export(file.getAbsolutePath)
    file should exist
    file.delete()
  }

}
