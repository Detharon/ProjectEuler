object Euler043 extends EulerApp {

  override def execute(): Any = generatePandigitals("9876543210")
    .filter(hasDivisibleDigits)
    .map(_.toLong)
    .sum

  private def generatePandigitals(from: String): LazyList[String] =
    LazyList.from(from.permutations.filter(!_.startsWith("0")))

  private def hasDivisibleDigits(digits: String): Boolean = {
    digits.substring(1, 4).toInt % 2 == 0 &&
    digits.substring(2, 5).toInt % 3 == 0 &&
    digits.substring(3, 6).toInt % 5 == 0 &&
    digits.substring(4, 7).toInt % 7 == 0 &&
    digits.substring(5, 8).toInt % 11 == 0 &&
    digits.substring(6, 9).toInt % 13 == 0 &&
    digits.substring(7, 10).toInt % 17 == 0
  }
}
