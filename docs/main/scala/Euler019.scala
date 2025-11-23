import scala.annotation.tailrec

object Euler019 extends EulerApp {

  private type DayOfWeek = String
  private type Year = Int

  override def execute(): Any = {
    @tailrec
    def sundaysInTwentyCentury(calendar: Calendar, sundays: Int): Int = {
      if (calendar.isInTwentyFirstCentury) sundays
      else if (calendar.isInTwentiethCentury) {
        val newSundays =
          if (calendar.isSunday && calendar.dayNumber == 1) sundays + 1 else sundays
        sundaysInTwentyCentury(calendar.nextDay(), newSundays)
      } else sundaysInTwentyCentury(calendar.nextDay(), sundays)
    }

    sundaysInTwentyCentury(Calendar.StartDate, 0)
  }

  case class Calendar(
      dayNumber: Int,
      dayOfWeekNumber: Int,
      monthNumber: Int,
      year: Year
  ) {
    private def daysPerMonth(year: Year): Array[Int] = Array(
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

    private def isLeapYear(year: Year): Boolean =
      (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)

    def nextDay(): Calendar = {
      val newDay = dayNumber + 1
      val newDayOfWeek = if (dayOfWeekNumber + 1 == 7) 0 else dayOfWeekNumber + 1

      if (newDay > daysPerMonth(year)(monthNumber)) {
        if (monthNumber == 11) Calendar(1, newDayOfWeek, 0, year + 1)
        else Calendar(1, newDayOfWeek, monthNumber + 1, year)
      } else Calendar(newDay, newDayOfWeek, monthNumber, year)
    }

    val isSunday: Boolean = dayOfWeekNumber == 6

    def isInTwentiethCentury: Boolean = year >= 1901 && year <= 2000

    def isInTwentyFirstCentury: Boolean = year >= 2001
  }

  object Calendar {
    val StartDate: Calendar = Calendar(dayNumber = 1, dayOfWeekNumber = 0, monthNumber = 0, year = 1900)
  }
}
