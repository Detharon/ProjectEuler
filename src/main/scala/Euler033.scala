import scala.annotation.tailrec

object Euler033 extends EulerApp {

  override def execute(): Int = (for {
    nominator <- 1 to 99
    denominator <- 1 to 99
  } yield Fraction(nominator, denominator))
    .filter(_.isDigitCancelling)
    .reduce((a, b) => a.product(b))
    .denominator

  private case class Fraction(numerator: Int, denominator: Int) {
    val isDigitCancelling: Boolean = {
      if (numerator >= denominator) false
      else {
        numerator.toString.find(d => d != '0' && denominator.toString.contains(d)) match
          case None => false
          case Some(commonDigit) => this.cancelDigit(commonDigit).contains(this)
      }
    }

    private def cancelDigit(digit: Int): Option[Fraction] = for {
      newNumerator <- numerator.toString.filterNot(_ == digit) match
        case "" => None
        case digits => Some(digits)
      newDenominator <- denominator.toString.filterNot(_ == digit) match
        case "" => None
        case digits => Some(digits)
    } yield Fraction(newNumerator.toInt, newDenominator.toInt)

    def product(fraction: Fraction): Fraction = {
      @tailrec
      def gcd(a: Int, b: Int): Int = {
        if (b == 0) a
        else gcd(b, a % b)
      }

      val newNumerator = this.numerator * fraction.numerator
      val newDenominator = this.denominator * fraction.denominator
      val greatestCommonDenominator = gcd(newNumerator, newDenominator)

      Fraction(newNumerator / greatestCommonDenominator, newDenominator / greatestCommonDenominator)
    }

    override def equals(obj: Any): Boolean = {
      obj match
        case Fraction(numerator, denominator) =>
          numerator.toDouble / denominator == this.numerator.toDouble / this.denominator
        case _ => false
    }
  }
}