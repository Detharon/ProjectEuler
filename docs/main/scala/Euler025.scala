import scala.annotation.tailrec

/**
 * Problem 25:
 * The Fibonacci sequence is defined by the recurrence relation:
 *
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * Hence the first 12 terms will be:
 *
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * F5 = 5
 * F6 = 8
 * F7 = 13
 * F8 = 21
 * F9 = 34
 * F10 = 55
 * F11 = 89
 * F12 = 144
 * The 12th term, F12, is the first term to contain three digits.
 *
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 *
 * --------------------------------------------------
 *
 * Explanation: This problem doesn't need any optimizations to be solved in milliseconds. There are some caveats though.
 * First of them is that the resulting number will exceed the range of Int or Long, so BigInt needs to be used instead.
 *
 * Also, a straightforward recursive algorithm that adds the result of two previous invocations will not do, as
 * in this problem we're not looking for an n-th element of a sequence, but rather an element of a sequence that matches
 * given criteria. It is also likely to crash due to stack overflow, as the recursion is too deep.
 *
 * That's why an iterative approach is preferred. Since scala supports tail-recursion, we can also make
 * a tail-recursive function that's as fast as an iterative solution.
 */
object Euler025 extends EulerApp {
  override def execute(): Any = {
    fibonacciDigits(1000)
  }

  def fibonacciDigits(digits: Int): BigInt = {
    @tailrec
    def fibonacci(p1: BigInt, p2: BigInt, current: BigInt, index: Int): BigInt =
      if (current.toString.length == digits) index
      else {
        fibonacci(p2, current, p2 + current, index + 1)
      }

    fibonacci(1, 1, 2, 3)
  }
}
