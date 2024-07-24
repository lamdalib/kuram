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

import semigroup.syntax.*
import foldable.syntax.*

import monoid.instances.all.given
import foldable.instances.list.given


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
