object Euler005 extends EulerApp {

  /**
   * Problem 5: 2520 is the smallest number that can be divided by each of the numbers from
   * 1 to 10 without any remainder.
   *
   * What is the smallest positive number that is evenly divisible by all of the numbers
   * from 1 to 20?
   *
   * --------------------------------------------------
   *
   * Explanation: Any number is evenly divisible by 1, so we can ignore it. We could just increment the number we're
   * checking by 2 and see if it's evenly divisible by all numbers from 2 to 20. But let's improve it even further.
   * If a number is divisible by 20, then it has to be a multiple of 20. So we could check all multiples of 20.
   *
   * Highest prime number here is 19. A number that is divisible both by 19 and 20 has to be a multiple of 380,
   * because 19 * 20 = 380. We can, thus, check every 380 numbers. Obviously we could go further and decrease
   * the numbers to be checked much more, but it already gives a result in a matter of milliseconds, so we can stop here.
   */
  override def execute(): Long = LazyList.from(380, 380).find { n =>
    (2 to 20).forall( k => n % k == 0)
  }.get

}
