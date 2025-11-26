import EulerHelper.*

import scala.annotation.tailrec

object Euler020 extends EulerApp {
  override def execute(): Any = {
    sumDigits(BigInt(100).factorial)
  }

  @tailrec
  private def sumDigits(n: BigInt, result: BigInt = 0): BigInt =
    if (n == 0) result
    else sumDigits(n / 10, result + n % 10)
}
