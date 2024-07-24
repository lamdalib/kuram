package kuram.compose

/** Compose
  *
  * Must obey the laws following:
  * 1. f ∘ (g ∘ h) = (f ∘ g) ∘ h
  *
  * References:
  * - [[https://bartoszmilewski.com/2014/11/04/category-the-essence-of-composition]]
  */
trait Compose[F[_, _]]:
  /** Composing two instances of given type, with this function applied last
    *
    * Example:
    * {{{
    * scala> import kuram.compose.Compose
    * scala> import kuram.compose.instances.given
    * scala> import kuram.compose.syntax.*
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
    * scala> import kuram.compose.Compose
    * scala> import kuram.compose.istances.given
    * scala> import kuram.compose.syntax.*
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

object Compose:
  def apply[F[_, _]](using instance: Compose[F]): Compose[F] = instance
