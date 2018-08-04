import scala.io.Source

object Euler011 extends EulerApp {

  override def execute(): Long = {
    findLagestProduct(prepareData)
  }

  def prepareData(): Array[Array[Int]] = {
    (for (line <- Source.fromURL(getClass.getResource("Euler011.data")).getLines())
      yield line.split(" ").map(_.toInt)).toArray
  }

  def findLagestProduct(matrix: Array[Array[Int]]): Long = {
    var max: Long = 0

    for (x <- 0 until matrix.length; j <- 0 until matrix(x).length) {
      if (x < matrix.length - 3) {
        max = Math.max(max, matrix(x)(j) * matrix(x + 1)(j) * matrix(x + 2)(j) * matrix(x + 3)(j))
      }

      if (j < matrix(x).length - 3) {
        max = Math.max(max, matrix(x)(j) * matrix(x)(j + 1) * matrix(x)(j + 2) * matrix(x)(j + 3))
      }

      if ((j < matrix(x).length - 3) && (x > 3)) {
        max = Math.max(max, matrix(x)(j) * matrix(x - 1)(j + 1) * matrix(x - 2)(j + 2) * matrix(x - 3)(j + 3))
      }

      if ((j < matrix(x).length - 3) && (x < matrix.length - 3)) {
        max = Math.max(max, matrix(x)(j) * matrix(x + 1)(j + 1) * matrix(x + 2)(j + 2) * matrix(x + 3)(j + 3))
      }
    }

    max
  }
}
