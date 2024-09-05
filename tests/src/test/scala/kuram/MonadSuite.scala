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

import kuram.Monad
import kuram.instances.all.given
import kuram.laws.MonadLaws

class MonadSuite extends munit.FunSuite {
  test("Should satisfy associativity") {
    val f = (a: Int) => List(a + 1)
    val g = (a: Int) => List(a * 2)
    List(
      List(),
      List(1),
      List(1, 2),
      List(1, 2, 3)
    ).foreach {
      case x => {
        val obtained = MonadLaws(using Monad[List]).associativity(x)(f, g)
        assertEquals(obtained, true)
      }
    }

    val fo = (a: Int) => Some(a + 1)
    val go = (a: Int) => Some(a * 2)
    List(
      None,
      Some(1)
    ).foreach {
      case x => {
        val obtained = MonadLaws(using Monad[Option]).associativity(x)(fo, go)
        assertEquals(obtained, true)
      }
    }
  }

  test("Should satisfy leftIdentity") {
    val f = (a: Int) => List(a + 1)
    val obtained1 = MonadLaws(using Monad[List]).leftIdentity(1, f)
    assertEquals(obtained1, true)

    val fo = (a: Int) => Some(a + 1)
    val obtained2 = MonadLaws(using Monad[Option]).leftIdentity(1, fo)
    assertEquals(obtained2, true)
  }

  test("Should satisfy rightIdentity") {
    List(
      List(),
      List(1),
      List(1, 2),
      List(1, 2, 3)
    ).foreach {
      case x => {
        val obtained = MonadLaws(using Monad[List]).rightIdentity(x)
        assertEquals(obtained, true)
      }
    }
  }
}
