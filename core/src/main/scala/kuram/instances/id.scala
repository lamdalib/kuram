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

import data.Id

private[instances] trait IdInstances {
  // Functor
  given idFunctor: Functor[Id] with {
    def map[A, B](a: Id[A])(f: A => B): Id[B] = Id(f(a))
  }

  // Applicative
  given idApplicative: Applicative[Id] with {
    def pure[A](a: => A): Id[A] = Id(a)
    def ap[A, B](f: Id[A => B])(a: Id[A]): Id[B] = f(a)
  }

  // Monad
  given idMonad: Monad[Id] with {
    def pure[A](a: => A): Id[A] = Id(a)
    def flatMap[A, B](a: Id[A])(f: A => Id[B]): Id[B] = f(a)
  }

  // Eq
  given idEq[A]: Eq[Id[A]] with {
    def eqv(a: Id[A], b: Id[A]): Boolean = a == b
  }

}
