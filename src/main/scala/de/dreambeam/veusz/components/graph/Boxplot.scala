package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.{BoxplotData, Numerical, Text}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object Boxplot {

  def apply(data: Vector[Vector[Double]]): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String]): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double]): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], whiskerMode: WhiskerMode.Value): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d, whiskerMode)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], whiskerMode: WhiskerMode.Value): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d, whiskerMode)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double], whiskerMode: WhiskerMode.Value): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d, whiskerMode)
  }

  def apply(data: Vector[Vector[Double]], whiskerMode: WhiskerMode.Value, fillFraction: Double ): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d, whiskerMode, fillFraction,  Text(Vector.empty))
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], whiskerMode: WhiskerMode.Value, fillFraction: Double ): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d, whiskerMode, fillFraction,  Text(Vector.empty))
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double], whiskerMode: WhiskerMode.Value, fillFraction: Double): Boxplot = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d, whiskerMode, fillFraction, Text(Vector.empty))
  }

}

case class Boxplot(var data: BoxplotData,
                   var whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
                   var fillFraction: Double = 0.75,
                   var labels: Text = Text(Vector.empty),
                   var name: String = "boxplot",
                   var config: BoxplotConfig = BoxplotConfig())
    extends GraphItem with Configurable with Executable {
  val group = "boxplot"

}

case class BoxplotConfig(main: BoxplotMainConfig = BoxplotMainConfig(),
                         fill: BoxplotFillConfig = BoxplotFillConfig(),
                         border: BorderConfig = BorderConfig(),
                         whiskerLine: de.dreambeam.veusz.format.LineStyleConfig = de.dreambeam.veusz.format.LineStyleConfig(),
                         markersBorder: BorderConfig = BorderConfig(),
                         markersFill: FillConfig = FillConfig())
