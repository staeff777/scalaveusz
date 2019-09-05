package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent}
import de.dreambeam.veusz.format.SizeUnit
import de.dreambeam.veusz.format.SizeUnits._

case class Grid (children: Vector[GridItem],
                 rows: Int = 2,
                 columns: Int = 2,
                 scaleRows: String = "",
                 scaleCols: String = "",
                 var name: String = "grid")
  extends PageItem
    with GridItem
    with Configurable
    with Executable
    with Parent
{
  val group = "grid"
  var config: GridConfig = GridConfig()
}

case class GridConfig(var hide: Boolean = false,
                      var leftMargin: SizeUnit = 1.7 cm,
                      var rightMargin: SizeUnit = 0 cm,
                      var topMargin: SizeUnit = 0.2 cm,
                      var bottomMargin: SizeUnit = 1.7 cm,
                      var internalMargin: SizeUnit = 0 cm)