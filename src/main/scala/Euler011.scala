import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Euler011 extends EulerApp {

    def prepareData(): Array[Array[Int]] = {
        val data: ArrayBuffer[Array[Int]] = ArrayBuffer()
        for (line <- Source.fromURL(getClass.getResource("Euler011.data")).getLines()) {
            data += line.split(" ").map(_.toInt)
        }

        data.toArray
    }


    def findLagestProduct(matrix: Array[Array[Int]]): Long = {
        var max: Long = 0

        for (i <- 0 until matrix.length; j <- 0 until matrix(i).length) {
            if (i < matrix.length - 3) {
                max = Math.max(max, matrix(i)(j) * matrix(i + 1)(j) * matrix(i + 2)(j) * matrix(i + 3)(j))
            }

            if (j < matrix(i).length - 3) {
                max = Math.max(max, matrix(i)(j) * matrix(i)(j+1) * matrix(i)(j+2) * matrix(i)(j+3))
            }

            if ((j < matrix(i).length - 3) && (i > 3)) {
                max = Math.max(max, matrix(i)(j) * matrix(i-1)(j+1) * matrix(i-2)(j+2) * matrix(i-3)(j+3))
            }

            if ((j < matrix(i).length - 3) && (i < matrix.length - 3)) {
                max = Math.max(max, matrix(i)(j) * matrix(i+1)(j+1) * matrix(i+2)(j+2) * matrix(i+3)(j+3))
            }
        }

        max
    }

    override def execute() {
        println(findLagestProduct(prepareData))
    }
}
