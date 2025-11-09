import scala.annotation.tailrec

/** The following iterative sequence is defined for the set of positive integers:
  *
  * n -> n / 2 (n is even) n -> 3n + 1 (n is odd)
  *
  * Using the rule above and starting with 13, we generate the following sequence:
  *
  * 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
  *
  * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been
  * proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
  *
  * Which starting number, under one million, produces the longest chain?
  *
  * '''NOTE''': Once the chain starts the terms are allowed to go above one million.
  *
  * --------------------------------------------------
  *
  * Explanation: Uses memoization with an array-based cache for fast lookups. Why array? Because map would be much
  * slower and we know its max size. In fact, we don't have to cache all the values. You can try it out yourself by
  * limiting the 'cacheLimit' to 100_000. It should be slower, but still faster than the brute-force solution.
  *
  * As you see we get rid of the optimization to start from 500_000. That's because, it turns out, it works slightly
  * slower than starting from the beginning, because our cache has a lot of misses this way, and we don't backtrace the
  * values to fill it.
  *
  * We could try to do it, but is it worth increasing the complexity of the solution even further, if it already runs in
  * milliseconds? Sometimes it is, but here I'll pass. If it wasn't a computational problem, but a logical one, then the
  * base, immutable solution would be the one preferred in almost all the instances, due to how simple and safe it is.
  *
  * This solution has much more places where errors can be made. For example, the 'n.toInt' is only safe because we
  * check right before that n is below a cache limit, which prevents the overflow.
  */
object Euler014M extends EulerApp {

  override def execute(): Any = (1 until 1_000_000).maxBy(collatzLength)

  private val cacheLimit = 1_000_000
  private val cache = new Array[Int](cacheLimit)
  cache(1) = 1

  private def collatzLength(base: Int): Int = {
    @tailrec
    def compute(n: Long, length: Int): Int = {
      if (n < cacheLimit && cache(n.toInt) != 0) {
        length + cache(n.toInt)
      } else if (n % 2 == 0) {
        compute(n / 2, length + 1)
      } else {
        compute(n * 3 + 1, length + 2)
      }
    }

    val result = compute(base, 0)
    if (base < cacheLimit) cache(base) = result
    result
  }
}
