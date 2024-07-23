package kuram

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
    *
    * References:
    * - [[https://bartoszmilewski.com/2014/12/05/categories-great-and-small/]]
    */
  def apply[T](using instance: Monoid[T]): Monoid[T] = instance

object MonoidInstances:
  given Monoid[Int] with
    def empty: Int = 0
    def combine(a: Int, b: Int): Int = a + b

  given Monoid[String] with
    def empty: String = ""
    def combine(a: String, b: String): String = a + b

  given disjunctionBooleanMonoid: Monoid[Boolean] with
    def empty: Boolean = true
    def combine(a: Boolean, b: Boolean): Boolean = a && b

  given conjuctionBooleanMonoid: Monoid[Boolean] with
    def empty: Boolean = false
    def combine(a: Boolean, b: Boolean): Boolean = a || b

  given [A]: Monoid[List[A]] with
    def empty: List[A] = Nil
    def combine(a: List[A], b: List[A]): List[A] = a ++ b

  given unionSetMonoid[A]: Monoid[Set[A]] with
    def empty: Set[A] = Set.empty[A]
    def combine(a: Set[A], b: Set[A]): Set[A] = a | b
  
  given intersectSetMonoid[A]: Monoid[Set[A]] with
    def empty: Set[A] = Set.empty[A]
    def combine(a: Set[A], b: Set[A]): Set[A] = a & b

  given [A: Monoid]: Monoid[Option[A]] with
    def empty: Option[A] = None
    def combine(a: Option[A], b: Option[A]): Option[A] = (a, b) match
      case (Some(a1), Some(b1)) => Some(Monoid[A].combine(a1, b1))
      case (a1, None) => a1
      case (None, b1) => b1

  given [K, V: Monoid]: Monoid[Map[K, V]] with
    def empty: Map[K, V] = Map.empty[K, V]
    def combine(a: Map[K, V], b: Map[K, V]): Map[K, V] =
      (a.keySet ++ b.keySet).foldRight(Map.empty[K, V]): (key, acc) =>
        acc.updated(key, Monoid[V].combine(
                          a.getOrElse(key, Monoid[V].empty),
                          b.getOrElse(key, Monoid[V].empty)))
