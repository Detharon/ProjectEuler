object Euler099 extends EulerApp {
  override def execute(): Any =
    readFile().max.index

  case class BigExponential(base: Int, exponent: Int, index: Int) extends Ordered[BigExponential] {
    override def compare(that: BigExponential): Int =
      (Math.log(base) * exponent).compare(Math.log(that.base) * that.exponent)
  }

  private def readFile(): List[BigExponential] =
    loadFileAsLines().zipWithIndex.map { case (line, index) =>
      line.split(",") match {
        case Array(base, exponent) => BigExponential(base.toInt, exponent.toInt, index + 1)
      }
    }

}
