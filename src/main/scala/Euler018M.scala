object Euler018M extends EulerApp {
  override def execute(): Int = {
    val dataArray: Array[Array[Int]] = prepareData()
    var rowIndex = dataArray.length - 2

    while (rowIndex >= 0) {
      dataArray(rowIndex).zipWithIndex.foreach { case (value, columnIndex) =>
        val childrenRow = dataArray(rowIndex + 1)
        val maxChildValue = Math.max(childrenRow(columnIndex), childrenRow(columnIndex + 1))
        dataArray(rowIndex)(columnIndex) = dataArray(rowIndex)(columnIndex) + maxChildValue
      }
      rowIndex -= 1
    }

    dataArray.head.head
  }

  private def prepareData(): Array[Array[Int]] =
    loadFileAsLines().toArray.map(_.split(" ").map(_.toInt).toArray)
}
