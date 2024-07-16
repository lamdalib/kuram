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
trait Category[F[_, _]]:
  def id[A]: F[A, A]

  /** Composing two instances of given type, with this function applied last
    *
    * Example:
    * {{{
    * scala> import kuram.Category
    * scala> import kuram.CategoryInstances.given
    * scala> import kuram.CategorySyntax.*
    *
    * scala> val addOne: Int => Int = _ + 1
    * val addOne: Int => Int = Lambda$XXXX

    * scala> val multiplyByTwo: Int => Int = _ * 2
    * val multiplyByTwo: Int => Int = Lambda$XXXX
    *
    * scala> val addThenMultiply = addOne >>> multiplyByTwo
    * val addThenMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addThenMultiply(1)
    * val res0: Int = 4
    *
    * scala> val addComposeMultiply = addOne <<< multiplyByTwo
    * val addComposeMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addComposeMultiply(1)
    * val res0: Int = 3
    * }}}
    */
  def compose[A, B, C](f: F[B, C], g: F[A, B]): F[A, C]

  /** Composing two instances of given type, with this function applied first
    *
    * Example:
    * {{{
    * scala> import kuram.Category
    * scala> import kuram.CategoryInstances.given
    * scala> import kuram.CategorySyntax.*
    *
    * scala> val addOne: Int => Int = _ + 1
    * val addOne: Int => Int = Lambda$XXXX

    * scala> val multiplyByTwo: Int => Int = _ * 2
    * val multiplyByTwo: Int => Int = Lambda$XXXX
    *
    * scala> val addThenMultiply = addOne >>> multiplyByTwo
    * val addThenMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addThenMultiply(1)
    * val res0: Int = 4
    * }}}
    */
  def andThen[A, B, C](f: F[A, B], g: F[B, C]): F[A, C] = compose(g, f)

object Category:
  def apply[F[_, _]](using instance: Category[F]): Category[F] = instance

object CategoryInstances:
  given Category[Function1] with
    def id[A]: A => A = identity
    def compose[A, B, C](f: B => C, g: A => B): A => C = f compose g

object CategorySyntax:
  extension [F[_, _], A, B, C](f: F[B, C])
    def <<<(g: F[A, B])(using c: Category[F]): F[A, C] = c.compose(f, g)

  extension [F[_, _], A, B, C](f: F[A, B])
    def >>>(g: F[B, C])(using c: Category[F]): F[A, C] = c.andThen(f, g)
