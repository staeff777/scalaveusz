package de.dreambeam.vuesz
import org.scalatest._
import java.io.File
import de.dreambeam.veusz._

class PolarItemsTest extends FlatSpec with Matchers {

  "Scala Veusz" should "render a NonOrthPoint" in {
    val nonOrthPoint = PolarItems.NonOrthPoint(Vector(1.0, 10.0), Vector(1.0, 1.0))

    nonOrthPoint.openInVeusz()
  }



}