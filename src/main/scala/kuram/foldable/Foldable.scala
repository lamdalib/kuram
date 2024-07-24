package kuram
package foldable

import monoid.Monoid

/** Foldable 
  *
  * Type class for foldable data structures.
  */
trait Foldable[F[_]]:
  extension [A](as: F[A])
    /** Folds the elements of the structure using a binary operation from right.
      *
      * Example:
      * {{{
      * scala> import kuram.foldable.instances.given
      * scala> import kuram.foldable.syntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @>> ("", (_ + _))
      * // or
      * scala> list.foldRight("")(_ + _)
      * val res0: String = abc
      * }}}
      */
    def foldRight[B](acc: B)(f: (A, B) => B): B

    /** Folds the elements of the structure using a binary operation from left.
      *
      * Example:
      * {{{
      * scala> import kuram.foldable.instances.given
      * scala> import kuram.foldable.syntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @<< ("", (_ + _))
      * // or
      * scala> list.foldLeft("")(_ + _)
      * val res0: String = abc
      * }}}
      */
    def foldLeft[B](acc: B)(f: (B, A) => B): B =
      foldRight(acc)((b, a) => f(a, b))

    /** Maps each element of the structure to a [[kuram.monoid.Monoid]] and combines the results.
      *
      * Example:
      * {{{
      * scala> import kuram.foldable.instances.given
      * scala> import kuram.foldable.syntax.*
      * scala> import kuram.monoid.nstances.given
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

object Foldable:
  /** Creating Foldable instance
    * 
    * Example:
    * {{{
    * scala> import kuram.foldable.Foldable
    * scala> import kuram.foldable.nstances.given
    *
    * scala> Foldable[List]
    * val res0: kuram.foldable.Foldable[List] = kuram.foldable.FoldableInstances$given_Foldable_List
    * }}}
    */
  def apply[F[_]](using instance: Foldable[F]): Foldable[F] = instance
