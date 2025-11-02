object Euler004 extends EulerApp {
  override def execute(): Int = (for {
    a <- 990 to 100 by -11
    b <- 999 to 100 by -1
  } yield a * b)
    .filter(n => isPalindrome(n.toString))
    .max

  def isPalindrome(s: String): Boolean = s == s.reverse
}
