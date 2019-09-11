package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent}
import de.dreambeam.veusz.format.SizeUnit
import de.dreambeam.veusz.format.SizeUnits._

object Grid {
  def apply(children: GridItem*): Grid = Grid(children = children.toVector)

  def apply(name: String, children: GridItem*): Grid = Grid(name = name, children = children.toVector)

  def apply(rows: Int, columns: Int, children: GridItem*): Grid = Grid(rows = rows, columns = columns, children = children.toVector)

  def apply(rows: Int, columns: Int, name: String, children: GridItem*): Grid = Grid(rows = rows, columns = columns, name = name, children = children.toVector)

  def apply(rows: Int, columns: Int, scaleRows: Vector[Double], scaleCols: Vector[Double], children: GridItem*): Grid =
    Grid(rows, columns, scaleRows, scaleCols, children = children.toVector)

  def apply(rows: Int, columns: Int, scaleRows: Vector[Double], scaleCols: Vector[Double], name: String, children: GridItem*): Grid =
    Grid(rows, columns, scaleRows, scaleCols, name, children = children.toVector)

  def apply(rows: Int = 2, columns: Int = 2, scaleRows: Vector[Double] = Vector.empty, scaleCols: Vector[Double] = Vector.empty, name: String = "grid", children: Vector[GridItem]): Grid =
    new Grid(rows, columns, scaleRows, scaleCols, name, children, GridConfig())
}

case class Grid(var rows: Int, var columns: Int, var scaleRows: Vector[Double], var scaleCols: Vector[Double], var name: String, var children: Vector[GridItem], var config: GridConfig)
    extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "grid"
}

case class GridConfig(var hide: Boolean = false,
                      var leftMargin: SizeUnit = 1.7 cm,
                      var rightMargin: SizeUnit = 0 cm,
                      var topMargin: SizeUnit = 0.2 cm,
                      var bottomMargin: SizeUnit = 1.7 cm,
                      var internalMargin: SizeUnit = 0 cm)
