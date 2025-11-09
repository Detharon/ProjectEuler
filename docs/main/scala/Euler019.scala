import scala.annotation.tailrec

object Euler019 extends EulerApp {

  override def execute(): Any = {
    @tailrec
    def nextDay(calendar: Calendar, sundays: Int): Int = {
      if (calendar.isInTwentyFirstCentury) sundays
      else if (calendar.isInTwentiethCentury) {
        println(calendar.toString + " " + calendar.dayOfWeek)
        val newSundays =
          if (calendar.dayOfWeek == "Sunday" && calendar.dayNumber == 1)
            sundays + 1
          else sundays
        nextDay(calendar.nextDay(), newSundays)
      } else nextDay(calendar.nextDay(), sundays)
    }

    nextDay(Calendar.StartDate, 0)
  }

  case class Calendar(
      dayNumber: Int,
      dayOfWeekNumber: Int,
      monthNumber: Int,
      yearNumber: Int
  ) {
    type DayOfWeek = String
    type Month = String

    private val daysOfWeek: List[DayOfWeek] = List(
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
      "Sunday"
    )

    private def daysPerMonth(year: Int): Array[Int] = Array(
      31, // January
      if (isLeapYear(year)) 29 else 28, // February
      31, // March
      30, // April
      31, // May
      30, // June
      31, // July
      31, // August
      30, // September
      31, // October
      30, // November
      31 // December
    )

    private def isLeapYear(year: Int): Boolean =
      (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)

    def nextDay(): Calendar = {
      val newDay = dayNumber + 1
      val newDayOfWeek =
        if (dayOfWeekNumber + 1 == 7) 0 else dayOfWeekNumber + 1

      if (newDay > daysPerMonth(yearNumber)(monthNumber)) {
        if (monthNumber == 11) Calendar(1, newDayOfWeek, 0, yearNumber + 1)
        else Calendar(1, newDayOfWeek, monthNumber + 1, yearNumber)
      } else Calendar(newDay, newDayOfWeek, monthNumber, yearNumber)
    }

    val dayOfWeek: DayOfWeek = daysOfWeek(dayOfWeekNumber)

    def isInTwentiethCentury: Boolean = yearNumber >= 1901 && yearNumber <= 2000

    def isInTwentyFirstCentury: Boolean = yearNumber >= 2001
  }

  object Calendar {
    val StartDate: Calendar = Calendar(1, 0, 0, 1900)
  }
}
