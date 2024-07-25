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
package semigroup

/** Semigroup
  *
  * Must obey the laws following:
  * 1. a + (b + c) = (a + b) + c
  *
  */
trait Semigroup[T] {
  /** Combining both given same type values.
    * 
    * Example:
    * {{{
    * scala> import kuram.semigroup.Semigroup
    * scala> import kuram.semigroup.instances.int.given
    * scala> import kuram.semigroup.syntax.*
    *
    * scala> Semigroup[Int].combine(1, 2)
    * val res0: Int = 3
    *
    * scala> "foo" |+| "bar"
    * val res1: String = "foobar"
    * }}}
    */
  def combine(a: T, b: T): T
}

object Semigroup {
  /** Creating instance of [[kuram.semigroup.Semigroup]] with given T.
    *
    * Example:
    * {{{
    * scala> import kuram.semigroup.Semigroup
    * scala> import kuram.semigroup.instances.int.given
    *
    * scala> Semigroup[Int]
    * val res0: kuram.semigroup.Semigroup[Int] = kuram.semigroup.SemigroupInstances$given_Semigroup_Int
    * }}}
    */
  def apply[T](using instance: Semigroup[T]): Semigroup[T] = instance
}
