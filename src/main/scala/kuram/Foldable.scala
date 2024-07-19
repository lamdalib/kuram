package kuram

/** Foldable 
  *
  * Type class for foldable data structures.
  */
trait Foldable[F[_]]:
  extension [A](as: F[A])
    /** Folds the elements of the structure using a binary operation from right.
      *
      * Example:
      * {{{
      * scala> import kuram.FoldableInstances.given
      * scala> import kuram.FoldableSyntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @>> ("", (_ + _))
      * val res0: String = abc
      * }}}
      */
    def foldRight[B](acc: B)(f: (A, B) => B): B

    /** Folds the elements of the structure using a binary operation from left.
      *
      * Example:
      * {{{
      * scala> import kuram.FoldableInstances.given
      * scala> import kuram.FoldableSyntax.*
      *
      * scala> val list = List("a", "b", "c")
      * val list: List[String] = List("a", "b", "c")
      *
      * scala> val f: (String, String) => String = _ + _
      * val f: (String, String) => String = Lambda$XXXX
      *
      * scala> list @<< ("", (_ + _))
      * val res0: String = abc
      * }}}
      */
    def foldLeft[B](acc: B)(f: (B, A) => B): B =
      foldRight(acc)((b, a) => f(a, b))

    /** Maps each element of the structure to a [[kuram.Monoid]] and combines the results.
      *
      * Example:
      * {{{
      * scala> import kuram.FoldableInstances.given
      * scala> import kuram.FoldableSyntax.*
      * scala> import kuram.MonoidInstances.given
      *
      * scala> val list = List(1, 2, 3)
      * val list: List[Int] = List(1, 2, 3)
      *
      * scala> list @@ (_ * 2)
      * val res0: Int = 12
      * }}}
      */
    def foldMap[B](f: A => B)(using m: Monoid[B]): B = 
      foldRight(m.empty)((a, b) => m.combine(f(a), b))

object Foldable:
  /** Creating Foldable instance
    * 
    * Example:
    * {{{
    * scala> import kuram.Foldable
    * scala> import kuram.FoldableInstances.given
    *
    * scala> Foldable[List]
    * val res0: kuram.Foldable[List] = kuram.FoldableInstances$given_Foldable_List
    * }}}
    */
  def apply[F[_]](using instance: Foldable[F]): Foldable[F] = instance

object FoldableInstances:
  given Foldable[List] with
    extension [A](as: List[A]) 
      override def foldRight[B](acc: B)(f: (A, B) => B): B =
        foldRight(acc)(f)

      override def foldLeft[B](acc: B)(f: (B, A) => B): B =
        foldLeft(acc)(f)


object FoldableSyntax:
  extension [F[_]: Foldable, A](foldable: F[A])
    /**
      * @see Alias for [[kuram.Foldable.foldRight]]
      */
    def @>>[B](acc: B, f: (A, B) => B): B =
      Foldable[F].foldRight(foldable)(acc)(f)

    /** 
      * @see Alias for [[kuram.Foldable.foldLeft]]
      */
    def @<<[B](acc: B, f: (B, A) => B): B =
      Foldable[F].foldLeft(foldable)(acc)(f)

    /** 
      * @see Alias for [[kuram.Foldable.foldMap]]
      */
    def @@[B](f: A => B)(using m: Monoid[B]): B =
      Foldable[F].foldMap(foldable)(f)
