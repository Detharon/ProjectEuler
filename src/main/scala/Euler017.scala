object Euler017 extends EulerApp {
  override def execute(): Any = (1 to 1000)
    .map(writeInt)
    .map(_.count(_.isLetter))
    .sum

  private def writeInt(i: Int): String = i match {
    case i if i >= 0 && i < 10     => writeDigit(i)
    case i if i >= 10 && i < 20    => writeTenToNineteen(i)
    case i if i >= 20 && i < 100   => writeTens(i)
    case i if i >= 100 && i < 1000 => writeHundreds(i)
    case 1000                      => "one thousand"
  }

  private def writeDigit(i: Int): String = i match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case 4 => "four"
    case 5 => "five"
    case 6 => "six"
    case 7 => "seven"
    case 8 => "eight"
    case 9 => "nine"
    case _ => throw IllegalArgumentException(s"Received unexpected input: $i")
  }

  private def writeTenToNineteen(i: Int): String = i match {
    case 10 => "ten"
    case 11 => "eleven"
    case 12 => "twelve"
    case 13 => "thirteen"
    case 14 => "fourteen"
    case 15 => "fifteen"
    case 16 => "sixteen"
    case 17 => "seventeen"
    case 18 => "eighteen"
    case 19 => "nineteen"
    case _  => throw IllegalArgumentException(s"Received unexpected input: $i")
  }

  private def writeTens(i: Int): String = {
    val remainder = i % 10
    val maybeRemainder = if (remainder != 0) Some(writeInt(remainder)) else None
    val tens = i - remainder match {
      case 20 => "twenty"
      case 30 => "thirty"
      case 40 => "forty"
      case 50 => "fifty"
      case 60 => "sixty"
      case 70 => "seventy"
      case 80 => "eighty"
      case 90 => "ninety"
      case _  => throw IllegalArgumentException(s"Received unexpected input: $i")
    }

    maybeRemainder match {
      case Some(digits) => s"$tens-$digits"
      case None         => tens
    }
  }

  private def writeHundreds(i: Int): String = {
    val remainder = i % 100
    val maybeRemainder = if (remainder != 0) Some(writeInt(remainder)) else None
    val hundred = s"${writeDigit((i - remainder) / 100)} hundred"

    maybeRemainder match {
      case Some(tens) => s"$hundred and $tens"
      case None       => hundred
    }
  }
}
