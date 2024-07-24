package kuram.category

object CategoryInstances:
  given Category[Function1] with
    def id[A]: A => A = identity
    def compose[A, B, C](f: B => C, g: A => B): A => C = f compose g
