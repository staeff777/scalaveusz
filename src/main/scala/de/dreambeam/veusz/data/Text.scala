package de.dreambeam.veusz.data


case class Text(
                 data: Vector[String],
                 name: String = ""
               ) extends Data
{
  override def hashCode(): Int = data.hashCode()

  override def isEmpty: Boolean = data.isEmpty
}