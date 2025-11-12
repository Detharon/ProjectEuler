object Euler058 extends EulerApp {
  override def execute(): Any = spiralNumbers(
    n = 3,
    step = 2
  )

  private def spiralNumbers(n: Int, step: Int, spiralPosition: Int = 1, primes: Int = 0, allNums: Int = 1): Int = {
    val isPrime = EulerHelper.naiveIsPrime(n)
    val totalPrimes = if (isPrime) primes + 1 else primes
    val newAllNums = allNums + 1

    val ratio = totalPrimes.toDouble / newAllNums
    val width = if (spiralPosition == 0) step - 1 else step + 1
    println(s"n is $n, primes $totalPrimes, all $newAllNums, ratio is $ratio, width is $width")

    if (ratio < 0.10) width
    else {
      val nextSpiralPosition = if (spiralPosition == 3) 0 else spiralPosition + 1
      val nextStep = if (nextSpiralPosition == 0) step + 2 else step
      spiralNumbers(
        n + step,
        nextStep,
        nextSpiralPosition,
        totalPrimes,
        newAllNums
      )
    }
  }
}
