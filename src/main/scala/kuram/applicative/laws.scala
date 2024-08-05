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
package applicative

package object laws {
  trait ApplicativeLaws[F[_]] {
    implicit def F: Applicative[F]

    /** must obey `x.apply(pure(a => a)) == x`  */
    def identity[A](fa: F[A]): Boolean =
      fa.ap(F.pure(a => a)) == fa

    /** must obey `pure(a).ap(pure(f)) == pure(f(a))` */
    def homomorphism[A, B](a: A, f: A => B): Boolean =
      F.pure(a).ap(F.pure(f)) == F.pure(f(a))

    /** must obey `pure(()).map2(x)((_, a) => a) == x`  */
    def leftIdentity[A](fa: F[A]): Boolean =
      F.pure(()).map2(fa)((_, a) => a) == fa

    /** must obey `x.map2(pure(()))((a, _) => a) == x` */
    def rightIdentity[A](fa: F[A]): Boolean =
      fa.map2(F.pure(()))((a, _) => a) == fa

    /** must obey `x.product(y).product(z) == x.product(y.product(z)).map(assoc)` */
    def associativity[A, B, C, Z](fa: F[A], fb: F[B], fc: F[C]): Boolean =
      fa.product(fb).product(fc) == fa.product(fb.product(fc)).map { case (a, (b, c)) =>
        ((a, b), c)
      }

    /** must obey `x.map2(y)((a, b) => ((f(a), g(b)))) == x.map(f).product(y.map(g))` */
    def naturality[A, B, C](fa: F[A], fb: F[B], f: A => B, g: B => C): Boolean =
      fa.map2(fb)((a, b) => ((f(a), g(b)))) == fa.map(f).product(fb.map(g))
  }

  object ApplicativeLaws {
    def apply[F[_]](using applicative: Applicative[F]): ApplicativeLaws[F] = new {
      implicit def F: Applicative[F] = applicative
    }
  }
}
