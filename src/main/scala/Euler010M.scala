import scala.annotation.tailrec

object Euler010M extends EulerApp {
  private type Sieve = Array[Boolean]

  private val limit = 2_000_000
  private val sieve = Array(false, false) ++ Array.fill(limit - 2)(true)
  private var primeSum = 0L

  override def execute(): Long = {
    fillSieve(2)
    primeSum
  }

  @tailrec
  private def fillSieve(n: Int): Sieve = {
    if (n == limit) sieve
    else if (sieve(n)) {
      primeSum += n
      sieveWithNewPrime(n)
      fillSieve(n + 1)
    } else fillSieve(n + 1)
  }

  private def sieveWithNewPrime(newPrime: Int): Unit = {
    var nonPrime = newPrime * 2
    // We will mark all the multiplies of the new prime number, up to 'limit' as non primes
    while (nonPrime < limit) {
      sieve(nonPrime) = false
      nonPrime = nonPrime + newPrime
    }
  }
}
