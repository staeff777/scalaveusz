package de.dreambeam.veusz.data

import java.time.{LocalDateTime, LocalDate}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Calendar

import scala.collection.immutable.ListMap
import scala.language.implicitConversions

object DateTimeConstructor {

  def format(i: Long): String = "%02d" format i

  def fromLocalDate(data: Vector[LocalDate])
                   (implicit offsetCalendar: Map[String, Int] = Map.empty[String, Int]): DateTime = {
    val offsetData = data
      .map(_.plusYears(offsetCalendar.getOrElse("yyyy", 0).asInstanceOf[Long]))
      .map(_.plusMonths(offsetCalendar.getOrElse("MM", 0).asInstanceOf[Long]))
      .map(_.plusDays(offsetCalendar.getOrElse("dd", 0).asInstanceOf[Long]))

    val years = offsetData.map(_.getYear).map(_.asInstanceOf[Long])
    val months = offsetData.map(_.getMonthValue).map(_.asInstanceOf[Long])
    val days = offsetData.map(_.getDayOfMonth).map(_.asInstanceOf[Long])

    DateTime((for {(y, m, d) <- (years, months, days).zipped} yield { s"${format(y)}-${format(m)}-${format(d)}"} ).toVector, "")
  }

  def fromLocalDateTime(data: Vector[LocalDateTime])
                       (implicit offsetCalendar: Map[String, Int] = Map.empty[String, Int]): DateTime = {
    val offsetData = data
      .map(_.plusYears(offsetCalendar.getOrElse("yyyy", 0).asInstanceOf[Long]))
      .map(_.plusMonths(offsetCalendar.getOrElse("MM", 0).asInstanceOf[Long]))
      .map(_.plusDays(offsetCalendar.getOrElse("dd", 0).asInstanceOf[Long]))
      .map(_.plusHours(offsetCalendar.getOrElse("HH", 0).asInstanceOf[Long]))
      .map(_.plusMinutes(offsetCalendar.getOrElse("mm", 0).asInstanceOf[Long]))
      .map(_.plusSeconds(offsetCalendar.getOrElse("ss", 0).asInstanceOf[Long]))

    val years = offsetData.map(_.getYear).map(_.asInstanceOf[Long])
    val months = offsetData.map(_.getMonthValue).map(_.asInstanceOf[Long])
    val days = offsetData.map(_.getDayOfMonth).map(_.asInstanceOf[Long])
    val hours = offsetData.map(_.getHour).map(_.asInstanceOf[Long])
    val minutes = offsetData.map(_.getMinute).map(_.asInstanceOf[Long])
    val seconds = offsetData.map(_.getSecond).map(_.asInstanceOf[Long])

    //DateTime((for {(y, m, d, h, mi, s) <- (years, months, days, hours, minutes, seconds)} yield { s"${format(y)}-${format(m)}-${format(d)}'T'${format(h)}:${format(mi)}:${format(s)}" }).toVector, "")

    val dates = (years zip months zip days zip hours zip minutes zip seconds) map {
      case (((((y, m), d), h), mi), s) => s"${format(y)}-${format(m)}-${format(d)}'T'${format(h)}:${format(mi)}:${format(s)}"
    }

    DateTime(dates, "")
  }

  def fromString(data: Vector[String])
           (pattern: String = "yyyy-MM-dd")
           (offsetCalendar: Map[String, Int] = Map.empty[String, Int])
           (implicit calendar: LocalDateTime = LocalDateTime.now().`with`(LocalDateTime.MIN)): DateTime = {
    val year = calendar.getYear + offsetCalendar.getOrElse("yyyy", 0)
    val month = calendar.getMonthValue + offsetCalendar.getOrElse("MM", 0)
    val day = calendar.getDayOfMonth + offsetCalendar.getOrElse("dd", 0)
    val hour = calendar.getHour + offsetCalendar.getOrElse("HH", 0)
    val minute = calendar.getMinute + offsetCalendar.getOrElse("mm", 0)
    val second = calendar.getSecond + offsetCalendar.getOrElse("ss", 0)

    implicit def format(i: Int): String = "%02d" format i
    implicit def int2Long(i: Int): Long = i.toLong

    case class Field(pos: Int, name: String, default: Int, chronoField: ChronoField)

    val pos: Map[String, Field] = Map("yyyy" -> Field(0, "year", year, ChronoField.YEAR)
      , "MM" -> Field(1, "month", month, ChronoField.MONTH_OF_YEAR)
      , "dd" -> Field(2, "day", day, ChronoField.DAY_OF_MONTH)
      , "HH" -> Field(3, "hour", hour, ChronoField.HOUR_OF_DAY)
      , "mm" -> Field(4, "minute", minute, ChronoField.MINUTE_OF_HOUR)
      , "ss" -> Field(5, "second", second, ChronoField.SECOND_OF_MINUTE)
    )

    val upsilon = pos.map {
      case (key, value) if pattern contains key => key -> (value, true) // mark for replacement
      case (key, value) => key -> (value, false) // mark for using default value
    }

    val parser = DateTimeFormatter.ofPattern(pattern)

    val completedDateTimeEntries = data.map { x =>
      val parsed = parser.parse(x)
      val c = upsilon map {
        case (key, (value, true)) => key -> parsed.get(value.chronoField)
        case (key, (value, false)) => key -> value.default
      }
      val cal = LocalDateTime.of(c("yyyy"), c("MM"), c("dd"), c("HH"), c("mm"), c("ss"))
        .plusYears(offsetCalendar.getOrElse("yyyy", 0).asInstanceOf[Long])
        .plusMonths(offsetCalendar.getOrElse("MM", 0).asInstanceOf[Long])
        .plusDays(offsetCalendar.getOrElse("dd", 0).asInstanceOf[Long])
        .plusHours(offsetCalendar.getOrElse("HH", 0).asInstanceOf[Long])
        .plusMinutes(offsetCalendar.getOrElse("mm", 0).asInstanceOf[Long])
        .plusSeconds(offsetCalendar.getOrElse("ss", 0).asInstanceOf[Long])

      val cv: Vector[String] = Vector(
          cal.getYear
        , cal.getMonthValue
        , cal.getDayOfMonth
        , cal.getHour
        , cal.getMinute
        , cal.getSecond
      )
        .map(format)

      s"${cv(0)}-${cv(1)}-${cv(2)}'T'${cv(3)}:${cv(4)}:${cv(5)}"
    }

    DateTime(completedDateTimeEntries, "")
  }
}



case class DateTime(data: Vector[String],
                    name: String) extends Data with BarChartData
{
  override def hashCode(): Int = data.hashCode()

  override def isEmpty: Boolean = data.isEmpty
}