import scala.annotation.tailrec

/**
 * The following iterative sequence is defined for the set of positive integers:
 *
 * n -> n / 2 (n is even)
 * n -> 3n + 1 (n is odd)
 *
 * Using the rule above and starting with 13, we generate the following sequence:
 *
 * 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
 *
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 *
 * Which starting number, under one million, produces the longest chain?
 *
 * '''NOTE''': Once the chain starts the terms are allowed to go above one million.
 *
 * --------------------------------------------------
 *
 * Explanation: A brute-force solution, but it works quite fast as it is.
 *
 * My first though when seeing this problem was to use the dynamic programming. After all, we'll keep hitting the same
 * sub-sequences (such as 16 -> 8 -> 4 -> 2 -> 1) many times. In practice, however, it turns out that cost of storing all
 * the previous results outweighs the benefits and the app runs 50% slower.
 *
 */
object Euler014 extends EulerApp {

  override def execute(): Any = (1 until 1_000_000).maxBy(collatzLength)

  private def collatzLength(n: Int): Int = {
    @tailrec
    def collatzLength(n: Long, currentLength: Int): Int = n match {
      case 1 => currentLength + 1
      case n if n % 2 == 0 => collatzLength(n / 2, currentLength + 1)
      case n => collatzLength(n * 3 + 1, currentLength + 1)
    }

    collatzLength(n, 0)
  }
}
