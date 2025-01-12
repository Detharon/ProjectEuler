import scala.annotation.tailrec

object Euler097 extends EulerApp {
  override def execute(): Any = {
    val base = 28433L
    val powersOfTwo = 7830457

    @tailrec
    def calculate(currentResult: Long, powersOfTwoLeft: Int = powersOfTwo): Long = {
      val newResultLastTenDigits = (currentResult * 2) % 10000000000L

      if (powersOfTwoLeft == 0) currentResult
      else calculate(newResultLastTenDigits, powersOfTwoLeft - 1)
    }

    calculate(base, powersOfTwo) + 1
  }
}
