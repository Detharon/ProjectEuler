import scala.annotation.tailrec

object Euler026 extends EulerApp {
  override def execute(): Any = (1 to 1000).maxBy(findCycleLength)

  private type Index = Int
  private type Remainder = Int

  private def findCycleLength(divisor: Int): Int = {

    @tailrec
    def findCycleLength(remainder: Int, pastResults: Map[Remainder, Index]): Int = {
      pastResults.get(remainder) match {
        // If it's in the map, then we've found the cycle
        case Some(index) => pastResults.size - index
        case None        =>
          remainder match {
            case 0                        => pastResults.size
            case r if remainder < divisor =>
              val newRemainder = remainder * 10
              findCycleLength(newRemainder, pastResults + (remainder -> pastResults.size))
            case r =>
              val newRemainder = (remainder % divisor) * 10
              findCycleLength(
                newRemainder,
                pastResults + (remainder -> pastResults.size)
              )
          }
      }
    }

    findCycleLength(remainder = 10, pastResults = Map.empty)
  }
}
