import EulerHelper._

import scala.language.postfixOps

object Euler020 extends EulerApp {
  override def execute(): Any = {
    readDigits(BigInt(100).factorial()) sum
  }

  def readDigits(n: BigInt): Stream[Short] = {
    if (n == 0) Stream.Empty
    else (n % 10).toShort #:: readDigits(n / 10)
  }
}
