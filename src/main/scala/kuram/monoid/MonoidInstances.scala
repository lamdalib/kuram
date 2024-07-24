package kuram
package monoid

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
