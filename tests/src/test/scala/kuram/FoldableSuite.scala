package kuram

import semigroup.syntax.*
import foldable.syntax.*

import monoid.instances.given
import foldable.instances.given

class FoldableSuite extends munit.FunSuite:
  test("right foldable on list"):
    val strList = List("a", "b", "c")
    val expected1 = "abc"
    val obtained1 = strList.foldRight("")(_ |+| _)
    assertEquals(obtained1, expected1)

    val intList = List(1, 2, 3)
    val expected2 = 6
    val obtained2 = intList @>> (0, _ |+| _)

    assertEquals(obtained2, expected2)

  test("left foldable on list"):
    val strList = List("a", "b", "c")
    val expected1 = "abc"
    val obtained1 = strList.foldLeft("")(_ |+| _)
    assertEquals(obtained1, expected1)

    val intList = List(1, 2, 3)
    val expected2 = 6
    val obtained2 = intList @<< (0, _ |+| _)

    assertEquals(obtained2, expected2)

  test("foldMap on list"):
    val intList = List(1, 2, 3)
    val expected = 12
    val obtained = intList.foldMap(_ * 2)

    assertEquals(obtained, expected)

end FoldableSuite
