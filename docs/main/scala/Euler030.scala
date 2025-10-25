import EulerHelper._

object Euler030 extends EulerApp {

  override def execute(): Int = 
    Iterator.from(2)
      .takeWhile(_ < 354285)
      .filter(n => digitsToPower(n, 5) == n)
      .sum

  private def digitsToPower(n: BigInt, power: Int): BigInt = 
    getDigits(n).map(BigInt(_) ** power).sum

  private def getDigits(n: BigInt): List[Int] = 
    n.toString().map(_.asDigit).toList
    
}
