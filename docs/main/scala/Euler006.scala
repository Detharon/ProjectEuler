/**
 * Problem 6: The sum of the squares of the first ten natural numbers is,
 *
 * 1^2^ + 2^2^ + ... + 10^2^ = 385
 *
 * The square of the sum of the first ten natural numbers is,
 *
 * (1 + 2 + ... + 10)^2^ = 55^2^ = 3025
 *
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is
 *
 * 3025 - 385 = 2640
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 *
 * --------------------------------------------------
 *
 * Explanation: It's a pretty straightforward problem. The numbers are small enough that we can just calculate it
 * right away. Scala's expressiveness makes this task a breeze.
 */
object Euler006 extends EulerApp {

  override def execute(): Any = squareOfSums(100) - sumOfSquares(100)

  private def sumOfSquares(n: Int) = (1 to 100).map(_.square).sum
  private def squareOfSums(n: Int) = (1 to 100).sum.square

  extension (i: Int) {
    def square: Int = i * i
  }

}
