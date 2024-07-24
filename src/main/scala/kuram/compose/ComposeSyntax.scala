package kuram
package compose

object ComposeSyntax:
  extension [F[_, _], A, B, C](f: F[B, C])
    def <<<(g: F[A, B])(using c: Compose[F]): F[A, C] = c.compose(f, g)

  extension [F[_, _], A, B, C](f: F[A, B])
    def >>>(g: F[B, C])(using c: Compose[F]): F[A, C] = c.andThen(f, g)
