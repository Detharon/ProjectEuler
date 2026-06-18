import EulerHelper.naiveIsPrime

object Euler027 extends EulerApp {

  override def execute(): Int = {
    val coefficients = for {
      a <- -999 until 1000
      b <- 2 to 1000 if b.naiveIsPrime
    } yield (a, b)

    val (a, b) = coefficients.maxBy { case (a, b) =>
      consecutivePrimes(a, b)
    }

    a * b
  }

  private def consecutivePrimes(a: Int, b: Int): Int =
    Iterator.from(0).takeWhile { n =>
      (n * n + n * a + b).naiveIsPrime
    }.length

}
