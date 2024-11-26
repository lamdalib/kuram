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

import kuram.Monad
import kuram.syntax.flatmap.*

trait MonadLaws[F[_]] extends ApplicativeLaws[F] with FlatMapLaws[F] {
  override given F: Monad[F]

  /** leftIdentity
    * pure(x).flatMap(f) == f(x)
    */
  def monadLeftIdentity[A, B](x: A)(f: A => F[B]): IsEq[F[B]] = {
    F.pure(x).flatMap(f) <-> f(x)
  }

  /** rightIdentity
    * x.flatMap(pure) == x
    */
  def monadRightIdentity[A](x: F[A]): IsEq[F[A]] = {
    x.flatMap(F.pure) <-> x
  }
}

object MonadLaws {
  def apply[F[_]](using monad: Monad[F]): MonadLaws[F] = new {
    given F: Monad[F] = monad
  }
}
