abstract class EulerApp {
  def execute(): Any

  def main(args: Array[String]): Unit = {
    warmUp()

    val start = System.currentTimeMillis()
    println(execute())
    val time = System.currentTimeMillis() - start

    println(s"Elapsed time: $time ms")
  }

  def warmUp(): Unit = {
    (0 to 25).foreach(_ ^ 5)
  }
}
