import scala.annotation.tailrec

object Euler012 extends EulerApp {
  override def execute(): Any = findTriangleNumber()

  private val primes: Seq[Int] =
    (2 to Math.sqrt(Integer.MAX_VALUE).toInt)
      .filter(EulerHelper.naiveIsPrime)

  @tailrec
  private def findTriangleNumber(n: Int = 1, k: Int = 2): Int =
    if (divisors(n) > 500) n
    else findTriangleNumber(n + k, k + 1)

  @tailrec
  private def divisors(
      n: Int,
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
    } else if (prime > n) divisorsFound.foldLeft(1) { case (accumulator, (_, factor)) =>
      accumulator * (factor + 1)
    }
    else divisors(n, primes.tail, divisorsFound)
  }

}
