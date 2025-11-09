object Euler009M extends EulerApp {
  override def execute(): Any = {
    var a = 1
    var b = 2
    var c = 997
    var found = false

    while (!found) {
      if (a * a + b * b == c * c) found = true
      else {
        if (b == c - 1 || b == c - 2) {
          a = a + 1
          b = a + 1
          c = 1000 - b - a
        } else {
          b = b + 1
          c = c - 1
        }
      }
    }

    a * b * c
  }
}
