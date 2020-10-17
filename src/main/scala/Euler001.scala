object Euler001 extends EulerApp {

    /**
     * Problem 1:
     * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
     * Find the sum of all the multiples of 3 or 5 below 1000.
     *
     * Explanation: The only tricky part of this exercise comes from the fact that we cannot simply count the multiplies of 3,
     * then calculate the multiplies of 5, and add these two numbers up. If we did that, then the numbers that are divisible
     * by 3 and 5 would be calculated twice.
     *
     * The problem can be also solved using the modulo operator
     * 3 until 1000 filter(n => n % 3 == 0 || n % 5 == 0) sum
     */
    override def execute(): Int = {
        val threes = 3 until 1000 by 3 sum
        val fives = 5 until 1000 by 5 sum
        val fifteens = 15 until 1000 by 15 sum

        threes + fives - fifteens
    }
}
