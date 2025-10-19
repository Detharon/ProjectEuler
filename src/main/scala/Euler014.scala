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
 * Explanation: A brute-force solution, but it works quite fast as it is. There's just one optimization, but it cuts the
 * execution time in half. Due to how the sequences are calculated, a collatzLength(2n) is always bigger than
 * collatzLength(n) by 1. So we can check only the upper half of the range to get the maximum length of the sequence.
 *
 * My first though when seeing this problem was to use the dynamic programming. After all, we'll keep hitting the same
 * sub-sequences (such as 16 -> 8 -> 4 -> 2 -> 1) many times. I implemented it using a map while keeping it
 * immutable, and it wasn't a great idea. The code ran much slower. In fact, even with a mutable map it was around
 * 50% slower than the brute-force. 
 *
 * On the other hand, this is why it's worth starting out from a simple solution and only then try to optimize.
 * There's less room for mistake, and we have a baseline result that we can improve further.
 *
 * Even though this solution is good enough, just to have more practice, let's also build a mutable one,
 * which will try to address the problem I ran into in the beginning and will push the performance even further.
 */
object Euler014 extends EulerApp {

  override def execute(): Any = (500_000 until 1_000_000).maxBy(collatzLength)

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
