package kuram

trait Monoid[T] extends Semigroup[T]:
  def empty: T

object Monoid:
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

