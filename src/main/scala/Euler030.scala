import EulerHelper.BigIntExp

object Euler030 extends EulerApp {

    def getDigits(n: BigInt): List[Int] = {
        n.toString().map(_.asDigit).toList
    }

    def digitsToPower(n: BigInt, power: Int): BigInt = {
        getDigits(n).map(BigInt(_) ** power).sum
    }

    override def execute(): Unit = {
        val result = Stream.from(2)
                .takeWhile(_ < 354285)
                .filter(n => digitsToPower(n, 5) == n)
                .sum

        println(result)
    }
}
