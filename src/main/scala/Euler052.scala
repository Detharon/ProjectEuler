object Euler052 extends EulerApp {

  override def execute(): Int =
    (1 to Integer.MAX_VALUE).find(isPermuted).get

  private def hasSameDigits(a: Int, b: Int): Boolean =
    a.toString.sorted == b.toString.sorted

  private def isPermuted(n: Int): Boolean =
    List(2, 3, 4, 5, 6).forall { factor =>
      hasSameDigits(n, n * factor)
    }

}
