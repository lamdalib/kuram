
package kuram
package semigroup

package object laws {
  trait SemigroupLaws[T] {
    implicit def F: Semigroup[T]
  }

  object SemigroupLaws {
    def apply[T](using semigroup: Semigroup[T]): SemigroupLaws[T] = new {
      implicit def F: Semigroup[T] = semigroup
    }
  }
}
