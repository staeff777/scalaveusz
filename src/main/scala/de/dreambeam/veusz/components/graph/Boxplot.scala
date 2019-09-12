package de.dreambeam.veusz.components.graph

import de.dreambeam.veusz.data.{BoxplotData, Numerical}
import de.dreambeam.veusz.format._
import de.dreambeam.veusz.{Configurable, Executable, GraphItem}

object Boxplot {

  def apply(data: Vector[Vector[Double]]) = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String]) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double]) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], whiskerMode: WhiskerMode.Value) = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], whiskerMode: WhiskerMode.Value) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double], whiskerMode: WhiskerMode.Value) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], whiskerMode: WhiskerMode.Value, fillFraction: Double = 0.75) = {
    val d = new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], whiskerMode: WhiskerMode.Value, fillFraction: Double = 0.75) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical())
    Boxplot(d)
  }

  def apply(data: Vector[Vector[Double]], labels: Vector[String], positions: Vector[Double], whiskerMode: WhiskerMode.Value, fillFraction: Double = 0.75) = {
    val d = new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))
    Boxplot(d)
  }

}

case class Boxplot(var data: BoxplotData,
                   var whiskerMode: WhiskerMode.Value = WhiskerMode.IQP15,
                   var fillFraction: Double = 0.75,
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
                         markersFill: BackgroundConfig = BackgroundConfig())
