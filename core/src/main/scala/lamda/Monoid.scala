package lamda

trait Monoid[T] extends Semigroup[T] {
  /**
    * Example:
    * {{{
    * scala> import lamda.Monoid
    * scala> import lamda.syntax.monoid._
    * scala> import lamda.instances.option._
    * scala> import lamda.instances.int._ 
    * scala> import lamda.instances.string._ 
    *
    * scala> Monoid[Option[Int]].empty
    * res0: Option[Int] = None
    *
    * scala> Monoid[String].empty
    * res0: Int = ""
    *
    * scala> Monoid[Int].empty
    * res0: Int = 0
    * }}}
    */
  def empty: T
}

object Monoid {
  def apply[T](implicit instance: Monoid[T]): Monoid[T] = instance
}
