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

import kuram.Applicative
import kuram.syntax.apply.*
import kuram.syntax.applicative.*

trait ApplicativeLaws[F[_]] extends ApplyLaws[F] {
  given F: Applicative[F]

  /** Identity
    * pure(a => a).ap(x) == x
    */
  override def identity[A](x: F[A]): IsEq[F[A]] = {
    F.pure((a: A) => a).ap(x) <-> x
  }

  /** Left Identity
    * pure(()).map2(x)((_, a) => a) == x
    */
  def leftIdentity[A](x: F[A]): IsEq[F[A]] = {
    F.pure(()).map2(x)((_, a) => a) <-> x
  }

  /** Right Identity
    * x.map2(pure(()))((a, _) => a) == x
    */
  def rightIdentity[A](x: F[A]): IsEq[F[A]] = {
    x.map2(F.pure(()))((a, _) => a) <-> x
  }

  /** Homomorphism
    * pure(a).ap(pure(f)) == pure(f(a))
    */
  def homomorphism[A, B](a: A)(f: A => B): IsEq[F[B]] = {
    F.pure(f).ap(F.pure(a)) <-> F.pure(f(a))
  }

  /** Interchange
    * ff.ap(pure(a)) == pure(f => f(a)).ap(ff)
    */
  def interchange[A, B](a: A)(ff: F[A => B]): IsEq[F[B]] = {
    ff.ap(F.pure(a)) <-> F.pure((f: A => B) => f(a)).ap(ff)
  }
}

object ApplicativeLaws {
  def apply[F[_]](using applicative: Applicative[F]): ApplicativeLaws[F] = new {
    given F: Applicative[F] = applicative
  }
}
