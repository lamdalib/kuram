package kuram.compose

object ComposeInstances:
  given Compose[Function1] with
    def compose[A, B, C](f: B => C, g: A => B): A => C = f compose g
