object Euler021 extends EulerApp {
  override def execute(): Any = {
    val divisorSums = (1 to 10000).map(n => divisors(n).sum)
    divisorSums.zipWithIndex.collect {
      case (sum, index) if sum < 10000 && index + 1 == divisorSums(sum - 1) => index + 1
    }.sum

    divisorSums.zipWithIndex.map { case (n, idx) => (idx + 1, n) }
  }

  private def divisors(n: Int): Seq[Int] = 1 +: (2 to n / 2).filter(n % _ == 0)
}
