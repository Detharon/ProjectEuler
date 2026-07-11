import scala.annotation.tailrec

object Euler051 extends EulerApp {
  override def execute() = {
    LazyList
      .from(1)
      .flatMap { digits =>
        generatePrimeFamilies(digits).map { primeFamily =>
          (primeFamily, primesCount(primeFamily))
        }
      }
      .find { case (primeFamily, primes) => primes == 8 }
  }

  private def generatePrimeFamilies(totalDigits: Int) = {
    // This generates sequences of indices for wildcards, e.g. a 3-digit number would have
    // (0), (1), (2), (0, 1), (0, 2), (1, 2) indices
    val wildcardIndices = (1 until totalDigits).flatMap { wildcards =>
      (0 until totalDigits).combinations(wildcards)
    }

    @tailrec
    def generate(
        totalDigits: Int,
        iteration: Int,
        wildcardIndex: Seq[Int],
        results: Seq[PrimeFamily]
    ): Seq[PrimeFamily] = {
      if (iteration == totalDigits) results
      else {
        val newDigits: Seq[DigitOrWildcard] =
          if (wildcardIndex.contains(iteration)) Seq(Wildcard)
          else
            (if (iteration == totalDigits - 1) 1 to 9 else 0 to 9)
              .map(Character.forDigit(_, 10))

        val newResults: Seq[PrimeFamily] =
          if (results.isEmpty)
            newDigits.map(newDigit => PrimeFamily(Seq(newDigit)))
          else
            for {
              newDigit <- newDigits
              result <- results
            } yield PrimeFamily(newDigit +: result.digits)

        generate(totalDigits, iteration + 1, wildcardIndex, newResults)
      }
    }

    wildcardIndices.flatMap(generate(totalDigits, 0, _, List.empty))
  }

  private def primesCount(primeFamily: PrimeFamily): Int = {
    val digitsToCheck =
      (if (primeFamily.digits.head == Wildcard) 1 to 9 else 0 to 9).map(n => Character.forDigit(n, 10))

    digitsToCheck
      .map(primeFamily.replaceWildcard)
      .count(isPrime)
  }

  private type DigitOrWildcard = Char | Wildcard.type
  private case object Wildcard
  private case class PrimeFamily(digits: Seq[DigitOrWildcard]) {
    override def toString: String = digits.map {
      case c: Char  => c
      case Wildcard => '*'
    }.mkString

    def replaceWildcard(digit: Char): Int =
      digits
        .map {
          case c: Char  => c
          case Wildcard => digit
        }
        .mkString
        .toInt
  }

  private def isPrime(n: Long): Boolean = n match
    case n if n < 2 => false
    case _          => (2 to Math.sqrt(n.toDouble).toInt).forall(n % _ != 0)
}
