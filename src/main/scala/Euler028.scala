object Euler028 extends EulerApp {

  override def execute(): Long = {
    1 + Stream.from(3, 2)
      .takeWhile(_ <= 1001)
      .map(sumForDiagonal(_))
      .sum
  }

  def sumForDiagonal(n: Long): Long = {
    n * n +
      n * n - (n - 1) +
      n * n - 2 * (n - 1) +
      n * n - 3 * (n - 1)
  }
}