object Euler021 extends EulerApp {
  override def execute(): Any = {
    val divisorSums = (0 to 10000).map(n => divisors(n).sum)
    divisorSums.zipWithIndex.collect {
      case (sum, index) if sum < 10000 && sum != index && index == divisorSums(sum) =>
        index
    }.sum
  }

  private def divisors(n: Int): Seq[Int] = 1 +: (2 to Math.sqrt(n).toInt).flatMap {
    case i if n % i == 0 => if (i != n / i) List(i, n / i) else List(i)
    case _ => List()
  }
}
