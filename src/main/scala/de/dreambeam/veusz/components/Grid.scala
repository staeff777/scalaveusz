package de.dreambeam.veusz.components

import de.dreambeam.veusz.{Configurable, Executable, GridItem, PageItem, Parent, WrappedGridItem}
import de.dreambeam.veusz.format.SizeUnit
import de.dreambeam.veusz.format.SizeUnits._

object Grid {
  def apply(children: WrappedGridItem*): Grid = Grid(children = children.toVector)

  def apply(name: String, children: WrappedGridItem*): Grid = Grid(name = name, children = children.toVector)

  def apply(rows: Int, columns: Int, children: WrappedGridItem*): Grid = Grid(rows = rows, columns = columns, children = children.toVector)

  def apply(rows: Int, columns: Int, name: String, children: WrappedGridItem*): Grid = Grid(rows = rows, columns = columns, name = name, children = children.toVector)

  def apply(rows: Int, columns: Int, scaleRows: Vector[Double], scaleCols: Vector[Double], children: WrappedGridItem*): Grid =
    Grid( children = children.toVector, rows, columns, scaleRows, scaleCols)

  def apply(rows: Int, columns: Int, scaleRows: Vector[Double], scaleCols: Vector[Double], name: String, children: WrappedGridItem*): Grid =
    Grid(children = children.toVector, rows, columns, scaleRows, scaleCols, name)

}

case class Grid( var children: Vector[WrappedGridItem],
                 var rows: Int = 2,
                var columns: Int = 2,
                var scaleRows: Vector[Double] = Vector.empty,
                var scaleCols: Vector[Double] = Vector.empty,
                var name: String = "grid",
                var config: GridConfig = GridConfig())
    extends PageItem with GridItem with Configurable with Executable with Parent {
  val group = "grid"
}

case class GridConfig(var hide: Boolean = false,
                      var leftMargin: SizeUnit = 1.7 cm,
                      var rightMargin: SizeUnit = 0 cm,
                      var topMargin: SizeUnit = 0.2 cm,
                      var bottomMargin: SizeUnit = 1.7 cm,
                      var internalMargin: SizeUnit = 0 cm)
