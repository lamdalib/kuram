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
package functor

/** Functor
  *
  * Must obey the laws following
  * 1. Identity: x.map(a => a) == x
  * 2. Associativity: x.map(f).map(g) == x.map(a => f(a).map(g))
  */
trait Functor[F[_]] {
  extension [A](fa: F[A]) {

    /** Mapping given function over instance.
      *
      * Example:
      * {{{
      * scala> import kuram.functor.instances.list.given
      * scala> import kuram.functor.syntax.*
      *
      * scala> val list = List(1, 2, 3)
      * val list: List[Int] = List(1, 2, 3)
      *
      * scala> val f: (Int) => Int = _ * 2
      * val f: (Int) => Int = Lambda$XXXX
      *
      * scala> list |> f
      * // or
      * scala> list.map(f)
      * val res0: List[Int] = List(2, 4, 6)
      * }}}
      */
    def map[B](f: A => B): F[B]

    /** @see Alias of [[map]]. 
      * Sometimes we can't use [[map]] because the type 
      * already had a built-in .map combinator.
      */
    final def fmap[B](f: A => B): F[B] = fa.map(f)
  }
}

object Functor {

  /** Creating instance of [[Functor]] for F.
    *
    * Example:
    * {{{
    * scala> import kuram.functor.Functor
    * scala> import kuram.functor.instances.list.given
    *
    * scala> Functor[List]
    * val res0: kuram.functor.Functor[List] = kuram.functor.FunctorInstances$given_Functor_List
    * }}}
    */
  def apply[F[_]](using instance: Functor[F]): Functor[F] = instance
}
