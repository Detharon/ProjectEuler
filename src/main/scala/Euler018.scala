object Euler018 extends EulerApp {
  override def execute(): Int = {
    val reversedData = prepareData().reverse
    reversedData.tail
      .foldLeft(reversedData.head) { case (previousRow, currentRow) =>
        currentRow.zipWithIndex.map { case (element, index) =>
          element + Math.max(previousRow(index), previousRow(index + 1))
        }
      }.head
  }

  private def prepareData(): List[List[Int]] =
    loadFileAsLines().map(_.split(" ").map(_.toInt).toList)
}
