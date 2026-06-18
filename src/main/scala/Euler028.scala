object Euler028 extends EulerApp {

  override def execute(): Long = {
    1 + Iterator
      .from(3, 2)
      .takeWhile(_ <= 1001)
      .map(sumForDiagonal)
      .sum
  }

  private def sumForDiagonal(n: Int): Int = {
    n * n +
      n * n - (n - 1) +
      n * n - 2 * (n - 1) +
      n * n - 3 * (n - 1)
  }
}
