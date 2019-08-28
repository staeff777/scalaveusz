import de.dreambeam.veusz.components.Boxplot
import de.dreambeam.veusz.data.BoxplotData

object BoxplotRenderDemo extends App {

  val d = BoxplotData(Vector.empty, Vector.empty)
  val boxPlot = Boxplot(d)

  boxPlot.show("boxplot")
}
