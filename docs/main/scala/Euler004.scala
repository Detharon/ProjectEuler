import EulerHelper.isPalindrome

object Euler004 extends EulerApp {

  /**
   * Problem 4: A palindromic number reads the same both ways. The largest palindrome made from the product of two
   * 2-digit numbers is 9009 = 91 * 99
   *
   * Find the largest palindrome made from the product of two 3-digit numbers.
   *
   * --------------------------------------------------
   *
   * Explanation: A brute-force solution would also work reasonably well, but we can actually reduce the number of
   * numbers to be checked.
   *
   * We know that multiplying two 3-digit numbers will give us a 6-digit number at most, so the palindrome
   * we're looking for has the "abccba" form, or: 100000a + 10000b + 1000c + 100c + 10b + a.
   * If we add those numbers we get "100001a + 10010b + 1100c".
   * We can then factor out 11, which means that our palindrome has to be divisible by 11:
   * 11(9091a + 910b + 100c)
   *
   * One of the numbers then has to be divisible by 11, because it's a prime number.
   * We can start from 990 instead of 999 and decrease the numbers checked by 11.
   */
  override def execute(): Int = (for {
    a <- 990 to 100 by -11
    b <- 999 to 100 by -1
  } yield a * b)
    .filter(_.toString.isPalindrome)
    .max
}
