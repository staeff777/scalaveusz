import de.dreambeam.veusz.components.Image

object ImageRenderDemo extends App{
  val dataset = (for (x <- 0 until 100; y <- 0 until 100) yield
    (x.toDouble, y.toDouble) -> (x + y).toDouble
    ).toMap

  val img2d = Image(dataset)
  img2d.config.colorMap = "heat"
  img2d.config.invertColormap = true


   img2d.show("image")
}
