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
package instances

private[instances] trait TupleInstances {
  // Eq
  given tuple1Eq[A]: Eq[Tuple1[A]] with {
    def eqv(a: Tuple1[A], b: Tuple1[A]): Boolean = a == b
  }

  given tuple2Eq[A, B]: Eq[Tuple2[A, B]] with {
    def eqv(a: Tuple2[A, B], b: Tuple2[A, B]): Boolean = a == b
  }

  given tuple3Eq[A, B, C]: Eq[Tuple3[A, B, C]] with {
    def eqv(a: Tuple3[A, B, C], b: Tuple3[A, B, C]): Boolean = a == b
  }

  given tuple4Eq[A, B, C, D]: Eq[Tuple4[A, B, C, D]] with {
    def eqv(a: Tuple4[A, B, C, D], b: Tuple4[A, B, C, D]): Boolean = a == b
  }

  given tuple5Eq[A, B, C, D, E]: Eq[Tuple5[A, B, C, D, E]] with {
    def eqv(a: Tuple5[A, B, C, D, E], b: Tuple5[A, B, C, D, E]): Boolean = a == b
  }

  given tuple6Eq[A, B, C, D, E, F]: Eq[Tuple6[A, B, C, D, E, F]] with {
    def eqv(a: Tuple6[A, B, C, D, E, F], b: Tuple6[A, B, C, D, E, F]): Boolean = a == b
  }

  given tuple7Eq[A, B, C, D, E, F, G]: Eq[Tuple7[A, B, C, D, E, F, G]] with {
    def eqv(a: Tuple7[A, B, C, D, E, F, G], b: Tuple7[A, B, C, D, E, F, G]): Boolean = a == b
  }

  given tuple8Eq[A, B, C, D, E, F, G, H]: Eq[Tuple8[A, B, C, D, E, F, G, H]] with {
    def eqv(a: Tuple8[A, B, C, D, E, F, G, H], b: Tuple8[A, B, C, D, E, F, G, H]): Boolean = a == b
  }

  given tuple9Eq[A, B, C, D, E, F, G, H, I]: Eq[Tuple9[A, B, C, D, E, F, G, H, I]] with {
    def eqv(a: Tuple9[A, B, C, D, E, F, G, H, I], b: Tuple9[A, B, C, D, E, F, G, H, I]): Boolean = a == b
  }

  given tuple10Eq[A, B, C, D, E, F, G, H, I, J]: Eq[Tuple10[A, B, C, D, E, F, G, H, I, J]] with {
    def eqv(a: Tuple10[A, B, C, D, E, F, G, H, I, J], b: Tuple10[A, B, C, D, E, F, G, H, I, J]): Boolean = a == b
  }

  given tuple11Eq[A, B, C, D, E, F, G, H, I, J, K]: Eq[Tuple11[A, B, C, D, E, F, G, H, I, J, K]] with {
    def eqv(a: Tuple11[A, B, C, D, E, F, G, H, I, J, K], b: Tuple11[A, B, C, D, E, F, G, H, I, J, K]): Boolean = a == b
  }

  given tuple12Eq[A, B, C, D, E, F, G, H, I, J, K, L]: Eq[Tuple12[A, B, C, D, E, F, G, H, I, J, K, L]] with {
    def eqv(a: Tuple12[A, B, C, D, E, F, G, H, I, J, K, L], b: Tuple12[A, B, C, D, E, F, G, H, I, J, K, L]): Boolean =
      a == b
  }

  given tuple13Eq[A, B, C, D, E, F, G, H, I, J, K, L, M]: Eq[Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M]] with {
    def eqv(
      a: Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M],
      b: Tuple13[A, B, C, D, E, F, G, H, I, J, K, L, M]
    ): Boolean = a == b
  }

  given tuple14Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N]: Eq[Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]]
  with {
    def eqv(
      a: Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N],
      b: Tuple14[A, B, C, D, E, F, G, H, I, J, K, L, M, N]
    ): Boolean = a == b
  }

  given tuple15Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]: Eq[Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]]
  with {
    def eqv(
      a: Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O],
      b: Tuple15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O]
    ): Boolean = a == b
  }

  given tuple16Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
    : Eq[Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]] with {
    def eqv(
      a: Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P],
      b: Tuple16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P]
    ): Boolean = a == b
  }

  given tuple17Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
    : Eq[Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]] with {
    def eqv(
      a: Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q],
      b: Tuple17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q]
    ): Boolean = a == b
  }

  given tuple18Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
    : Eq[Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]] with {
    def eqv(
      a: Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R],
      b: Tuple18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R]
    ): Boolean = a == b
  }

  given tuple19Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
    : Eq[Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]] with {
    def eqv(
      a: Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S],
      b: Tuple19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S]
    ): Boolean = a == b
  }

  given tuple20Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
    : Eq[Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]] with {
    def eqv(
      a: Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T],
      b: Tuple20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
    ): Boolean = a == b
  }

  given tuple21Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
    : Eq[Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]] with {
    def eqv(
      a: Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U],
      b: Tuple21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U]
    ): Boolean = a == b
  }

  given tuple22Eq[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]
    : Eq[Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]] with {
    def eqv(
      a: Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V],
      b: Tuple22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V]
    ): Boolean = a == b
  }
}
