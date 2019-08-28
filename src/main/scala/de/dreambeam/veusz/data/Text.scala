package de.dreambeam.veusz.data

object Text {
  def apply(
             data: Vector[String],
             name: String = ""
           ) =
    new Text(data, name)
}

case class Text(
                 data: Vector[String],
                 name: String = ""
               ) extends Data
{
  override def hashCode(): Int = data.hashCode()

  override def isEmpty: Boolean = data.isEmpty
}