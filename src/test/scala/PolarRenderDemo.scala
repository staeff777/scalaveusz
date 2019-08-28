import de.dreambeam.veusz.components.{NonOrthPoint, PolarGraph}

object PolarRenderDemo extends App{

  val polar = PolarGraph()

  val nonOrt = NonOrthPoint(Vector.empty, Vector.empty)

  nonOrt.show("nonOrt")

}
