package kuram

import SemigroupSyntax.*
import FoldableSyntax.*

import MonoidInstances.given
import FoldableInstances.given

class FoldableSuite extends munit.FunSuite:
  test("right foldable on list"):
    val strList = List("a", "b", "c")
    val expected1 = "abc"
    val obtained1 = strList @>> ("", _ |+| _)
    assertEquals(obtained1, expected1)

    val intList = List(1, 2, 3)
    val expected2 = 6
    val obtained2 = intList @>> (0, _ |+| _)

    assertEquals(obtained2, expected2)

  test("left foldable on list"):
    val strList = List("a", "b", "c")
    val expected1 = "abc"
    val obtained1 = strList @<< ("", _ |+| _)
    assertEquals(obtained1, expected1)

    val intList = List(1, 2, 3)
    val expected2 = 6
    val obtained2 = intList @<< (0, _ |+| _)

    assertEquals(obtained2, expected2)

  test("foldMap on list"):
    val intList = List(1, 2, 3)
    val expected = 12
    val obtained = intList @@ (_ * 2)

    assertEquals(obtained, expected)

end FoldableSuite
