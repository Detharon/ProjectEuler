/**
 * Problem 9: A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
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
 * Explanation: This time let's use the mutable state for maximum efficiency.
 * Even though the problem was solved relatively fast on my machine, this solution is about 10 to 15 times faster
 * than the idiomatic one. They share the same algorithm.
 *
 */
object Euler009M extends EulerApp {

  override def execute(): Any = {
    var a = 1
    var b = 2
    var c = 997
    var found = false

    while (!found) {
      if (a * a + b * b == c * c) found = true
      else {
        if (b == c - 1 || b == c - 2) {
          a = a + 1
          b = a + 1
          c = 1000 - b - a
        } else {
          b = b + 1
          c = c - 1
        }
      }
    }

    a * b * c
  }

}
