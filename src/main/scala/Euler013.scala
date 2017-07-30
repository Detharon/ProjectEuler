import scala.io.Source

object Euler013 extends EulerApp {
    def prepareData(): Array[Array[Int]] = {
        (for (line <- Source.fromURL(getClass.getResource("Euler013.data")).getLines())
            yield line.split("").map(_.toInt)).toArray
    }

    override def execute(): Unit = {
        val data = prepareData()

        var sum: Long = 0;
        for (column <- (0 until data(0).length) if (sum < 10000000000000L)) {
            for (row <- (0 until data.length)) {
                sum += data(row)(column)
            }
            sum *= 10
        }

        println(sum.toString.substring(0,10))
    }
}
