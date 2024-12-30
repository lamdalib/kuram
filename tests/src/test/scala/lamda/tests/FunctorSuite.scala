package lamda.tests

import lamda.Functor
import lamda.instances.all._

class FunctorSuite extends munit.FunSuite {
  test("map should apply function to wrapped value") {
    val o1: Option[Int] = Some(1)
    val expected = Some(2)
    val obtained = Functor[Option].map(o1)(_ + 1)
    assert(obtained == expected)
  }

  test("as should replace wrapped value with new value") {
    val o1: Option[Int] = Some(1)
    val expected = Some("hello")
    val obtained = Functor[Option].as(o1)("hello")
    assert(obtained == expected)
  }

  test("void should replace wrapped value with unit") {
    val o1: Option[Int] = Some(1)
    val expected = Some(())
    val obtained = Functor[Option].void(o1)
    assert(obtained == expected)
  }

  test("lift should create function operating on wrapped values") {
    val o1: Option[Int] = Some(1)
    val expected = Some(2)
    val obtained = Functor[Option].lift((a: Int) => a + 1)(o1)
    assert(obtained == expected)
  }
}
