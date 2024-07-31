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
package functor

package object laws {
  trait FunctorLaws[F[_]] {
    implicit def F: Functor[F]

    /** must obey `x.map(a => a) == x`
      */
    def identity[A](fa: F[A]): Boolean =
      F.map(fa)(a => a) == fa

    /** must obey `x.map(f).map(g) == x.map(f andThen g)`
      */
    def composition[A, B, C](fa: F[A], f: A => B, g: B => C): Boolean =
      F.map(F.map(fa)(f))(g) == F.map(fa)(f.andThen(g))
  }

  object FunctorLaws {
    def apply[F[_]](using functor: Functor[F]): FunctorLaws[F] = new {
      implicit def F: Functor[F] = functor
    }
  }
}
