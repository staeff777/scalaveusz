import de.dreambeam.veusz.components.Boxplot
import de.dreambeam.veusz.data.{BoxplotData, Numerical}

object BoxplotRenderDemo extends App {

  val d = BoxplotData(Vector(Numerical(Vector(1,2,3)), Numerical(Vector(1,2,4,5))), Vector("A","B"), Numerical(Vector(2,4,8)))
  val boxPlot = Boxplot(d)
  boxPlot.setGlobalVueszPath("C:\\Program Files (x86)\\Veusz\\veusz.exe")
  //boxPlot.show("boxplot")
  boxPlot.export("c:/temp/box.png", dpi = 10, antialias= false, backcolor = "#00ffff")
}
