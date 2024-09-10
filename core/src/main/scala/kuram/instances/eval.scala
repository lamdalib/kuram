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

import data.Eval

object eval {

  // Functor
  given evalFunctor: Functor[Eval] with {
    extension [A](fa: Eval[A]) {
      def map[B](f: A => B): Eval[B] =
        fa.map(f)
    }
  }

  // Applicative
  given evalApplicative: Applicative[Eval] with {
    def pure[A](a: => A): Eval[A] = Eval.now(a)

    extension [A](fa: Eval[A]) {
      def ap[B](ff: Eval[A => B]): Eval[B] = for {
        a <- fa
        f <- ff
      } yield f(a)
    }
  }

  // Monad
  given evalMonad: Monad[Eval] with {
    def pure[A](a: => A): Eval[A] = Eval.now(a)

    extension [A](fa: Eval[A]) {
      def flatMap[B](f: A => Eval[B]): Eval[B] =
        fa.flatMap(f)
    }
  }
}
