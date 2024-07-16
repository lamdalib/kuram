package kuram

import SemigroupSyntax._
import SemigroupInstances.given

class SemigroupSuite extends munit.FunSuite:
  test("combine int values"):
    val (x, y) = (1, 2)

    val expected = x + y
    val obtained1 = Semigroup[Int].combine(x, y)
    val obtained2 = x |+| y

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

end SemigroupSuite
