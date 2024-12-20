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

/** Apply
  *
  * Version of [[kuram.Applicative]] without [[kuram.Applicative.pure]] combinator.
  *
  * Example:
  * {{{
  * scala> import kuram.Apply
  * scala> import kuram.instances.list.given
  *
  * scala> val functions: List[Int => Int] = List(_ + 1, _ * 2)
  * scala> val intList = List(1, 2, 3)
  *
  * scala> Apply[List].ap(functions)(intList)
  * res0: List[Int] = List(2, 3, 4, 2, 4, 6)
  *
  *
  * scala> import kuram.instances.map.given
  *
  * scala> val functions: Map[String, Int => Int] = Map("a" -> (_ + 1), "b" -> (_ * 2))
  * scala> val intMap = Map("a" -> 1, "b" -> 2)
  *
  * scala> summon[Apply[[V] =>> Map[String, V]]].ap(functions)(intMap)
  * res1: Map[String, Int] = Map(a -> 2, b -> 4)
  * }}}
  */
trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

  def ap2[A, B, Z](ff: F[(A, B) => Z])(fa: F[A], fb: F[B]): F[Z] =
    ap(ap(map(ff)(_.curried))(fa))(fb)

  override def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] =
    ap(map(fa)(a => (b: B) => (a, b)))(fb)

  def productR[A, B](fa: F[A])(fb: F[B]): F[B] =
    ap(as(fa)((b: B) => b))(fb)

  def productL[A, B](fa: F[A])(fb: F[B]): F[A] =
    ap(map(fa)(a => (b: B) => a))(fb)
}

object Apply {

  /** Creating instance of [[Apply]] with given F.
    *
    * Example:
    * {{{
    * scala> import kuram.Apply
    * scala> import kuram.instances.list.given
    *
    * scala> Apply[List]
    * }}}
    */
  def apply[F[_]](using instance: Apply[F]): Apply[F] = instance
}
