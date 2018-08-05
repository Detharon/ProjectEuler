object Euler001 extends EulerApp {
    override def execute(): Int = {
        val threes = 3 until 1000 by 3 sum
        val fives = 5 until 1000 by 5 sum
        val fifteens = 15 until 1000 by 15 sum

        threes + fives - fifteens
    }
}
