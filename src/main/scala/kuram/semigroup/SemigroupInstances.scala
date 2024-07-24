package kuram.semigroup

object SemigroupInstances:
  given Semigroup[Int] with
    def combine(a: Int, b: Int): Int = a + b

  given Semigroup[String] with
    def combine(a: String, b: String): String = a + b

  given [A]: Semigroup[List[A]] with
    def combine(a: List[A], b: List[A]): List[A] = a ++ b

