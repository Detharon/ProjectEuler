object Euler041 extends EulerApp {
  /**
   * Problem 41: We shall say that an n-digit number is pandigital if it makes use of all the digits
   * 1 to n exactly once. For example, 2143 is a n-digit pandigital and is also prime.
   *
   * What is the largest n-digit pandigital prime that exists?
   *
   * --------------------------------------------------
   *
   * Explanation: We can start from the "987654321" pandigital number and all of its permutations.
   * This way we check all the 9-digital pandigital numbers.
   * Then we generate the 8-digital pandigital number permutations and keep looking for a prime number.
   *
   * We also need to make sure that the generated permutations are sorted by a descending order, so the first
   * prime number found is the biggest one.
   */
  override def execute(): Any = generatePandigitals("987654321").find(isPrime)

  private def generatePandigitals(from: String): LazyList[Int] =
    if (from.isEmpty) LazyList.empty
    else LazyList
      .from(from.permutations.map(_.toInt))
      .sorted(using Ordering[Int].reverse) #::: generatePandigitals(from.tail)

  private def isPrime(n: Int): Boolean = n match
    case n if n < 2 => false
    case _ => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)
}
