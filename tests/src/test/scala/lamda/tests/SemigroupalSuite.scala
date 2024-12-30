package lamda.tests

import lamda.Semigroupal
import lamda.syntax.semigroupal._
import lamda.instances.all._

class SemigroupalSuite extends munit.FunSuite {
  test("product should combine two wrapped vlaues into a tuple") {
    val o1 = Some(1)
    val o2 = Some(2)
    val expected = Some((1, 2))
    val obtained = Semigroupal[Option].product(o1, o2)
    assert(obtained == expected)
  }
}
