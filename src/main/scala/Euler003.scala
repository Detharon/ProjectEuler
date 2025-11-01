import EulerHelper.naiveIsPrime

import scala.annotation.tailrec

object Euler003 extends EulerApp {

  /**
   * Problem 3: The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143.
   *
   * --------------------------------------------------
   *
   * Explanation: We can just divide the number by prime numbers until the number that we get is itself a prime.
   * The numbers are low enough that we don't need any optimisations to solve it in a matter of milliseconds.
   *
   */
  override def execute(): Long = primeFactors(600851475143L).max

  @tailrec
  private def primeFactors(n: Long, i: Int = 2, primesFound: List[Int] = List.empty): List[Int] =
    if (n == 1) primesFound
    else if (n % i == 0 && naiveIsPrime(i)) primeFactors(n / i, i + 1, primesFound :+ i)
    else primeFactors(n, i + 1, primesFound)

}
