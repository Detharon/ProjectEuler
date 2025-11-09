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
