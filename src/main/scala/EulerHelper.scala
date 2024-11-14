import scala.annotation.tailrec
import scala.math.BigInt

object EulerHelper {

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
