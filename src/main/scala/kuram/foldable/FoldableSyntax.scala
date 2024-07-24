package kuram.foldable

object FoldableSyntax:
  extension [F[_]: Foldable, A, B](foldable: F[A])
    /** @see Alias of [[kuram.foldable.Foldable.foldRight]]
      */
    def @>>(acc: B, f: (A, B) => B): B =
      Foldable[F].foldRight(foldable)(acc)(f)

    /** @see Alias of [[kuram.foldable.Foldable.foldLeft]]
      */
    def @<<(acc: B, f: (B, A) => B): B =
      Foldable[F].foldLeft(foldable)(acc)(f)
