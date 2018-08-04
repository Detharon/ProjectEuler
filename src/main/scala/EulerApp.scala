abstract class EulerApp {
  def execute(): Unit

  def main(args: Array[String]): Unit = {
    warmUp()

    val start = System.currentTimeMillis()
    execute()
    val time = System.currentTimeMillis() - start

    println(s"Elapsed time: $time ms")
  }

  def warmUp(): Unit = {
    (0 to 25).foreach(_ ^ 5)
  }
}
