object Euler016 extends EulerApp {
  override def execute(): Int = BigInt(2).pow(1000).toString().map(_.asDigit).sum
}
