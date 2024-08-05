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

import applicative.Applicative
import applicative.instances.all.given
import applicative.laws.{ApplicativeLaws => laws}

class ApplicativeSuite extends munit.FunSuite {
  val listInputs = List(
    List(),
    List(1),
    List(1, 2, 3),
    List('a', 'b', 'c'),
    List("hello", "world"),
    List(List(), List()),
    List(List(1), List(1)),
    List(List(List(1)), List(1))
  )

  val optionInputs = List(
    Option(1),
    Option(None),
    Option((1, 2, 3)),
    Option(List('a', 'b', 'c')),
    Option(Array("hello", "world")),
    Option(List(List(), List())),
    Option(List(List(1), List(1))),
    Option(List(List(List(1)), List((1, 2, 3))))
  )

  test("Should satisfy identity law") {
    listInputs.foreach(fa => {
      val obtained = laws(using Applicative[List]).identity(fa)
      assertEquals(obtained, true)
    })

    optionInputs.foreach(fa => {
      val obtained = laws(using Applicative[Option]).identity(fa)
      assertEquals(obtained, true)
    })
  }

  test("Should satisfy homomorphism law") {
    val f = (x: Int) => x + 1
    val x = 1

    val listObtained = laws(using Applicative[List]).homomorphism(x, f)
    assertEquals(listObtained, true)

    val optionObtained = laws(using Applicative[Option]).homomorphism(x, f)
    assertEquals(optionObtained, true)
  }

  test("Should satisfy left identity law") {
    listInputs.foreach(fa => {
      val obtained = laws(using Applicative[List]).leftIdentity(fa)
      assertEquals(obtained, true)
    })

    optionInputs.foreach(fa => {
      val obtained = laws(using Applicative[Option]).leftIdentity(fa)
      assertEquals(obtained, true)
    })
  }

  test("Should satisfy right identity law") {
    listInputs.foreach(fa => {
      val obtained = laws(using Applicative[List]).rightIdentity(fa)
      assertEquals(obtained, true)
    })

    optionInputs.foreach(fa => {
      val obtained = laws(using Applicative[Option]).rightIdentity(fa)
      assertEquals(obtained, true)
    })
  }

  test("Should satisfy associativity law") {
    List(
      (List(), List(), List()),
      (List(1), List(2), List(3)),
      (List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)),
      (List('a'), List('b', 'c'), List('e'))
    ).foreach((fa, fb, fc) => {
      val obtained = laws(using Applicative[List]).associativity(fa, fb, fc)
      assertEquals(obtained, true)
    })
  }

  test("Should satisfy naturality law") {
    val f = (x: Int) => x + 1
    val g = (x: Int) => x - 1

    List(
      (List(1), List(2))
    ).foreach((fa, fb) => {
      val obtained = laws(using Applicative[List]).naturality(fa, fb, f, g)
      assertEquals(obtained, true)
    })

    List(
      (None, None),
      (Option(1), Option(2)),
      (None, Option(2)),
      (Option(1), None)
    ).foreach((fa, fb) => {
      val obtained = laws(using Applicative[Option]).naturality(fa, fb, f, g)
      assertEquals(obtained, true)
    })
  }
}
