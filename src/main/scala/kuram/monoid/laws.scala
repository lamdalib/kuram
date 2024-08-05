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
package monoid

package object laws {
  trait MonoidLaws[T] {
    implicit def F: Monoid[T]

    /** must obey: `id + x == x + id` */
    def identity(x: T): Boolean =
      (F.combine(F.empty, x) == x) == (F.combine(x, F.empty) == x)

    /** must obey: `a + (b + c) = (a + b) + c` */
    def associativity(a: T, b: T, c: T): Boolean =
      F.combine(a, F.combine(b, c)) == F.combine(F.combine(a, b), c)

    /** must obey: `f(a) + f(b) == f(a + b)` */
    def homomorphism[U](a: T, b: T)(f: T => U)(using U: Monoid[U]): Boolean =
      U.combine(f(a), f(b)) == f(F.combine(a, b))
  }

  object MonoidLaws {
    def apply[T](using monoid: Monoid[T]): MonoidLaws[T] = new {
      implicit def F: Monoid[T] = monoid
    }
  }
}
