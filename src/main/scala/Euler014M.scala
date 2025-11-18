import scala.annotation.tailrec

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
