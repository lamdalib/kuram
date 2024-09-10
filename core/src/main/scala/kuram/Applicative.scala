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

trait Applicative[F[_]] extends Apply[F] {

  /** Convert any value into Applicative.
    *
    * Example:
    * {{{
    * scala> import kuram.Applicative
    * scala> import kuram.instances.list.given
    *
    * scala> Applicative[List].pure(1)
    * res0: List[Int] = List(1)
    * }}}
    */
  def pure[A](a: => A): F[A]

  extension [A](fa: F[A]) {
    override def map[B](f: A => B): F[B] =
      fa.ap(pure(f))

    def map2[B, Z](fb: F[B])(f: (A, B) => Z): F[Z] =
      fa.ap(
        fb.map(b => a => f(a, b))
      )

    def map3[B, C, Z](fb: F[B], fc: F[C])(f: (A, B, C) => Z): F[Z] =
      fa.ap(
        fb.map2(fc) { (b, c) =>
          f(_, b, c)
        }
      )

    def map4[B, C, D, Z](fb: F[B], fc: F[C], fd: F[D])(f: (A, B, C, D) => Z): F[Z] =
      fa.product(fb).map2(fc.product(fd)) { case ((a, b), (c, d)) =>
        f(a, b, c, d)
      }

    def map5[B, C, D, E, Z](fb: F[B], fc: F[C], fd: F[D], fe: F[E])(f: (A, B, C, D, E) => Z): F[Z] =
      fa.product(fb).map3(fc.product(fd), fe) { case ((a, b), (c, d), e) =>
        f(a, b, c, d, e)
      }

    def map6[B, C, D, E, G, Z](fb: F[B], fc: F[C], fd: F[D], fe: F[E], fg: F[G])(f: (A, B, C, D, E, G) => Z): F[Z] =
      fa.product(fb)
        .map3(
          fc.product(fd),
          fe.product(fg)
        ) { case ((a, b), (c, d), (e, g)) => f(a, b, c, d, e, g) }

  }
}

object Applicative {

  /** Creating instance of [[Applicative]] with given type.
    *
    * Example:
    * {{{
    * scala> import kuram.Applicative
    * scala> import kuram.instances.list.given
    *
    * scala> Applicative[List]
    * res0: kuram.Applicative[List]
    * }}}
    */
  def apply[F[_]](using instance: Applicative[F]): Applicative[F] = instance
}
