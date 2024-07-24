package kuram.semigroup

/** Semigroup
  *
  * Must obey the laws following:
  * 1. a + (b + c) = (a + b) + c
  *
  */
trait Semigroup[T]:
  /** Combining both given same type values.
    * 
    * Example:
    * {{{
    * scala> import kuram.semigroup.Semigroup
    * scala> import kuram.semigroup.instances.given
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

object Semigroup:
  /** Creating instance of [[kuram.semigroup.Semigroup]] with given T.
    *
    * Example:
    * {{{
    * scala> import kuram.semigroup.Semigroup
    * scala> import kuram.semigroup.instances.given
    *
    * scala> Semigroup[Int]
    * val res0: kuram.semigroup.Semigroup[Int] = kuram.semigroup.SemigroupInstances$given_Semigroup_Int
    * }}}
    */
  def apply[T](using instance: Semigroup[T]): Semigroup[T] = instance
