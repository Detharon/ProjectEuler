object Euler006 extends EulerApp {
  override def execute(): Int = squareOfSums(100) - sumOfSquares(100)

  private def sumOfSquares(n: Int) = (1 to n).map(_.square).sum

  private def squareOfSums(n: Int) = (1 to n).sum.square

  extension (i: Int) {
    def square: Int = i * i
  }
}
