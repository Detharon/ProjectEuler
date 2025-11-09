object Euler005 extends EulerApp {
  // 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 = 9699690
  override def execute(): Int = Iterator
    .from(9699690, 9699690)
    .find { n =>
      (2 to 20).forall(k => n % k == 0)
    }
    .get

}
