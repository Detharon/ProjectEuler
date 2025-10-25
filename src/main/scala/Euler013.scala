object Euler013 extends EulerApp {
  override def execute(): String = {
    val data = prepareData()

    var sum: Long = 0;
    for (column <- data(0).indices if sum < 10000000000000L) {
      for (row <- data.indices) {
        sum += data(row)(column)
      }
      sum *= 10
    }

    sum.toString.substring(0, 10)
  }

  def prepareData(): Array[Array[Int]] = {
    val data = for {
      line <- loadFileAsLines()
    } yield line.split("").map(_.toInt)
    data.toArray
  }
}
