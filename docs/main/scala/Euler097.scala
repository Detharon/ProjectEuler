import scala.annotation.tailrec

/** Problem 97 — Large Non-Mersenne Prime
  *
  * The first known prime found to exceed one million digits was discovered in 1999; and is a Mersenne prime of the
  * form: 2^6972593^ - 1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2^p^ -
  * 1, have been found which contain more digits.
  *
  * However, in 2004 there was found a massive non-Mersenne prime which contains 2 357 207 digits: 28433 * 2^7830457^ +
  * 1
  *
  * Find the last ten digits of this prime number.
  *
  * --------------------------------------------------
  *
  * Explanation: This number is extreme. Too big to be represented as a BigInteger, so we're out of luck here!
  *
  * Fortunately, we're only asked to provide the last ten digits of this number, and we know how it looks in the n *
  * 2^p^ + 1 form. We can simply keep multiplying our 'n' by 2, exactly 'p' times, and then add 1 when the computation
  * stops.
  *
  * Since we're interested just in the last 10 digits, we can modulo divide it by 10000000000L after each
  * multiplication. This way we'll avoid the number from overflowing. Why does this method works?
  *
  * Let's consider a simper case. If I wanted to see what's the last digit of 3 to the power of 100, during the first
  * iteration we could rewrite it as 3 * 3^99^. Then we could write 9 * 3^98^, then 27 * 3^97^. But we just need the
  * last digit, so if we do 27 % 10, we'll get 7. The 2 is gone, sure, but when multiplying, the rightmost digit will
  * never be affected by the digits on its left side, so we can ignore them altogether.
  *
  * We can apply the same method to this problem.
  */
object Euler097 extends EulerApp {
  override def execute(): Any = {
    val base = 28433L
    val powersOfTwo = 7830457

    @tailrec
    def calculate(
        currentResult: Long,
        powersOfTwoLeft: Int = powersOfTwo
    ): Long = {
      val newResultLastTenDigits = (currentResult * 2) % 10000000000L

      if (powersOfTwoLeft == 0) currentResult
      else calculate(newResultLastTenDigits, powersOfTwoLeft - 1)
    }

    calculate(base, powersOfTwo) + 1
  }
}
