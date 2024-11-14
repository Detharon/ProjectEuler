import EulerHelper._

object Euler020 extends EulerApp {
  override def execute(): Any = {
    readDigits(BigInt(100).factorial()).sum
  }

  private def readDigits(n: BigInt): LazyList[Short] = {
    if (n == 0) LazyList()
    else (n % 10).toShort #:: readDigits(n / 10)
  }
}
