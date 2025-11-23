import java.time.{ DayOfWeek, LocalDate }

object Euler019M extends EulerApp {
  override def execute(): Int = {
    val endDate = LocalDate.of(2000, 12, 31)
    var currentDate = LocalDate.of(1901, 1, 1)
    var sundays = 0

    while (currentDate.isBefore(endDate)) {
      if (currentDate.getDayOfMonth == 1 && currentDate.getDayOfWeek == DayOfWeek.SUNDAY) sundays += 1
      currentDate = currentDate.plusDays(1)
    }

    sundays
  }
}
