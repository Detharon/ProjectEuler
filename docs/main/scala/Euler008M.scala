import scala.collection.mutable.ListBuffer

/**
 * Problem 8:
 * The four adjacent digits in the 1000-digit number that have the greatest product are:
 * 9 × 9 × 8 × 9 = 5832
 *
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product.
 * What is the value of this product?
 *
 * --------------------------------------------------
 *
 * Explanation:
 * This implementation improves upon the previous functional solution. That version was concise and easy to reason about,
 * but its performance suffered as a result.
 *
 * In the earlier approach, the sliding windows were completely independent of one another.
 * This meant that when calculating the product of 13 elements, we were performing 11 redundant multiplications
 * in each neighboring window. Instead, we can maintain a running product — dividing by the digit that leaves the window
 * and multiplying by the new digit that enters it. The only catch here are the zeroes that break the computation chain.
 *
 * While this approach is definitely faster, it’s also more error-prone and harder to read.
 * For example, consider the following line:
 *
 * {{{
 * previous = buffer.remove(0).asDigit
 * }}}
 *
 * It’s not immediately clear that `remove(0)` returns the element being removed.
 * Moreover, if the `.asDigit` call were omitted, the code would still compile and run,
 * but it would silently produce an incorrect result. For such a small task, the optimization is not worth
 * the effort, so treat it merely as a mental exercise.
 */
object Euler008M extends EulerApp {
  override def execute(): Any = {
    val number = "73167176531330624919225119674426574742355349194934" +
      "96983520312774506326239578318016984801869478851843" +
      "85861560789112949495459501737958331952853208805511" +
      "12540698747158523863050715693290963295227443043557" +
      "66896648950445244523161731856403098711121722383113" +
      "62229893423380308135336276614282806444486645238749" +
      "30358907296290491560440772390713810515859307960866" +
      "70172427121883998797908792274921901699720888093776" +
      "65727333001053367881220235421809751254540594752243" +
      "52584907711670556013604839586446706324415722155397" +
      "53697817977846174064955149290862569321978468622482" +
      "83972241375657056057490261407972968652414535100474" +
      "82166370484403199890008895243450658541227588666881" +
      "16427171479924442928230863465674813919123162824586" +
      "17866458359124566529476545682848912883142607690042" +
      "24219022671055626321111109370544217506941658960408" +
      "07198403850962455444362981230987879927244284909188" +
      "84580156166097919133875499200524063689912560717606" +
      "05886116467109405077541002256983155200055935729725" +
      "71636269561882670428252483600823257530420752963450"
    val windowSize = 13
    val buffer = ListBuffer.from(number.take(windowSize).toCharArray)
    var i = windowSize - 1
    var previous = 1
    var maxProduct = 0L
    var currentProduct = buffer.map(_.asDigit.toLong).product

    while (i < number.length - 1) {
      if (buffer.contains('0')) currentProduct = 0
      else if (currentProduct == 0) {
        // The product is zero even though buffer doesn't contain any, time to recalculate
        currentProduct = buffer.map(_.asDigit.toLong).product
      } else {
        currentProduct = currentProduct / previous * number(i).asDigit
      }

      if (currentProduct > maxProduct) maxProduct = currentProduct

      previous = buffer.remove(0).asDigit
      i = i + 1
      buffer.append(number(i))
    }

    maxProduct
  }
}
