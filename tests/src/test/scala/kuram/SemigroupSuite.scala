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

import semigroup.Semigroup
import semigroup.instances.all.given
import semigroup.laws.{SemigroupLaws => laws}

class SemigroupSuite extends munit.FunSuite {
  test("Should satisfy associativity") {
    List(
      (1, 2, 3),
      (100, 200, 300),
      (1, 10, 100)
    ).foreach {
      case (a, b, c) => {
        val obtained = laws(using Semigroup[Int]).associativity(a, b, c)
        assertEquals(obtained, true)
      }
    }

    List(
      ("h", "", "world"),
      ("hello", "beautiful", "world"),
      ("", "", "w")
    ).foreach {
      case (a, b, c) => {
        val obtained = laws(using Semigroup[String]).associativity(a, b, c)
        assertEquals(obtained, true)
      }
    }

    List(
      (List(1, 2), List(), List(3, 4)),
      (List(), List(), List(3, 4, 5)),
      (List(1), List(2), List(3, 4, 5)),
      (List(), List(), List())
    ).foreach {
      case (a, b, c) => {
        val obtained = laws(using Semigroup[List[Int]]).associativity(a, b, c)
        assertEquals(obtained, true)
      }
    }

    List(
      (List("hello"), List(), List("world")),
      (List("h"), List("w"), List("l")),
      (List("hello"), List("beautiful"), List("world")),
      (List(), List(), List())
    ).foreach {
      case (a, b, c) => {
        val obtained = laws(using Semigroup[List[String]]).associativity(a, b, c)
        assertEquals(obtained, true)
      }
    }

  }

  test("Should satisfy homomorphism") {
    List(
      ("", ""),
      ("hello", ""),
      ("", "world"),
      ("hello", "world")
    ).foreach {
      case (s1, s2) => {
        val obtained = laws(using Semigroup[String]).homomorphism(s1, s2)(_.length)
        assertEquals(obtained, true)
      }
    }
  }
}
