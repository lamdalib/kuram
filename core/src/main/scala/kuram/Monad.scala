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

/**
  *
  * Example:
  * {{{
  * scala> import kuram.Monad
  * scala> import kuram.instances.list.given
  *
  * scala> Monad[List].map(List(1, 2, 3))(_ + 2)
  * res0: List[Int] = List(3, 4, 5)
  * }}}
  */
trait Monad[F[_]] extends Applicative[F] with FlatMap[F] {
  override def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(a => pure(f(a)))
}

object Monad {

  /** Creating instance of [[Monad]] with given F.
    *
    * Example:
    * {{{
    * scala> import kuram.Monad
    * scala> import kuram.instances.list.given
    *
    * scala> Monad[List]
    * }}}
    */
  def apply[F[_]](using instance: Monad[F]): Monad[F] = instance
}
