package de.dreambeam.veusz.data

object BoxplotData {
  def apply(data: Vector[Vector[Double]],
            labels: Vector[String]) =
    new BoxplotData(data.map(Numerical(_)), labels)
}

case class BoxplotData(data: Vector[Numerical],
                       labels: Vector[String],
                       positions: Option[Numerical] = None,
                       name: String = "") extends Data
{
  override def hashCode(): Int = data.hashCode()
}