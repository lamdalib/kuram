
package kuram
package foldable

package object laws {
  trait FoldableLaws[F[_]] {
    implicit def F: Foldable[F]
  }

  object FoldableLaws {
    def apply[F[_]](using foldable: Foldable[F]): FoldableLaws[F] = new {
      implicit def F: Foldable[F] = foldable
    }
  }
}
