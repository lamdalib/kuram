/*
 * Copyright (c) 2024 kattulib
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package kuram

import compose.syntax.*
import compose.instances.given
import category.instances.given

class CategorySuite extends munit.FunSuite:
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

  test("identity"):
    val f: Int => Int = _ + 1

    val randomValue = System.currentTimeMillis().toInt
    val expected = f(randomValue)
    val obtained1 = (f <<< given_Category_Function1.id)(randomValue)
    val obtained2 = (given_Category_Function1.id <<< f)(randomValue)
    val obtained3 = (f >>> given_Category_Function1.id)(randomValue)
    val obtained4 = (given_Category_Function1.id >>> f)(randomValue)

    assertEquals(obtained1, expected)
    assertEquals(obtained2, expected)
    assertEquals(obtained3, expected)
    assertEquals(obtained4, expected)

end CategorySuite
