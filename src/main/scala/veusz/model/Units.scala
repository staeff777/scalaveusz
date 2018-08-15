package veusz.model


sealed trait SizeUnit {
  def value:Double
  def unit:String
  def getValue: String = value + unit

  override def toString(): String = getValue
}

case class Point(value:Double) extends  SizeUnit {
  override def unit: String = "pt"
}

case class Centimeter(value:Double) extends  SizeUnit {
  override def unit: String = "cm"
}



object SizeUnits{
  implicit class DoubleWithUnits(v:Double){
    def cm() = Centimeter(v)
    def pt() = Point(v)
  }

  //Convert Double to Point on Default
  implicit def double2Point(x: Double) = Point(x)

}
