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

import kuram.Functor

trait FunctorLaws[F[_]] {
  given F: Functor[F]

  /** Identity
    * x.map(a => a) == x
    */
  def functorIdentity[A](x: F[A]): IsEq[F[A]] = {
    F.map(x)(a => a) <-> x
  }

  /** Composition
    * x.map(f).map(g) == x.map(g compose f)
    */
  def functorComposition[A, B, C](x: F[A], f: A => B, g: B => C): IsEq[F[C]] = {
    F.map(F.map(x)(f))(g) <-> F.map(x)(g compose f)
  }
}

object FunctorLaws {
  def apply[F[_]](using functor: Functor[F]): FunctorLaws[F] = new {
    given F: Functor[F] = functor
  }
}
