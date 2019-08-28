package de.dreambeam.veusz.data

object Numerical {
  def apply(
             data: Vector[Double] = Vector.empty,
             name: String = "",
             symErrors: Option[Vector[Double]] = None,
             negErrors: Option[Vector[Double]] = None,
             posErrors: Option[Vector[Double]] = None
           ) =
    new Numerical(data, name, symErrors, negErrors, posErrors)

}

case class Numerical(
                      data: Vector[Double] = Vector.empty,
                      name: String = "",
                      symErrors: Option[Vector[Double]] = None,
                      negErrors: Option[Vector[Double]] = None,
                      posErrors: Option[Vector[Double]] = None
                    ) extends Data
{
  override def hashCode(): Int =
    (31 * data.hashCode) ^ (59 * symErrors.hashCode) ^ (283 * negErrors.hashCode) ^ (911 * posErrors.hashCode)

  override def isEmpty: Boolean = data.isEmpty
}
