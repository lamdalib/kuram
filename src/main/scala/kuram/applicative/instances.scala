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
package applicative

package object instances {
  object list {
    given listApplicative: Applicative[List] with {
      def pure[A](a: => A): List[A] = List(a)

      def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] = for {
        f <- ff
        a <- fa
      } yield f(a)
    }
  }

  object map {
    given mapApplicative[K]: Applicative[[V] =>> Map[K, V]] with {
      def pure[A](a: => A): Map[K, A] = Map.empty[K, A].withDefaultValue(a)

      def ap[A, B](ff: Map[K, A => B])(fa: Map[K, A]): Map[K, B] = for {
        (k, f) <- ff
        a <- fa.get(k)
      } yield (k, f(a))
    }
  }

  object option {
    given optionApplicative: Applicative[Option] with {
      def pure[A](a: => A): Option[A] = Option(a)

      def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] = for {
        f <- ff
        a <- fa
      } yield f(a)
    }
  }

  object all {
    export list.given
    export map.given
    export option.given
  }
}
