package Colors

/**
  * Created by Kaufmann on 08.12.2016.
  * based on http://stackoverflow.com/questions/1168260/algorithm-for-generating-unique-colors
  */


object UniqueLists {

  def apply() = new UniqueColors()

  def apply(num:Int)(implicit list:Vector[String]  ) = list(num % list.size)

  val LONGCOLORS = Vector(//"#000000",  removed black
                      "#00FF00",
                      "#0000FF",
                      "#FF0000",
                      "#01FFFE",
                      "#FFA6FE",
                      "#FFDB66",
                      "#006401",
                      "#010067",
                      "#95003A",
                      "#007DB5",
                      "#FF00F6",
                      //"#FFEEE8",
                      "#774D00",
                      "#90FB92",
                      "#0076FF",
                      //"#D5FF00",
                      "#FF937E",
                      "#6A826C",
                      "#FF029D",
                      "#FE8900",
                      "#7A4782",
                      "#7E2DD2",
                      "#85A900",
                      "#FF0056",
                      "#A42400",
                      "#00AE7E",
                      "#683D3B",
                      "#BDC6FF",
                      "#263400",
                      "#BDD393",
                      "#00B917",
                      "#9E008E",
                      "#001544",
                      "#C28C9F",
                      "#FF74A3",
                      "#01D0FF",
                      "#004754",
                      "#E56FFE",
                      "#788231",
                      "#0E4CA1",
                      "#91D0CB",
                      "#BE9970",
                      "#968AE8",
                      "#BB8800",
                      "#43002C",
                      //"#DEFF74",
                      "#00FFC6",
                      "#FFE502",
                      "#620E00",
                      "#008F9C",
                      //"#98FF52",
                      "#7544B1",
                      "#B500FF",
                      "#00FF78",
                      "#FF6E41",
                      "#005F39",
                      "#6B6882",
                      "#5FAD4E",
                      "#A75740",
                      "#A5FFD2",
                      "#FFB167",
                      "#009BFF",
                      "#E85EBE")


  val SHORTCOLORS = Vector(

    "#5D478B", // 10
    "#7B7922",  // 4
    "#6F4242",  // 1
    "#528B8B",  // 7
    "#A52A2A",  // 2
    "#808000",  // 5
    "#00688B",   //8
    "#CD1076",  // 11
    "#CD6600",  // 3
    "#385E0F",  // 6
    "#236B8E"  // 9
  )

  val LINES = Vector(
    "dashed",
    "dash-dot-dot",
    "dotted",
    "dash-dot"
  )
}


case class UniqueColors(val colors:Vector[String] = UniqueLists.LONGCOLORS) {
  var i = 0

  def next() = {
    val color = UniqueLists(i)(colors)
    i += 1
    color
  }
}


case class UniqueLines(val lines:Vector[String] = UniqueLists.LINES) {
  var i = 0

  def next() = {
    val color = UniqueLists(i)(lines)
    i += 1
    color
  }
}