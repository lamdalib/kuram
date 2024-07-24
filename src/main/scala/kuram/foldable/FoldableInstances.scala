package kuram
package foldable

object FoldableInstances:
  given Foldable[List] with
    extension [A](as: List[A]) 
      override def foldRight[B](acc: B)(f: (A, B) => B): B =
        as.foldRight(acc)(f)

      override def foldLeft[B](acc: B)(f: (B, A) => B): B =
        as.foldLeft(acc)(f)

  given Foldable[IndexedSeq] with
    extension [A](as: IndexedSeq[A])
      override def foldRight[B](acc: B)(f: (A, B) => B): B =
        as.foldRight(acc)(f)

      override def foldLeft[B](acc: B)(f: (B, A) => B): B =
        as.foldLeft(acc)(f)     
