package de.dreambeam.veusz.components

import de.dreambeam.veusz._
import de.dreambeam.veusz.util.RenderTools._
import de.dreambeam.veusz.util.SizeUnit
import de.dreambeam.veusz.util.SizeUnits._

object Grid {

  val defaultName = "grid"

  def apply() =
    new Grid(children = None)

  def apply(name: String) =
    new Grid(name, None)

  def apply(children: Vector[GridItem],
            rows: Int = 2,
            columns: Int = 2,
            scaleRows: String = "",
            scaleCols: String = "") =
    new Grid(defaultName, Some(children), rows, columns, scaleRows, scaleCols)
}

class Grid private (val name: String = Grid.defaultName,
                    val children: Option[Vector[GridItem]],
                    val rows: Int = 2,
                    val columns: Int = 2,
                    val scaleRows: String = "",
                    val scaleCols: String = "")
  extends PageItem
    with GridItem
    with Configurable
    with Executable
{
  val group = "grid"

  object config {
    var hide: Boolean = false
    var leftMargin: SizeUnit = 1.7 cm
    var rightMargin: SizeUnit = 0 cm
    var topMargin: SizeUnit = 0.2 cm
    var bottomMargin: SizeUnit = 1.7 cm
    var internalMargin: SizeUnit = 0 cm
  }
}
