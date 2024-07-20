package kuram

import ComposeSyntax.*
import CategoryInstances.given

class ComposeSuite extends munit.FunSuite:
  test("apply last function"):
    val addOne: Int => Int = _ + 1
    val multiplyByTwo: Int => Int = _ * 2

    val randomValue = System.currentTimeMillis().toInt
    val expected = (addOne compose multiplyByTwo)(randomValue)
    val obtained = (addOne <<< multiplyByTwo)(randomValue)

    assertEquals(obtained, expected)

  test("apply first function"):
    val addOne: Int => Int = _ + 1
    val multiplyByTwo: Int => Int = _ * 2

    val randomValue = System.currentTimeMillis().toInt
    val expected = (addOne andThen multiplyByTwo)(randomValue)
    val obtained = (addOne >>> multiplyByTwo)(randomValue)

    assertEquals(obtained, expected)

  test("associativity"):
    val f: Int => Int = _ + 1
    val g: Int => Int = _ * 2
    val h: Int => Int = _ - 1

    val randomValue = System.currentTimeMillis().toInt
    val expectedLast1 = (f compose (g compose h))(randomValue)
    val obtainedLast1 = (f <<< (g <<< h))(randomValue)

    val expectedLast2 = ((f compose g) compose h)(randomValue)
    val obtainedLast2 = ((f <<< g) <<< h)(randomValue)

    val expectedFirst1 = (f andThen (g andThen h))(randomValue)
    val obtainedFirst1 = (f >>> (g >>> h))(randomValue)

    val expectedFirst2 = ((f andThen g) andThen h)(randomValue)
    val obtainedFirst2 = ((f >>> g) >>> h)(randomValue)

    assertEquals(obtainedLast1, expectedLast1)
    assertEquals(obtainedLast2, expectedLast2)
    assertEquals(obtainedFirst1, expectedFirst1)
    assertEquals(obtainedFirst2, expectedFirst2)

end ComposeSuite
