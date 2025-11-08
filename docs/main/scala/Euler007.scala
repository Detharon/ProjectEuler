import EulerHelper.naiveIsPrime

object Euler007 extends EulerApp {
  override def execute(): Any = Iterator.from(2)
    .filter(naiveIsPrime)
    .drop(10000)
    .next()
}
