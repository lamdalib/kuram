package lamda.tests

import lamda.Semigroup
import lamda.instances.all._

class SemigroupSuite extends munit.FunSuite {
  test("combine should merge two values correctly") {
    val o1 = Some(1)
    val o2 = Some(2)
    val expected = Some(3)
    val obtained = Semigroup[Option[Int]].combine(o1, o2)
    assert(expected == obtained)
  }
}
