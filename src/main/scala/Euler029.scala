object Euler029 extends EulerApp {

  override def execute(): Any = {
    val results = for {
      a <- 2 to 100
      b <- 2 to 100
    } yield BigInt(a).pow(b)

    results.distinct.length
  }

}
