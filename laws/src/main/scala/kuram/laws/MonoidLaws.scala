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

package kuram.laws

import kuram.Monoid
import kuram.syntax.semigroup.*

trait MonoidLaws[T] extends SemigroupLaws[T] {
  given F: Monoid[T]

  /** Left Identity
    * id + a == a
    */
  def leftIdentity(a: T): IsEq[T] = {
    // F.combine(F.empty, a) <-> a
    (F.empty |+| a) <-> a
  }

  /** Right Identity
    * a + id == a
    */
  def rightIdentity(a: T): IsEq[T] = {
    // F.combine(a, F.empty) <-> a
    (a |+| F.empty) <-> a
  }
}

object MonoidLaws {
  def apply[T](using monoid: Monoid[T]): MonoidLaws[T] = new {
    given F: Monoid[T] = monoid
  }
}
