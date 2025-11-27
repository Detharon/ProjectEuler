object Euler022 extends EulerApp {
  override def execute(): Any =
    loadFileAsLines()
      .flatMap(_.split(","))
      .view
      .map(name => name.slice(1, name.length - 1))
      .sorted
      .zipWithIndex
      .map { case (name, index) =>
        name.map(c => (c - 'A') + 1).sum * (index + 1)
      }.sum
}
