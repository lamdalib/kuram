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

  test("combine string values"):
    val (s1, s2) = ("foo", "bar")

    val expected = s1 + s2
    val obtained1 = Semigroup[String].combine(s1, s2)
    val obtained2 = s1 |+| s2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("combine list of integer values"):
    val (l1, l2) = (List(1, 2, 3), List(4, 5))

    val expected = l1 ++ l2
    val obtained1 = Semigroup[List[Int]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("combine list of string values"):
    val (l1, l2) = (List("a", "b", "c"), List("d", "e"))

    val expected = l1 ++ l2
    val obtained1 = Semigroup[List[String]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)


  test("combine list of integer lists lists"):
    val (l1, l2) = (List(List(1), List(2, 3), List(4)), List(List(5, 6), List(7)))

    val expected = l1 ++ l2
    val obtained1 = Semigroup[List[List[Int]]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)
end SemigroupSuite
