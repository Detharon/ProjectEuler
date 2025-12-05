import scala.annotation.tailrec

object Euler025 extends EulerApp {
  override def execute(): Any = fibonacciDigits(1000)

  def fibonacciDigits(digitsLimit: Int): BigInt = {
    @tailrec
    def fibonacci(p1: BigInt, p2: BigInt, current: BigInt, index: Int): BigInt =
      if (current.toString.length == digitsLimit) index
      else fibonacci(p2, current, p2 + current, index + 1)

    fibonacci(1, 1, 2, 3)
  }
}
