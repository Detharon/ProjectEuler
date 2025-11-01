import scala.annotation.tailrec
import scala.math.BigInt

object EulerHelper {

  extension (n: Int) {
    def naiveIsPrime: Boolean = n match {
      case n if n < 2 => false
      case _ => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)
    }
  }

  extension (n: Long) {
    def naiveIsPrime: Boolean = n match {
      case n if n < 2 => false
      case _ => (2L to Math.sqrt(n.toDouble).toLong).forall(n % _ != 0)
    }
  }

  extension (n: BigInt) {
    def **(power: Int): BigInt = n.pow(power)

    def factorial: BigInt = {
      @tailrec
      def factorial(n: BigInt, acc: BigInt): BigInt = {
        if (n == 0) acc
        else factorial(n - 1, acc * n)
      }

      factorial(n, 1)
    }
  }
  
  extension (s: String) {
    def isPalindrome: Boolean = {
      @tailrec
      def isPalindrome(s: String): Boolean = {
        if (s.length < 2) true
        else if (s.head == s.last) isPalindrome(s.substring(1, s.length - 1))
        else false
      }

      isPalindrome(s)
    }
  }
}
