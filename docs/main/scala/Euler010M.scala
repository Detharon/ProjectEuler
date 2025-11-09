import scala.annotation.tailrec

/** Problem 10: The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17 Find the sum of all the primes below two million.
  *
  * --------------------------------------------------
  *
  * Explanation: It's the perfect problem to use the sieve of Eratosthenes. If you've never heard about it, then I
  * suggest looking it up, as it will be used in the future problems as well:
  * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
  *
  * Unfortunately, the sieve itself doesn't go along well with immutable data structures because its creation is done
  * iteratively, by small incremental changes.
  *
  * In this solution, we're filling the sieve, which initially considers all numbers to be primes (true) and marks the
  * multiples of existing primes as non-primes (false). Once we hit next number, we know that if its factors were not
  * found already, then it's a prime. No need for any another check!
  *
  * While filling the sieve, we'll also calculate the primeSum to avoid iterating the array. It makes it even more
  * mutable with a global state, but also faster.
  *
  * We could also cut the numbers of elements stored by twice if we don't check the even numbers, we know that they are
  * not primes, but that would make the solution more complex. We could also use a more memory-efficient structure, such
  * as BitSet, which requires 1 bit to store 1 number, as compared to array of booleans where we need 1 byte to store a
  * boolean. It would also increase the complexity, since BitSets are a bit more tricky to handle. Could also decrease
  * the performance, but I haven't checked that.
  */
object Euler010M extends EulerApp {

  private type Sieve = Array[Boolean]

  private val limit = 2_000_000
  private val sieve = Array(false, false) ++ Array.fill(limit - 2)(true)
  private var primeSum = 0L

  override def execute(): Any = {
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
