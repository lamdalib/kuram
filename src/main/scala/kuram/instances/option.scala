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

object option {
  // Applicative
  given optionApplicative: Applicative[Option] with {
    def pure[A](a: => A): Option[A] = Option(a)

    extension [A](fa: Option[A]) {
      def ap[B](ff: Option[A => B]): Option[B] = for {
        f <- ff
        a <- fa
      } yield f(a)
    }
  }

  // Apply
  given optionApply: Apply[Option] with {
    extension [A](fa: Option[A]) {
      def map[B](f: A => B): Option[B] = fa.map(f)

      def ap[B](ff: Option[A => B]): Option[B] = for {
        f <- ff
        a <- fa
      } yield f(a)
    }
  }

  // Functor
  given optionFunctor: Functor[Option] with {
    extension [A](fa: Option[A]) {
      def map[B](f: A => B): Option[B] =
        fa.map(f)
    }
  }

  // Monad
  given optionMonad: Monad[Option] with {
    def pure[A](a: => A): Option[A] = Option(a)

    extension [A](fa: Option[A]) {
      def flatMap[B](f: A => Option[B]): Option[B] =
        fa.flatMap(f)
    }
  }

  // Monoid
  given optionMonoid[A: Monoid]: Monoid[Option[A]] with {
    def empty: Option[A] = None
    def combine(a: Option[A], b: Option[A]): Option[A] = (a, b) match {
      case (Some(a1), Some(b1)) => Some(Monoid[A].combine(a1, b1))
      case (a1, None)           => a1
      case (None, b1)           => b1
    }
  }

  // Semigroup
  given optionSemigroup[A: Semigroup]: Semigroup[Option[A]] with {
    def combine(a: Option[A], b: Option[A]): Option[A] = (a, b) match {
      case (Some(aa), Some(bb)) => Some(Semigroup[A].combine(aa, bb))
      case (aa @ _, None)       => aa
      case (None, bb @ _)       => bb
    }
  }
}