package kuram

trait Eq[T] {
  def eqv(a: T, b: T): Boolean
  def neqv(a: T, b: T): Boolean = !eqv(a, b)
}

object Eq {
  def apply[T](using instance: Eq[T]): Eq[T] = instance
}
