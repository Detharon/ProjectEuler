import scala.collection.immutable.Seq
import scala.collection.mutable

object Euler018 extends EulerApp {
  override def execute(): Any = {
    given ordering: Ordering[Route] = Ordering.by[Route, Int](_.sum)

    val data: Seq[Seq[Int]] = prepareData()
    val root = Route.start(data.head.head)
    val priorityQueue = mutable.PriorityQueue(root)(using Ordering.by[Route, Int](_.sum))
    var max = 0

    while (priorityQueue.nonEmpty) {
      val topRoute = priorityQueue.dequeue()
      println(topRoute)
      val children = generateChildren(topRoute, data)
      priorityQueue.enqueue(children*)
      max = Math.max(max, topRoute.sum)
    }

    max
  }

  private def prepareData(): Seq[Seq[Int]] =
    loadFileAsLines().map(_.split(" ").map(_.toInt).toSeq)

  private def generateChildren(route: Route, data: Seq[Seq[Int]]): List[Route] = {
    route.depth match {
      case depth if depth == data.size - 1 => List.empty
      case _ =>
        val lastIndex = route.lastIndex
        val childRow = data(route.depth)
        List(
          route.withElement(childRow(lastIndex), lastIndex),
          route.withElement(childRow(lastIndex + 1), lastIndex + 1)
        )
    }
  }

  case class Route(path: List[Int], lastIndex: Int) {
    def depth: Int = path.length
    def sum: Int = path.sum
    def withElement(element: Int, index: Int): Route = Route(path :+ element, index)
  }

  object Route {
    def start(x: Int): Route = Route(path = List(x), lastIndex = 0)
  }
}
