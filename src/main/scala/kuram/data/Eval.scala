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
package eval

trait Eval[+A] { self =>
  def value: A

  def map[B](f: A => B): Eval[B] =
    flatMap(a => Now(f(a)))

  def flatMap[B](f: A => Eval[B]): Eval[B] =
    Eval.Defer(() => f(self.value))
}

// call-by-value, eager and memoized
final case class Now[A](a: A) extends Eval[A] {
  def value: A = a
}

// call-by-name, lazy and not-memoized
final case class Always[A](f: () => A) extends Eval[A] {
  def value: A = f()
}

// call-by-need, lazy and memoized
final case class Later[A](f: () => A) extends Eval[A] {
  lazy val value: A = f()
}

object Eval {
  def now[A](value: A): Now[A] =
    Now(value)

  def always[A](f: => A): Always[A] =
    Always(() => f)

  def later[A](f: => A): Later[A] =
    Later(() => f)

  def defer[A](thunk: => Eval[A]): Eval[A] =
    Eval.Defer(() => thunk)

  sealed case class Defer[A](f: () => Eval[A]) extends Eval[A] {
    def value: A = f().value
  }
}
