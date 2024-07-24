package kuram
package semigroup

object SemigroupSyntax:
  /** @see Alias of [[kuram.semigroup.Semigroup.combine]]
    */
  extension [T](a: T)
    def |+|(b: T)(using semigroup: Semigroup[T]): T = semigroup.combine(a, b)
