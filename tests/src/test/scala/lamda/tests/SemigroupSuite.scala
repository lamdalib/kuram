package lamda.tests

import lamda.Semigroup
import lamda.instances.all._
import lamda.syntax.semigroup._

class SemigroupSuite extends munit.FunSuite {
  test("combine should merge two values correctly") {
    val o1 = Option(1)
    val o2 = Option(2)
    val expected = Some(3)
    val obtained = Semigroup[Option[Int]].combine(o1, o2)
    val obtained2 = o1 |+| o2
    assert(obtained == expected)
    assert(obtained2 == expected)
  }
}
