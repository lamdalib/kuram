package kuram

import SemigroupSyntax.*
import MonoidInstances.given

class MonoidSuite extends munit.FunSuite:
  test("combine int values"):
    val (x, y) = (1, 2)

    val expected = x + y
    val obtained1 = Monoid[Int].combine(x, y)
    val obtained2 = x |+| y

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("combine string values"):
    val (s1, s2) = ("foo", "bar")

    val expected = s1 + s2
    val obtained1 = Monoid[String].combine(s1, s2)
    val obtained2 = s1 |+| s2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("combine list of integer values"):
    val (l1, l2) = (List(1, 2, 3), List(4, 5))

    val expected = l1 ++ l2
    val obtained1 = Monoid[List[Int]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("combine list of string values"):
    val (l1, l2) = (List("a", "b", "c"), List("d", "e"))

    val expected = l1 ++ l2
    val obtained1 = Monoid[List[String]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)


  test("combine list of integer lists lists"):
    val (l1, l2) = (List(List(1), List(2, 3), List(4)), List(List(5, 6), List(7)))

    val expected = l1 ++ l2
    val obtained1 = Monoid[List[List[Int]]].combine(l1, l2)
    val obtained2 = l1 |+| l2

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)

  test("associativity"):
    val (a, b, c) = (1, 2, 3)
    
    val expected1 = a + (b + c)
    val expected2 = (a + b) + c

    val obtained1 = a |+| (b |+| c)
    val obtained2 = (a |+| b) |+| c


    assertEquals(obtained1, expected1)
    assertEquals(obtained2, expected2)
    assertEquals(obtained1, obtained2)

  test("homomorphism"):
    val (s1, s2) = ("hello", "world")

    val expected1 = s1.length + s2.length
    val expected2 = (s1 + s2).length

    val obtained1 = s1.length |+| s2.length
    val obtained2 = (s1 |+| s2).length

    assertEquals(obtained1, expected1)
    assertEquals(obtained2, expected2)
    assertEquals(obtained1, obtained2)

  test("identity element"):
    val expected = ""
    val obtained = Monoid[String].empty

    assertEquals(obtained, expected)

end MonoidSuite
