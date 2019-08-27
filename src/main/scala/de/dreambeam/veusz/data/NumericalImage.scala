package de.dreambeam.veusz.data

case class NumericalImage(map: Map[(Double, Double), Double] = Map.empty, name: String = "")  extends Data{
  override def hashCode(): Int = map.hashCode()
}
