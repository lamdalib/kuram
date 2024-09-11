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
package transformers

opaque type StateT[F[_], S, A] = S => F[(S, A)]
object StateT {
  def apply[F[_], S, A](instance: S => F[(S, A)]): StateT[F, S, A] =
    instance

  def lift[F[_], S, A](a: A)(using F: Applicative[F]): StateT[F, S, A] = StateT { s =>
    F.pure((s, a))
  }

  extension [F[_], S, A](runF: StateT[F, S, A]) {
    def run(initialState: S): F[(S, A)] =
      runF(initialState)

    def map[B](f: A => B)(using F: Monad[F]): StateT[F, S, B] = {
      flatMap(a => lift(f(a)))
    }

    def flatMap[B](f: A => StateT[F, S, B])(using F: Monad[F]): StateT[F, S, B] = StateT { (s0: S) =>
      F.flatMap(run(s0)) { case (s1, a) =>
        f(a).run(s1)
      }
    }
  }

}
