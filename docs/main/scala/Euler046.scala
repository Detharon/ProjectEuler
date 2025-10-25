import scala.annotation.tailrec

object Euler046 extends EulerApp {

  override def execute(): Any = {
    LazyList.from(3, 2)
      .filter(!isPrime(_))
      .find(!goldbachConjecture(_))
  }

  private def goldbachConjecture(n: Int): Boolean = {
    val newPrimesIterator = primesIterator
    val newSquaresIterator = squaresIterator
    goldbachConjecture(n, newPrimesIterator.next(), newSquaresIterator.next(), newPrimesIterator, newSquaresIterator)
  }

  @tailrec
  private def goldbachConjecture(n: Int, prime: Int, square: Int, primes: Iterator[Int], squares: Iterator[Int]): Boolean = {
    (prime, square) match
      case _ if prime + square == n =>
        println(s"$n is a sum of $prime and $square")
        true
      case _ if square > n && prime < n =>
        val newSquaresIterator = squaresIterator
        goldbachConjecture(n, primes.next(), squaresIterator.next(), primes, newSquaresIterator)
      case _ if square <= n && prime < n =>
        goldbachConjecture(n, prime, squares.next(), primes, squares)
      case _ => false
  }

  private def nextTwiceSquare(from: Int): LazyList[Int] = {
    (2 * from * from) #:: nextTwiceSquare(from + 1)
  }

  private def nextPrime(from: Int): LazyList[Int] = {
    if (isPrime(from)) from #:: nextPrime(from + 1)
    else nextPrime(from + 1)
  }

  private def isPrime(n: Long): Boolean = n match
    case n if n < 2 => false
    case _ => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)

  def primesIterator: Iterator[Int] = (2 #:: nextPrime(3)).iterator
  def squaresIterator: Iterator[Int] = nextTwiceSquare(1).iterator
}
