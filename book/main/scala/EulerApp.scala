import scala.io.Source
import scala.util.Using

abstract class EulerApp {
  def execute(): Any

  def main(args: Array[String]): Unit = {
    warmUp()
    measure(execute)
  }

  def loadFileAsLines(): List[String] =
    Using(Source.fromResource(s"${getClass.getSimpleName.init}.txt"))(_.getLines().toList).get

  private def warmUp(): Unit = 
    (0 to 25).foreach(_ ^ 5)

  private def measure(f: () => Any): Unit = {
    val start = System.currentTimeMillis()
    println(s"Result: ${f()}")
    val time = System.currentTimeMillis() - start
    println(s"Elapsed time: $time ms")
  }
}
