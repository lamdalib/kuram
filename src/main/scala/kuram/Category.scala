package kuram

/** Category
  *
  * Must obey the laws following:
  * 1. f ∘ (g ∘ h) = (f ∘ g) ∘ h
  * 2. id ∘ f = f
  *    f ∘ id = f
  *
  * References:
  * - [[https://bartoszmilewski.com/2014/11/04/category-the-essence-of-composition]]
  */
trait Category[F[_, _]] extends Compose[F]:
  def id[A]: F[A, A]

object Category:
  def apply[F[_, _]](using instance: Category[F]): Category[F] = instance

object CategoryInstances:
  given Category[Function1] with
    def id[A]: A => A = identity
    def compose[A, B, C](f: B => C, g: A => B): A => C = f compose g
