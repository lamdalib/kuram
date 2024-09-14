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

package kuram.laws

import kuram.FlatMap
import kuram.syntax.flatmap.*

trait FlatMapLaws[F[_]] extends ApplyLaws[F] {
  given F: FlatMap[F]

  /** Associativity
    * x.flatMap(f).flatMap(g) <-> x.flatMap(a => f(a).flatMap(g))
    */
  def associativity[A, B, C](x: F[A])(f: A => F[B], g: B => F[C]): IsEq[F[C]] = {
    x.flatMap(f).flatMap(g) <-> x.flatMap(a => f(a).flatMap(g))
  }
}

object FlatMapLaws {
  def apply[F[_]](using apply: FlatMap[F]): FlatMapLaws[F] = new {
    given F: FlatMap[F] = apply
  }
}
