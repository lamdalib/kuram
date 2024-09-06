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
package laws

trait MonadLaws[F[_]] {
  implicit def M: Monad[F]

  /** must obey `x.flatMap(f).flatMap(g) == x.flatMap(a => f(a).flatMap(g))` */
  def associativity[A, B, C, Z](x: F[A])(f: A => F[B], g: B => F[C]): Boolean =
    x.flatMap(f).flatMap(g) == x.flatMap(a => f(a).flatMap(g))

  /** must obey `pure(x).flatMap(f) == f(x)` */
  def leftIdentity[A, B](x: A, f: A => F[B]): Boolean =
    M.pure(x).flatMap(f) == f(x)

  /** must obey `x.flatMap(pure) == x` */
  def rightIdentity[A, B](x: F[A]): Boolean =
    x.flatMap(M.pure) == x
}

object MonadLaws {
  def apply[F[_]](using monad: Monad[F]): MonadLaws[F] = new {
    implicit def M: Monad[F] = monad
  }
}
