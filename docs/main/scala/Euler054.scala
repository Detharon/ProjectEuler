/** In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
  *
  * High Card: Highest value card. One Pair: Two cards of the same value. Two Pairs: Two different pairs. Three of a
  * Kind: Three cards of the same value. Straight: All cards are consecutive values. Flush: All cards of the same suit.
  * Full House: Three of a kind and a pair. Four of a Kind: Four cards of the same value. Straight Flush: All cards are
  * consecutive values of same suit. Royal Flush: Ten, Jack, Queen, King, Ace, in same suit. The cards are valued in the
  * order: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
  *
  * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of
  * eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of
  * queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next
  * highest cards are compared, and so on.
  *
  * Consider the following five hands dealt to two players:
  *
  * Hand Player 1 Player 2 Winner 1 5H 5C 6S 7S KD Pair of Fives 2C 3S 8S 8D TD Pair of Eights Player 2 2 5D 8C 9S JS AC
  * Highest card Ace 2C 5C 7D 8S QH Highest card Queen Player 1 3 2D 9C AS AH AC Three Aces 3D 6D 7D TD QD Flush with
  * Diamonds Player 2 4 4D 6S 9H QH QC Pair of Queens Highest card Nine 3D 6D 7H QD QS Pair of Queens Highest card Seven
  * Player 1 5 2H 2D 4C 4D 4S Full House With Three Fours 3C 3D 3S 9S 9D Full House with Three Threes Player 1
  *
  * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten
  * cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You
  * can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific
  * order, and in each hand there is a clear winner.
  *
  * How many hands does Player 1 win?
  *
  * --------------------------------------------------
  *
  * Explanation: It's a pretty good example from the business domain modeling perspective, which is where Scala shines.
  */
object Euler054 extends EulerApp {

  override def execute(): Any = {
    loadFileAsLines()
      .map { line =>
        val (player1cards, player2cards) = parseLine(line)
        val (player1rank, player2rank) = (handRank(player1cards), handRank(player2cards))

        println(s"${player1cards.sorted} -> $player1rank")
        println(s"${player2cards.sorted} -> $player2rank")

        (player1rank, player2rank)
      }
      .count { case (player1rank, player2rank) => player1rank > player2rank }
  }

  enum Color(val symbol: Char) {
    case Spades extends Color('S')
    case Hearts extends Color('H')
    case Clubs extends Color('C')
    case Diamonds extends Color('D')
  }

  private object Color {
    def fromChar(c: Char): Option[Color] = Color.values.find(_.symbol == c)
  }

  sealed trait Card {
    val color: Color
  }

  opaque type CardNumber <: Int = Int
  opaque type CardFace <: Char = Char

  case class NumberedCard(value: CardNumber, color: Color) extends Card

  case class FaceCard(value: CardFace, color: Color) extends Card

  private def cardRank(card: Card): Int = card match {
    case NumberedCard(value, _) => value // From 2 to 9
    case FaceCard(value, _)     =>
      value.toUpper match {
        case 'T'   => 10
        case 'J'   => 11
        case 'Q'   => 12
        case 'K'   => 13
        case 'A'   => 14
        case other => throw new IllegalArgumentException(s"Illegal card $other found")
      }
  }

  given Ordering[Card] with
    def compare(x: Card, y: Card): Int = cardRank(x).compare(cardRank(y))

  private def parseLine(line: String): (List[Card], List[Card]) =
    line
      .split(" ")
      .map(parseCard)
      .zipWithIndex
      .toList
      .partitionMap {
        case (card, idx) if idx < 5 => Left(card)
        case (card, idx)            => Right(card)
      }

  private def parseCard(card: String): Card = card.toList match {
    case value :: colorChar :: Nil =>
      val color = Color
        .fromChar(colorChar)
        .getOrElse(
          throw new RuntimeException(s"Found unknown color $colorChar")
        )
      if (value.isDigit) NumberedCard(value.asDigit, color)
      else FaceCard(value, color)
    case unknown =>
      throw new RuntimeException(s"Found unsupported card $unknown, aborting.")
  }

  opaque type Hand = List[Card]

  private def handRank(hand: Hand): Int = {
    val groupedByColors = hand.groupBy(_.color)
    val groupedByValue = hand.groupBy(cardRank).values.toList.sortBy(_.size)
    val inOrder = handInOrder(hand)

    if (groupedByColors.size == 1) {
      // Royal / Straight Flush
      if (inOrder) 800 + cardRank(hand.maxBy(cardRank))
      // Flush
      else 500 + cardRank(hand.maxBy(cardRank))
    } else if (inOrder) {
      // Straight
      400 + cardRank(hand.maxBy(cardRank))
    } else {
      groupedByValue match {
        // Four of a Kind
        case c :: d :: Nil if c.size == 1 && d.size == 4 => 700 + cardRank(d.head)
        // Full House
        case c :: d :: Nil if c.size == 2 && d.size == 3 => 600 + cardRank(d.head)
        // Three of a Kind
        case a :: b :: c :: Nil if c.size == 3 => 300 + cardRank(c.head)
        // Two pairs
        case a :: b :: c :: Nil if c.size == 2 => 200 + Math.max(cardRank(c.head), cardRank(b.head))
        // One pair
        case a :: b :: c :: d :: Nil if d.size == 2 => 100 + cardRank(d.head)
        // High card
        case other => other.flatten.map(cardRank).max
      }
    }

  }

  private def handInOrder(hand: Hand): Boolean = {
    val cardRanks = hand.sorted.map(cardRank)
    cardRanks.zip(cardRanks.tail).forall { case (previous, next) =>
      next - previous == 1
    }
  }
}
