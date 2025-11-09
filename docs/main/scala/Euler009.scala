/** Problem 9: A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
  *
  * a^2^ + b^2^ = c^2^
  *
  * For example 3^2^ + 4^2^ = 9 + 16 = 25 = 5^2^.
  *
  * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  *
  * Find the product abc.
  *
  * --------------------------------------------------
  *
  * Explanation: The numbers are low enough for a naive approach, so let's use it, while keeping the code nice and
  * clean. We need to check all combinations of a, b, c fpr which a + b + c = 1000 and find those that form a
  * Pythagorean triplet.
  *
  * This naive algorithm start from the highest possible 'c', keeps the sum of parameters at 1000, while gradually
  * incrementing 'b' and decrementing 'c' until this it's possible. This condition (b == c - 2 || b == c - 1) indicates
  * that the next triangle can't be created this way, so we need to increase the 'a' instead, while resetting the other
  * two parameters: 'b' to the lowest possible one, 'c' to whatever will give a sum of 1000.
  */
object Euler009 extends EulerApp {

  override def execute(): Any = {
    triangles.dropWhile(!isPythagorean(_)).head.product
  }

  type Triangle = (Int, Int, Int)
  val triangles: LazyList[Triangle] = triangles(1, 2, 997)

  private def triangles(a: Int, b: Int, c: Int): LazyList[Triangle] = {
    (a, b, c) #:: {
      if (b == c - 2 || b == c - 1) triangles(a + 1, a + 2, 997 - (2 * a))
      else triangles(a, b + 1, c - 1)
    }
  }

  private def isPythagorean(triangle: Triangle): Boolean = {
    val Triangle(a, b, c) = triangle
    a * a + b * b == c * c
  }

  extension (t: Triangle) {
    def product: Int = t._1 * t._2 * t._3
  }

}
