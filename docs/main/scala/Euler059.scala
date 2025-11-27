object Euler059 extends EulerApp {
  override def execute(): Any = {
    val originalData = readFile()

    val a = 'a'.toInt
    val z = 'z'.toInt
    val possibleCiphers = for {
      first <- a to z
      second <- a to z
      third <- a to z
    } yield Array(first, second, third)

    possibleCiphers.view
      .map(cipher => decrypt(originalData, cipher))
      .find(containsManyWords)
      .get
      .sum
  }

  private def containsManyWords(input: Array[Int]): Boolean = {
    val string = input.map(_.toChar).mkString
    // TODO: Try finding words (alphanumeric tokens)
    if (string.contains("Euler")) {
      println(string)
      true
    } else false
  }

  private def decrypt(input: Array[Int], cipher: Array[Int]): Array[Int] = {
    val output = input.clone()
    var i = 0
    while (i < input.length) {
      output(i) = input(i) ^ cipher(i % 3)
      i += 1
    }
    output
  }

  private def readFile(): Array[Int] =
    loadFileAsLines().head.split(",").map(c => c.toInt)
}
