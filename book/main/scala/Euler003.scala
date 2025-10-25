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
  override def execute(): Long = nextPrimeFactor(600851475143L, primes.iterator)

  @tailrec
  private def nextPrimeFactor(n: Long, primesIterator: Iterator[Int]): Long = {
    val currentPrime = primesIterator.next()

    if (isPrime(n)) n
    else {
      if (n % currentPrime == 0) nextPrimeFactor(n / currentPrime, primesIterator)
      else nextPrimeFactor(n, primesIterator)
    }
  }

  private val primes: LazyList[Int] = 2 #:: nextPrime(3)

  private def nextPrime(from: Int): LazyList[Int] = {
    if (isPrime(from)) from #:: nextPrime(from + 1)
    else nextPrime(from + 1)
  }

  private def isPrime(n: Long): Boolean = n match
    case n if n < 2 => false
    case _ => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)
}
