package kuram

/*
 * Semigroup
 *
 * Must obey the laws following
 * 1. a + (b + c) = (a + b) + c
 *
 */
trait Semigroup[T]:
  def combine(a: T, b: T): T

object Semigroup:
  /* 
   * Creating instance of semigroup from given T
   *
   * Example:
   * {{{
   * scala> import kuram.semigroup._
   *
   * scala> val intSG = Semigroup[Int]
   * scala> intSG.combine(1, 2)
   * res0: 
   * }}}
   * */
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
