import scala.annotation.tailrec

object Euler015 extends EulerApp {
  override def execute(): Long = countLatticePaths(21, 21)

  private def countLatticePaths(length: Int, height: Int): Long = {
    val lattice = fillLatticeArray(length, height)
    lattice(length - 1)(height - 1)
  }

  @tailrec
  private def fillLatticeArray(length: Int, height: Int, currentResult: Seq[Seq[Long]] = Seq.empty): Seq[Seq[Long]] =
    currentResult match {
      // First row, we'll fill it with ones
      case Nil =>
        fillLatticeArray(
          length,
          height,
          Seq(Seq.fill(length)(1L))
        )
      // All rows filled, time to finish
      case other if other.size == height => currentResult
      // Filling the next row, but not the first one
      case other =>
        val nextRowNum = currentResult.size
        val previousRow = currentResult(nextRowNum - 1)
        fillLatticeArray(
          length,
          height,
          currentResult :+ previousRow.foldLeft(List.empty) { case (result, previousRowNum) =>
            result :+ result.lastOption.getOrElse(0L) + previousRowNum
          }
        )
    }
}
