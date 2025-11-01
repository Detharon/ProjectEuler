import EulerHelper.naiveIsPrime

import scala.annotation.tailrec

object Euler003 extends EulerApp {
  override def execute(): Long = primeFactors(600851475143L).max

  @tailrec
  private def primeFactors(n: Long, i: Int = 2, primesFound: List[Int] = List.empty): List[Int] =
    if (n == 1) primesFound
    else if (n % i == 0 && naiveIsPrime(i)) primeFactors(n / i, i + 1, primesFound :+ i)
    else primeFactors(n, i + 1, primesFound)
}
