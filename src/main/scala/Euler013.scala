object Euler013 extends EulerApp {
  override def execute(): String =
    loadFileAsLines().map(BigInt.apply).sum.toString().take(10)
}
