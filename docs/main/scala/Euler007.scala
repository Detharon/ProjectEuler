import EulerHelper.naiveIsPrime

/** Problem 7: By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10001st prime number?
 *
 * --------------------------------------------------
 *
 * Explanation: We can simply brute-force this problem by using the most naive prime check, 
 * since the 10001st prime will be a low number. This solution is pretty fast, even though the algorithm is not very efficient.
 *
 * As you can see, we don't even filter out even numbers higher than 2 (or all the multiplies of already found primes),
 * but we'll need these methods later on. For now, a basic algorithm does pretty well.
 *
 */
object Euler007 extends EulerApp {

  override def execute(): Any = Iterator.from(2)
    .filter(naiveIsPrime)
    .drop(10000)
    .next()
}
