package de.dreambeam.veusz.format

object Colors  {
  val Foreground = "foreground"
  val Background = "background"
  val Transparent = "transparent"
  val Auto = "auto"
  val White = "white"
  val Black = "black"
  val Red = "red"
  val Green = "green"
  val Blue = "blue"
  val Cyan = "cyan"
  val Magenta = "magenta"
  val Yellow = "yellow"
  val Grey = "grey"
  val DarkRed = "darkred"
  val DarkGreen = "darkgreen"
  val DarkBlue = "darkblue"
  val DarkCyan = "darkcyan"
  val DarkMagenta = "darkmagenta"
  val LightGrey = "lightgrey"
}

object ColorMapType extends Enumeration {
  val Grey = Value("grey")
  val Blue = Value("blue")
  val Heat = Value("heat")
  //val BlueToDarkOrange = Value("blue-darkorange")
  //val BlueToDarkOrangeStep12 = Value("blue-darkorange-step12")
  val Traffic = Value("traffic-7-25-sync@60")
  val TrafficWithNone = Value("traffic-7-25-sync@60_None@-10")
}