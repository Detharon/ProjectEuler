object Euler023 extends EulerApp {
  override def execute(): Any = {
    val limit = 28123
    val abundantNumbers = (1 to limit).filter(isAbundant)
    val abundantNumbersSet = abundantNumbers.toSet
    (1 to limit).filterNot { n =>
      isSumOfTwoAbundant(n, abundantNumbers, abundantNumbersSet)
    }.sum
  }

  private def isSumOfTwoAbundant(
      n: Int,
      abundantNumbers: Seq[Int],
      abundantNumbersSet: Set[Int]
  ): Boolean =
    abundantNumbers.takeWhile(_ <= n).exists { a =>
      abundantNumbersSet.contains(n - a)
    }

  private def isAbundant(n: Int): Boolean = divisors(n).sum > n

  private def divisors(n: Int): Seq[Int] = 1 +: (2 to Math.sqrt(n).toInt).flatMap {
    case i if n % i == 0 => if (i != n / i) List(i, n / i) else List(i)
    case _               => List()
  }
}
