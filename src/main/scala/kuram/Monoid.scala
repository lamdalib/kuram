package kuram

/** Monoid
  *
  * Must obey the laws following:
  * 1. a + (b + c) = (a + b) + c
  * 2. id . f = f
  *    f . id = f
  */
trait Monoid[T] extends Semigroup[T]:
  def empty: T

object Monoid:
  /** Creating instance of [[kuram.Monoid]] with given T.
    *
    * Example:
    * {{{
    * scala> import kuram.Monoid
    * scala> import kuram.MonoidInstances.given
    *
    * scala> Monoid[Int]
    * val res0: kuram.Monoid[Int] = kuram.MonoidInstances$given_Monoid_Int
    * }}}
    */
  def apply[T](using instance: Monoid[T]): Monoid[T] = instance

object MonoidInstances:
  given Monoid[Int] with
    def empty: Int = 0
    def combine(a: Int, b: Int): Int = a + b

  given Monoid[String] with
    def empty: String = ""
    def combine(a: String, b: String): String = a + b

  given [A]: Monoid[List[A]] with
    def empty: List[A] = Nil
    def combine(a: List[A], b: List[A]): List[A] = a ++ b

