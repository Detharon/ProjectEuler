object Euler024 extends EulerApp {
  override def execute(): String = "0123456789".permutations.drop(999_999).next()
}
