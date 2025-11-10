object Euler011M extends EulerApp {

  override def execute(): Long = findLargestProduct(prepareData())

  def prepareData(): Array[Array[Int]] =
    loadFileAsLines().map(_.split(" ").map(_.toInt)).toArray

  private def findLargestProduct(matrix: Array[Array[Int]]): Long = {
    var max: Long = 0

    for (col <- matrix.indices; row <- matrix(col).indices) {
      if (row < matrix.length - 3) {
        max = Math.max(max, matrix(row)(col) * matrix(row + 1)(col) * matrix(row + 2)(col) * matrix(row + 3)(col))
      }

      if (col < matrix(row).length - 3) {
        max = Math.max(max, matrix(row)(col) * matrix(row)(col + 1) * matrix(row)(col + 2) * matrix(row)(col + 3))
      }

      if ((col < matrix(row).length - 3) && (row > 3)) {
        max = Math.max(
          max,
          matrix(row)(col) * matrix(row - 1)(col + 1) * matrix(row - 2)(col + 2) * matrix(row - 3)(col + 3)
        )
      }

      if ((col < matrix(row).length - 3) && (row < matrix.length - 3)) {
        max = Math.max(
          max,
          matrix(row)(col) * matrix(row + 1)(col + 1) * matrix(row + 2)(col + 2) * matrix(row + 3)(col + 3)
        )
      }
    }

    max
  }
}
