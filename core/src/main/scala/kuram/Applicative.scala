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

  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    ap(pure(f))(fa)

  def map2[A, B, Z](fa: F[A], fb: F[B])(f: (A, B) => Z): F[Z] =
    ap(map(fb)(b => a => f(a, b)))(fa)

  def map3[A, B, C, Z](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => Z): F[Z] =
    ap(
      map2(fb, fc) { (b, c) =>
        f(_, b, c)
      }
    )(fa)

  def map4[A, B, C, D, Z](fa: F[A], fb: F[B], fc: F[C], fd: F[D])(f: (A, B, C, D) => Z): F[Z] =
    map2(product(fa, fb), product(fc, fd)) { case ((a, b), (c, d)) =>
      f(a, b, c, d)
    }

  def map5[A, B, C, D, E, Z](fa: F[A], fb: F[B], fc: F[C], fd: F[D], fe: F[E])(f: (A, B, C, D, E) => Z): F[Z] =
    map3(product(fa, fb), product(fc, fd), fe) { case ((a, b), (c, d), e) =>
      f(a, b, c, d, e)
    }

  def map6[A, B, C, D, E, G, Z](fa: F[A], fb: F[B], fc: F[C], fd: F[D], fe: F[E], fg: F[G])(
    f: (A, B, C, D, E, G) => Z
  ): F[Z] =
    map3(
      product(fa, fb),
      product(fc, fd),
      product(fe, fg)
    ) { case ((a, b), (c, d), (e, g)) => f(a, b, c, d, e, g) }

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
