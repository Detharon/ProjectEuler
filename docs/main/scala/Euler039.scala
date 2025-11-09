object Euler039 extends EulerApp {

  /** Problem 39: perimeter If 'p' is the perimeter of a right angle triangle with integral length sides, {a, b, c},
    * there are exactly three solutions for p = 120. {20, 48, 52}, {24, 45, 51}, {30, 40, 50}
    *
    * For which value of p <= 1000 is the number of solutions maximised?
    *
    * --------------------------------------------------
    *
    * We could just check all the possible values of a, b, c and check if they follow the Pythagorean theorem, a^2^ +
    * b^2^ = c^2^, but we can reduce the number of possibilities to check since we also know that the sum of two length
    * sides must be greater than the length of the third side, that is: a + b > c.
    *
    * For example, for a perimeter of 120, we know that one side has to be smaller than 60, because the sum of two sides
    * has to be greater than the third side. The upper bound, then, is perimeter / 2 - 1.
    */
  override def execute(): Int = (1 to 1000).maxBy { n =>
    findRightTriangles(n).size
  }

  private def findRightTriangles(perimeter: Int) = (for {
    a <- 1 until perimeter / 2 - 1
    b <- 1 until perimeter / 2 - 1
    c = perimeter - a - b
  } yield {
    if (a + b > c && a + c > b && a + b > c) Some(Triangle(a, b, c))
    else None
  }).flatten.toSet.filter(_.isRight)

  case class Triangle(a: Int, b: Int, c: Int) {
    def isRight: Boolean = a * a + b * b == c * c
  }

  private object Triangle {
    def apply(a: Int, b: Int, c: Int): Triangle = {
      val List(x, y, z) = List(a, b, c).sorted
      new Triangle(x, y, z)
    }
  }

}
