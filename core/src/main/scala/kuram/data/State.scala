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

package kuram
package data

opaque type State[S, +A] = S => (S, A)
object State {
  def apply[S, A](instance: S => (S, A)): State[S, A] = instance

  def lift[S, A](a: A): State[S, A] = State { s =>
    (s, a)
  }

  extension [S, A](runF: State[S, A]) {
    def run(initialState: S): (S, A) = {
      runF(initialState)
    }

    def flatMap[B](f: A => State[S, B]): State[S, B] = State { s1 =>
      val (s2, a) = run(s1)
      f(a)(s2)
    }

    def map[B](f: A => B): State[S, B] = {
      flatMap(a => State.lift(f(a)))
    }
  }
}
