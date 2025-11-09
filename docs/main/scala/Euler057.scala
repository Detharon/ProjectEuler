import scala.annotation.tailrec

object Euler057 extends EulerApp {

  def execute(): Int =
    (1 to 1000).map(expansion(_, 1, 2)).count { case (numerator, denominator) =>
      numerator.toString.length > denominator.toString.length
    }

  @tailrec
  private def expansion(
      depth: Int,
      numerator: BigInt,
      denominator: BigInt
  ): (BigInt, BigInt) = {
    if (depth == 1) (numerator + denominator, denominator)
    else expansion(depth - 1, denominator, numerator + 2 * denominator)
  }
}
