import scala.annotation.tailrec

object Euler012 extends EulerApp {
  private val primes: Seq[Int] =
    (2 to 65550)
      .filter(EulerHelper.naiveIsPrime)

  override def execute(): Any = divisors(28)

  @tailrec
  private def findTriangleNumber(n: Int = 1, k: Int = 2): Long =
    if (divisors(n) > 10) n
    else findTriangleNumber(n + k, k + 1)

  // 28 = 2^2 * 7*1 = (2 + 1) * (1 + 1) = 6
  @tailrec
  private def divisors(
      n: Long,
      primes: Seq[Int] = primes,
      divisorsFound: Map[Int, Int] = Map.empty
  ): Int = {
    val prime = primes.head

    if (n % prime == 0) {
      val newFactor = divisorsFound.get(prime) match {
        case Some(factor) => factor + 1
        case None         => 1
      }
      divisors(
        n / prime,
        primes,
        divisorsFound + (prime -> newFactor)
      )
    } else if (prime >= n) divisorsFound.foldLeft(1) { case (accumulator, (divisor, factor)) =>
      accumulator * (divisor * factor + 1)
    }
    else divisors(n, primes.tail, divisorsFound)
  }

}
