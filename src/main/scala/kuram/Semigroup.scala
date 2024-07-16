package kuram

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
    * scala> import kuram.Semigroup
    * scala> import kuram.SemigroupInstances.given
    * scala> import kuram.SemigroupSyntax.*
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
  /** Creating instance of [[kuram.Semigroup]] with given T.
    *
    * Example:
    * {{{
    * scala> import kuram.Semigroup
    * scala> import kuram.SemigroupInstances.given
    *
    * scala> Semigroup[Int]
    * val res0: kuram.Semigroup[Int] = kuram.SemigroupInstances$given_Semigroup_Int
    * }}}
    */
  def apply[T](using instance: Semigroup[T]): Semigroup[T] = instance

object SemigroupInstances:
  given Semigroup[Int] with
    def combine(a: Int, b: Int): Int = a + b

  given Semigroup[String] with
    def combine(a: String, b: String): String = a + b

  given [A]: Semigroup[List[A]] with
    def combine(a: List[A], b: List[A]): List[A] = a ++ b

object SemigroupSyntax:
  extension [T](a: T)
    def |+|(b: T)(using semigroup: Semigroup[T]): T = semigroup.combine(a, b)
