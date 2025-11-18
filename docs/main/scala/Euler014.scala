import scala.annotation.tailrec

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
