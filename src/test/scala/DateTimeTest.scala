import java.time.{LocalDate, LocalDateTime}
import java.util.Calendar

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.Inspectors._
import de.dreambeam.veusz.data.DateTimeConstructor

class DateTimeTest extends FlatSpec with Matchers {

  def fixture =
    new {
      val dateString = Vector("02/12/2019")
      val dateJava = LocalDate.of(2019, 2, 12)
      val dateTimeJava = LocalDateTime.of(2019, 2, 12, 12, 30, 50)
      val pattern = "MM/dd/yyyy"
    }

  "DateTimeConstructor" should "correctly add positive year offset" in {
    val f = fixture
    val offsetCalendar = Map("yyyy"->2000)
    val dt = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar)
    dt.data.mkString should startWith ("4019-02-12")
  }

  it should "fill in missing date values from string" in {
    val datesWithPatterns = Vector(("12/2", "dd/M"), ("12", "dd"))

    forAll (datesWithPatterns) { dwp =>
      val date = Vector(dwp._1)
      val pattern = dwp._2
      val dtc = DateTimeConstructor.fromString(date)(pattern)()(LocalDateTime.of(2019, 2, 12, 0, 0, 0))
      dtc.data.mkString should equal ("2019-02-12'T'00:00:00")
    }
  }

  it should "correctly substract negative year offset from string" in {
    val f = fixture
    val offsetCalendar = Map("yyyy"->(-5))

    val dt = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar)
    dt.data.mkString should startWith ("2014-02-12")
  }

  it should "correctly add positive month offset from string" in {
    val f = fixture
    val offsetCalendar = Map("MM"->2)

    val dt = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar)
    dt.data.mkString should startWith("2019-04-12")
  }

  it should "correctly handle data overflows from string" in {
    val f = fixture
    val offsetCalendar1 = Map("MM"->11)
    val offsetCalendar2 = Map("dd"->5)
    val offsetCalendar3 = Map("dd"->30)
    val dt1 = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar1)
    val dt2 = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar2)
    val dt3 = DateTimeConstructor.fromString(f.dateString)(f.pattern)(offsetCalendar3)
    dt1.data.mkString should startWith("2020-01-12")
    dt2.data.mkString should startWith("2019-02-17")
    dt3.data.mkString should startWith("2019-03-14")
  }

  it should "correctly substract negative year offset from java.time.LocalDate" in {
    val f = fixture
    val offsetCalendar = Map("yyyy"->(-5))

    val dt = DateTimeConstructor.fromLocalDate(Vector(f.dateJava))(offsetCalendar)
    dt.data.mkString should startWith ("2014-02-12")
  }

  it should "correctly add positive month offset from java.time.LocalDate" in {
    val f = fixture
    val offsetCalendar = Map("MM"->2)

    val dt = DateTimeConstructor.fromLocalDate(Vector(f.dateJava))(offsetCalendar)
    dt.data.mkString should startWith("2019-04-12")
  }

  it should "correctly handle data overflows from java.time.LocalDate" in {
    val f = fixture
    val offsetCalendar1 = Map("MM"->11)
    val offsetCalendar2 = Map("dd"->5)
    val offsetCalendar3 = Map("dd"->30)
    val dt1 = DateTimeConstructor.fromLocalDate(Vector(f.dateJava))(offsetCalendar1)
    val dt2 = DateTimeConstructor.fromLocalDate(Vector(f.dateJava))(offsetCalendar2)
    val dt3 = DateTimeConstructor.fromLocalDate(Vector(f.dateJava))(offsetCalendar3)
    dt1.data.mkString should startWith("2020-01-12")
    dt2.data.mkString should startWith("2019-02-17")
    dt3.data.mkString should startWith("2019-03-14")
  }

  it should "correctly substract negative year offset from java.time.LocalDateTime" in {
    val f = fixture
    val offsetCalendar = Map("yyyy"->(-5))

    val dt = DateTimeConstructor.fromLocalDateTime(Vector(f.dateTimeJava))(offsetCalendar)
    dt.data.mkString should startWith ("2014-02-12")
  }

  it should "correctly add positive month offset from java.time.LocalDateTime" in {
    val f = fixture
    val offsetCalendar = Map("MM"->2)

    val dt = DateTimeConstructor.fromLocalDateTime(Vector(f.dateTimeJava))(offsetCalendar)
    dt.data.mkString should startWith("2019-04-12")
  }

  it should "correctly handle data overflows from java.time.LocalDateTime" in {
    val f = fixture
    val offsetCalendar1 = Map("MM"->11)
    val offsetCalendar2 = Map("dd"->5)
    val offsetCalendar3 = Map("dd"->30)
    val dt1 = DateTimeConstructor.fromLocalDateTime(Vector(f.dateTimeJava))(offsetCalendar1)
    val dt2 = DateTimeConstructor.fromLocalDateTime(Vector(f.dateTimeJava))(offsetCalendar2)
    val dt3 = DateTimeConstructor.fromLocalDateTime(Vector(f.dateTimeJava))(offsetCalendar3)
    dt1.data.mkString should startWith("2020-01-12")
    dt2.data.mkString should startWith("2019-02-17")
    dt3.data.mkString should startWith("2019-03-14")
  }

}