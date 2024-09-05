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

import kuram.Functor
import kuram.instances.all.given
import kuram.laws.FunctorLaws

class FunctorSuite extends munit.FunSuite {
  test("Should satisfy identity law") {
    // TODO: I should use property based testing.
    val inputs = List(
      (List(), true),
      (List(1), true),
      (List(1, 2, 3), true),
      (List('a', 'b', 'c'), true),
      (List("hello", "world"), true),
      (List(List(), List()), true),
      (List(List(1), List(1)), true),
      (List(List(List(1)), List(1)), true)
    )

    inputs.foreach((fa, expected) => {
      val obtained = FunctorLaws(using Functor[List]).identity(fa)
      assertEquals(obtained, expected)
    })
  }

  test("Should satisfy composition law") {
    val f: Int => Double = _ * 2.5
    val g: Double => String = _.toString

    val inputs = List(
      (Some(42), f, g, true),
      (None, f, g, true)
    )

    inputs.foreach((fa, f, g, expected) => {
      val obtained = FunctorLaws(using Functor[Option]).composition(fa, f, g)
      assertEquals(obtained, expected)
    })
  }
}
