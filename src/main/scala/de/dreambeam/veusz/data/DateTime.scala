package de.dreambeam.veusz.data

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Calendar

import scala.collection.immutable.ListMap

object DateTime {
  def apply(
             data: Vector[String],
             name: String = ""
           ) =
    new DateTime(data, name)

  def apply(
           data: Vector[String],
           pattern: String,
           name: String = ""
           )
           (calendar: Calendar = {
             val calendar = Calendar.getInstance
             calendar.set(
               calendar.get(Calendar.YEAR),
               calendar.get(Calendar.MONTH),
               calendar.get(Calendar.DAY_OF_MONTH),
               0,
               0,
               0
             )
             calendar
           })
           (offset_calendar: Calendar = {
             val offset_calendar = Calendar.getInstance
             offset_calendar.set(0, 0, 0, 0, 0, 0)
             offset_calendar
           }): DateTime = {

    val year = calendar.get(Calendar.YEAR) + offset_calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + offset_calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH) + offset_calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY) + offset_calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE) + offset_calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND) + offset_calendar.get(Calendar.SECOND)

    implicit def format(i: Int): String = "%02d" format i

    case class Field(pos: Int, name: String, default: String, chronoField: ChronoField, calendarField: Int)

    val pos = ListMap( "yyyy" -> Field(0, "year", year.toInt, ChronoField.YEAR, Calendar.YEAR)
      , "MM" -> Field(1, "month", month.toInt + 1, ChronoField.MONTH_OF_YEAR, Calendar.MONTH) // increment month because it is zero based
      , "dd" -> Field(2, "day", day.toInt, ChronoField.DAY_OF_MONTH, Calendar.DAY_OF_MONTH)
      , "HH" -> Field(3, "hour", hour.toInt, ChronoField.HOUR_OF_DAY, Calendar.HOUR_OF_DAY)
      , "mm" -> Field(4, "minute", minute.toInt, ChronoField.MINUTE_OF_HOUR, Calendar.MINUTE)
      , "ss" -> Field(5, "second", second.toInt, ChronoField.SECOND_OF_MINUTE, Calendar.SECOND)
    )

    val upsilon = pos.map {
      case (key, value) if pattern contains key => key -> (value, true) // mark for replacement
      case (key, value)                         => key -> (value, false) // mark for using default value
    }

    val parser = DateTimeFormatter.ofPattern(pattern)

    val completedDateTimeEntries = data.map { x =>
      val parsed = parser.parse(x)
      val c = upsilon map {
        case (key, (value, true))  => key -> format(parsed.get(value.chronoField) + offset_calendar.get(value.calendarField))
        case (key, (value, false)) => key -> value.default
      }
      s"""${c("yyyy")}-${c("MM")}-${c("dd")}'T'${c("HH")}:${c("mm")}:${c("ss")}"""
    }
    DateTime(completedDateTimeEntries, name)
  }
}

case class DateTime(
                     data: Vector[String],
                     name: String = ""
                   ) extends Data
{
  override def hashCode(): Int = data.hashCode()
}
