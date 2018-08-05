import scala.annotation.tailrec
import scala.math.BigInt

object EulerHelper {

  implicit class BigIntExp(n: BigInt) {
    def **(power: Int): BigInt = {
      n.pow(power)
    }

    def factorial(): BigInt = {
      @tailrec
      def factorial(n: BigInt, acc: BigInt): BigInt = {
        if (n == 0) acc
        else factorial(n - 1, acc * n)
      }
      factorial(n, 1)
    }
  }

}
