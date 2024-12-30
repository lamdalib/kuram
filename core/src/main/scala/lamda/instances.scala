package lamda

private[lamda] trait OptionInstances {
  implicit val optionMonad: Monad[Option] = new Monad[Option] {
    def pure[A](a: => A): Option[A] = Option(a)
    def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa
      .flatMap(f)
  }

  implicit def optionMonoid[T: Monoid]: Monoid[Option[T]] =
    new Monoid[Option[T]] {
      def empty: Option[T] = None
      def combine(a: Option[T], b: Option[T]): Option[T] = (a, b) match {
        case (Some(x), Some(y)) => Some(Monoid[T].combine(x, y))
        case (Some(x), None) => Some(x)
        case (None, Some(y)) => Some(y)
        case (None, None) => None
      }
    }
}

private[lamda] trait IntInstances {
  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def empty: Int = 0
    def combine(a: Int, b: Int): Int = a + b
  }
}

private[lamda] trait StringInstances {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def empty: String = ""
    def combine(a: String, b: String): String = a + b
  }
}

private[lamda] trait ListInstances {
  implicit val listMonad: Monad[List] = new Monad[List] {
    def pure[A](a: => A): List[A] = List(a)
    def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)
  }

  implicit def listMonoid[T: Monoid]: Monoid[List[T]] = new Monoid[List[T]] {
    def empty: List[T] = Nil
    def combine(a: List[T], b: List[T]): List[T] = a ++ b
  }
}

private[lamda] trait AllInstances
    extends OptionInstances
    with IntInstances
    with StringInstances
    with ListInstances

package object instances {
  object option extends OptionInstances
  object int extends IntInstances
  object string extends StringInstances
  object list extends ListInstances
  object all extends AllInstances
}
