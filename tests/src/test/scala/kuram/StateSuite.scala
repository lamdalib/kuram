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

import kuram.data.State
import kuram.instances.eval.given

class StateSuite extends munit.FunSuite {
  test("Should satisfy run") {
    val (s1, v1) = State
      .apply[Int, Int] { s =>
        (s, s + 1)
      }
      .run(1)
      .value

    assertEquals(s1, 1)
    assertEquals(v1, s1 + 1)
  }

  test("Should satisfy runS") {
    val s1 = State
      .apply[Int, Int] { s =>
        (s, s + 1)
      }
      .runS(1)
      .value

    assertEquals(s1, 1)
  }

  test("Should satisfy runA") {
    val v1 = State
      .apply[Int, Int] { s =>
        (s, s + 1)
      }
      .runA(1)
      .value

    assertEquals(v1, 2)
  }
}
