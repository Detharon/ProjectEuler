object Euler002 extends EulerApp {
  override def execute(): Int =
    fibonacci.filter(_ % 2 == 0).takeWhile(_ < 4_000_000).sum

  val fibonacci: LazyList[Int] = 1 #:: fibonacciFunction(1, 2)

  private def fibonacciFunction(a: Int, b: Int): LazyList[Int] =
    a #:: fibonacciFunction(b, a + b)
}
