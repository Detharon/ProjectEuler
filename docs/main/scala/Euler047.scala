import scala.annotation.tailrec
import scala.collection.mutable

object Euler047 extends EulerApp {

  override def execute(): Any = {
    var n = 2
    val consecutiveRequirement = 4
    var consecutiveUnique = 0
    val currentFactors = mutable.ArrayBuffer[Int]()

    while (consecutiveUnique < consecutiveRequirement) {
      val factors = factorsFor(n).distinct
      println(s"Factors of $n are $factors")

      if (factors.size == consecutiveRequirement) {
        consecutiveUnique += 1
      } else {
        consecutiveUnique = 0
        currentFactors.clear()
      }

      currentFactors.addAll(factors)
      n += 1
    }

    n - consecutiveRequirement
  }

  private def factorsFor(n: Int): List[Int] = {
    @tailrec
    def factorsHelper(
        n: Int,
        currentPrime: Int,
        primesIterator: Iterator[Int],
        factors: List[Int]
    ): List[Int] = {
      if (isPrime(n)) factors :+ n
      else {
        if (n % currentPrime == 0)
          factorsHelper(
            n / currentPrime,
            currentPrime,
            primesIterator,
            factors :+ currentPrime
          )
        else {
          factorsHelper(n, primesIterator.next(), primesIterator, factors)
        }
      }
    }

    val primesIterator = primes.iterator
    factorsHelper(n, primesIterator.next(), primesIterator, List.empty)
  }

  private def primes: LazyList[Int] = 2 #:: nextPrime(3)

  private def nextPrime(from: Int): LazyList[Int] = {
    if (isPrime(from)) from #:: nextPrime(from + 1)
    else nextPrime(from + 1)
  }

  private def isPrime(n: Long): Boolean = n match
    case n if n < 2 => false
    case _          => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)

}
