package kuram
package syntax

private[syntax] trait EqSyntax {
  extension [T](a: T)(using Eq[T]) {
    def ===(b: T): Boolean = Eq[T].eqv(a, b)
    def =!=(b: T): Boolean = Eq[T].neqv(a, b)
    def eqv(b: T): Boolean = Eq[T].eqv(a, b)
    def neqv(b: T): Boolean = Eq[T].neqv(a, b)
  }
}
