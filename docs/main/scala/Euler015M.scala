object Euler015M extends EulerApp {
  override def execute(): Long = countLatticePaths(21, 21)

  private def countLatticePaths(length: Int, height: Int): Long = {
    val prevRow = Array.fill(length)(1L)

    for (_ <- 1 until height) {
      for (col <- 1 until length) {
        prevRow(col) += prevRow(col - 1)
      }
    }

    prevRow(length - 1)
  }
}
