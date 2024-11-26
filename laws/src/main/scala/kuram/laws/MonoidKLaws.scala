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

import kuram.kinds.MonoidK

trait MonoidKLaws[F[_]] extends SemigroupKLaws[F] {
  override given F: MonoidK[F]

  /** left identity
    * empty <+> a = a
    */
  def monoidKLeftIdentity[A](x: F[A]): IsEq[F[A]] = {
    F.combineK(F.empty, x) <-> x
  }

  /** right identity
    * a <+> empty = a
    */
  def monoidKRightIdentity[A](x: F[A]): IsEq[F[A]] = {
    F.combineK(x, F.empty) <-> x
  }

}

object MonoidKLaws {
  def apply[F[_]](using monoidk: MonoidK[F]): MonoidKLaws[F] = new {
    given F: MonoidK[F] = monoidk
  }
}
