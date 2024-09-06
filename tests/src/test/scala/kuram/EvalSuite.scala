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

import kuram.data.Eval

class EvalSuite extends munit.FunSuite {
  test("Should satisfy eager and memoized") {
    var a = 0

    val eagerVal = Eval.now {
      a = 1
      1 + 2 * 3
    }

    val evalRes = eagerVal.value

    assertEquals(a, 1)
    assertEquals(evalRes, 7)
  }

  test("Should satisfy lazy and non-memoized") {
    var a = 0

    val lazyVal = Eval.always {
      a = 1
      1 + 2 * 3
    }

    assertEquals(a, 0)

    val evalRes = lazyVal.value
    assertEquals(a, 1)
    assertEquals(evalRes, 7)
  }

  test("Should satisfy lazy and memoized") {
    var a = 0

    val lazyVal = Eval.later {
      a = 1
      1 + 2 * 3
    }

    assertEquals(a, 0)

    val evalRes = lazyVal.value
    assertEquals(a, 1)
    assertEquals(evalRes, 7)
  }
}
