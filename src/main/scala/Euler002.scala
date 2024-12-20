object Euler002 extends EulerApp {

  /**
   * Problem 2: Each new term in the Fibonacci sequence is generated by adding the previous two terms.
   * By starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
   *
   * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
   * 
   * --------------------------------------------------
   *
   * Explanation: The numbers are small enough to calculate it in a matter of milliseconds without any optimisations.
   * Fibonacci implementation is done with a LazyList to make it infinite and to avoid a stack overflow linked to
   * too many recursive function calls.
   */
  override def execute(): Int =
    fibonacci.filter(_ % 2 == 0).takeWhile(_ < 4_000_000).sum

  val fibonacci: LazyList[Int] = 1 #:: fibonacciFunction(1, 2)

  private def fibonacciFunction(a: Int, b: Int): LazyList[Int] =
    a #:: fibonacciFunction(b, a + b)
}
