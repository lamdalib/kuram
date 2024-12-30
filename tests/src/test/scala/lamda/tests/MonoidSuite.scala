package lamda.tests

import lamda.Monoid
import lamda.instances.all._

class MonoidSuite extends munit.FunSuite {
  test("empty should return identity element for the type") {
    val expected = None
    val obtained = Monoid[Option[Int]].empty
    assert(expected == obtained)
  }
}
