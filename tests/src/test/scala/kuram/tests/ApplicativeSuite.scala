/*
 * Copyright (c) 2024 lamdalib
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

package kuram.tests

import kuram.laws.ApplicativeLaws
import kuram.data.Id
import kuram.instances.id.given
import kuram.syntax.eq.*

class ApplicativeSuite extends munit.FunSuite {
  private val laws = ApplicativeLaws[Id]

  test("identity") {
    val isEq = laws.identity(Id(1))
    assert(isEq.a === isEq.b)
  }

  test("leftIdentity") {
    val isEq = laws.leftIdentity(Id(1))
    assert(isEq.a === isEq.b)
  }

  test("rightIdentity") {
    val isEq = laws.rightIdentity(Id(1))
    assert(isEq.a === isEq.b)
  }

  test("homomorphism") {
    val f: String => Int = _.length
    val isEq = laws.homomorphism("test")(f)
    assert(isEq.a === isEq.b)
  }

  test("interchange") {
    val f: String => Int = _.length
    val isEq = laws.interchange("test")(Id(f))
    assert(isEq.a === isEq.b)
  }
}
