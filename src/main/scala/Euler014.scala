import scala.annotation.tailrec

object Euler014 extends EulerApp {

  override def execute(): Any = (1 until 1_000_000).map(collatzLength).max

  private def collatzLength(n: Int): Int = {
    @tailrec
    def collatzLength(n: Long, currentLength: Long): Int = n match {
      case 1 => (currentLength + 1).toInt
      case n if n % 2 == 0 => collatzLength(n / 2, currentLength + 1)
      case n => collatzLength(n * 3 + 1, currentLength + 1)
    }

    collatzLength(n, 0)
  }
}
