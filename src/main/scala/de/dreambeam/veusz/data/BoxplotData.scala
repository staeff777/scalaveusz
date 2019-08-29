package de.dreambeam.veusz.data

object BoxplotData {
  def apply(data: Vector[Vector[Double]]) =
    new BoxplotData(data.map(Numerical(_)), Vector.empty, Numerical())

  def apply(data: Vector[Vector[Double]],
            labels: Vector[String]) =
    new BoxplotData(data.map(Numerical(_)), labels, Numerical())

  def apply(data: Vector[Vector[Double]],
            labels: Vector[String],
            positions: Vector[Double]) =
    new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions))

  def apply(data: Vector[Vector[Double]],
            labels: Vector[String],
            positions: Vector[Double],
            name: String) =
    new BoxplotData(data.map(Numerical(_)), labels, Numerical(positions), name)
}

case class BoxplotData(data: Vector[Numerical],
                       labels: Vector[String],
                       positions: Numerical,
                       name: String = "") extends Data
{
  override def hashCode(): Int = data.hashCode()

  override def isEmpty: Boolean = data.isEmpty
}