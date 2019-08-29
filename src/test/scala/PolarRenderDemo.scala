import de.dreambeam.veusz.components.{NonOrthPoint, PolarGraph}

object PolarRenderDemo extends App{


  val nonOrt = NonOrthPoint(Vector(1.0, 10.0), Vector(1.0, 1.0))

  nonOrt.openInVeusz("nonOrt")

}
