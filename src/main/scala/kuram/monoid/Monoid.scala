package kuram
package monoid

import semigroup.Semigroup

/** Monoid
  *
  * Must obey the laws following:
  * 1. a + (b + c) = (a + b) + c
  * 2. id + f = f
  *    f + id = f
  */
trait Monoid[T] extends Semigroup[T]:
  def empty: T

object Monoid:
  /** Creating instance of [[kuram.monoid.Monoid]] with given T.
    *
    * Example:
    * {{{
    * scala> import kuram.monoid.Monoid
    * scala> import kuram.monoid.instances.given
    *
    * scala> Monoid[Int]
    * val res0: kuram.monoid.Monoid[Int] = kuram.monoid.MonoidInstances$given_Monoid_Int
    * }}}
    *
    * References:
    * - [[https://bartoszmilewski.com/2014/12/05/categories-great-and-small/]]
    */
  def apply[T](using instance: Monoid[T]): Monoid[T] = instance
