object EulerHelper {

  implicit class BigIntExp(n: BigInt) {
    def **(power: Int): BigInt = {
      n.pow(power)
    }
  }

}
