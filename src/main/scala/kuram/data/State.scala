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
package state

import applicative.Applicative
import flatmap.FlatMap
import eval.Eval

final class IndexedStateT[F[_], S1, S2, A](underlying: F[S1 => F[(S2, A)]]) {
  def run(s1: S1)(using F: FlatMap[F]): F[(S2, A)] = {
    F.flatMap(underlying)(f => f(s1))
  }

  def runS(s1: S1)(using F: FlatMap[F]): F[S2] = {
    F.map(run(s1))(_._1)
  }

  def runA(s1: S1)(using F: FlatMap[F]): F[A] = {
    F.map(run(s1))(_._2)
  }
}
object IndexedStateT {
  def apply[F[_], S1, S2, A](f: S1 => F[(S2, A)])(using F: Applicative[F]): IndexedStateT[F, S1, S2, A] = {
    new IndexedStateT(F.pure(f))
  }

  def applyF[F[_], S1, S2, A](f: F[S1 => F[(S2, A)]]): IndexedStateT[F, S1, S2, A] = {
    new IndexedStateT(f)
  }
}

private type IndexedState[S1, S2, A] = IndexedStateT[Eval, S1, S2, A]
private object IndexedState {
  def apply[S1, S2, A](f: S1 => (S2, A)): IndexedState[S1, S2, A] = {
    IndexedStateT.applyF(Eval.now((s: S1) => Eval.now(f(s))))
  }
}

private type StateT[F[_], S, A] = IndexedStateT[F, S, S, A]
private object StateT {
  def apply[F[_], S, A](f: S => F[(S, A)])(using F: Applicative[F]): StateT[F, S, A] = {
    IndexedStateT(f)
  }
}

type State[S, A] = StateT[Eval, S, A]
object State {
  def apply[S, A](f: S => (S, A)): State[S, A] = {
    IndexedStateT.applyF(Eval.now(s => Eval.now(f(s))))
  }
}
