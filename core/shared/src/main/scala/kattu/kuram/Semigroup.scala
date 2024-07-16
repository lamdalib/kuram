package kuram

trait Semigroup[T]:
  def combine(a: T, b: T): T

object Semigroup:
  def apply[T](using instance: Semigroup[T]): Semigroup[T] = instance

object SemigroupInstances:
  given Semigroup[Int] with
    def combine(a: Int, b: Int): Int = a + b

  given Semigroup[String] with
    def combine(a: String, b: String): String = a + b

  given [A]: Semigroup[Seq[A]] with
    def combine(a: Seq[A], b: Seq[A]): Seq[A] = a ++ b

object SemigroupSyntax:
  extension [T](a: T)
    def |+|(b: T)(using semigroup: Semigroup[T]): T = semigroup.combine(a, b)
