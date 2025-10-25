import EulerHelper.*

import scala.annotation.tailrec

/**
 * Problem 20: n! means n × (n − 1) × ... × 3 × 2 × 1
 *
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800
 * and the sum of the digits in the number 10! is
 * 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27
 *
 * Find the sum of the digits in the number 100!
 *
 * --------------------------------------------------
 *
 * Explanation: Thanks to Scala's extensive standard library, we can represent the factorial in question as a regular
 * BigInt. We'll define a general-usage factorial method (that looks like a built-in one, thanks to the fact that it's
 * an extension method).
 *
 * Once we have a result, we can read it, digit by digit, by dividing it by ten, also recursively.
 */
object Euler020 extends EulerApp {
  override def execute(): Any = {
    sumDigits(BigInt(100).factorial)
  }

  @tailrec
  private def sumDigits(n: BigInt, result: BigInt = 0): BigInt =
    if (n == 0) result
    else sumDigits(n / 10, result + n % 10)

}
