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

/** Foldable 
  *
  * Type class for foldable data structures.
  */
trait Foldable[F[_]] {
  extension [A](as: F[A]) {

    /** Folds the elements of the structure using a binary operation from right.
      *
      * Example:
      * {{{
      * scala> import kuram.instances.string.given
      * scala> import kuram.syntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @>> ("", f)
      * // or
      * scala> list.foldRight("")(f)
      * val res0: String = abc
      * }}}
      */
    def foldRight[B](acc: B)(f: (A, B) => B): B

    /** Folds the elements of the structure using a binary operation from left.
      *
      * Example:
      * {{{
      * scala> import kuram.instances.string.given
      * scala> import kuram.syntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @<< ("", f)
      * // or
      * scala> list.foldLeft("")(f)
      * val res0: String = abc
      * }}}
      */
    def foldLeft[B](acc: B)(f: (B, A) => B): B =
      foldRight(acc)((b, a) => f(a, b))

    /** Maps each element of the structure to a [[kuram.Monoid]] and combines the results.
      *
      * Example:
      * {{{
      * scala> import kuram.instances.int.given
      * scala> import kuram.syntax.*
      * scala> import kuram.instances.int.given
      *
      * scala> val list = List(1, 2, 3)
      * val list: List[Int] = List(1, 2, 3)
      *
      * scala> list.foldMap(_ * 2)
      * val res0: Int = 12
      * }}}
      */
    def foldMap[B](f: A => B)(using m: Monoid[B]): B =
      foldRight(m.empty)((a, b) => m.combine(f(a), b))
  }
}

object Foldable {

  /** Creating Foldable instance
    * 
    * Example:
    * {{{
    * scala> import kuram.Foldable
    * scala> import kuram.instances.list.given
    *
    * scala> Foldable[List]
    * val res0: kuram.Foldable[List] = kuram.FoldableInstances$given_Foldable_List
    * }}}
    */
  def apply[F[_]](using instance: Foldable[F]): Foldable[F] = instance
}
