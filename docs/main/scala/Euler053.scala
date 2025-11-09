import EulerHelper.factorial

object Euler053 extends EulerApp {

  override def execute(): Any = (for {
    n <- 1 to 100
    r <- 1 to n
  } yield countCombinations(n, r)).count(_ > 1_000_000)

  private def countCombinations(n: Int, r: Int) =
    BigInt(n).factorial / (BigInt(r).factorial * BigInt(n - r).factorial)
}
