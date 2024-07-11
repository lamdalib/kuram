package kuram

/* 
 * Category must be obey following laws:
 * 1. id . f == f 
 *    f . id == f
 * 2. h = g . f
 * 3. h . (g . f) == (h . g) . f
 *
 * Notes:
 * =>: is a generic type like F in Monad[F[_]]
 */
trait Category[=>:[_, _]]: 
  self =>
  def id[A]: A =>: A
  def compose[A, B, C](f: B =>: C, g: A =>: B): A =>: C

object CategoryInstances:
  object FunctionCategory extends Category[Function1]:
    def id[A]: A => A = identity
    def compose[A, B, C](f: B => C, g: A => B): A => C = f compose g
