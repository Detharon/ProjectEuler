import scala.io.Source
import scala.util.{Failure, Success, Using}

abstract class EulerApp {
  def execute(): Any

  def main(args: Array[String]): Unit = {
    warmUp()
    measure(execute)
  }

  def loadFileAsLines(): List[String] = {
    Using(Source.fromResource(problemFileName)) { file =>
      file.getLines().toList
    } match {
      case Failure(exception) => throw new Exception(s"File $problemFileName was not found or couldn't be processed.")
      case Success(value)     => value
    }
  }

  /** Helper method to get the name of the data file that corresponds to the class name. Data files have the same name
    * as the class files, but a different extension. This method also strips the M suffix.
    *
    * Examples:
    *   - Euler010M.scala returns Euler010.txt
    *   - Euler011.scala returns Euler011.txt
    *
    * @return
    *   the file name
    */
  private def problemFileName: String =
    (getClass.getSimpleName.init match {
      case mutable if mutable.endsWith("M") => mutable.init
      case other                            => other
    }) + ".txt"

  private def warmUp(): Unit =
    (0 to 25).foreach(_ ^ 5)

  private def measure(f: () => Any): Unit = {
    val start = System.currentTimeMillis()
    println(s"Result: ${f()}")
    val time = System.currentTimeMillis() - start
    println(s"Elapsed time: $time ms")
  }
}
