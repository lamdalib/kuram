package kuram.laws

import kuram.Alternative
import kuram.syntax.alternative.*

trait AlternativeLaws[F[_]] extends ApplicativeLaws[F] {
  given F: Alternative[F]

  /** Left Identity
    * empty <|> x = x
    */
  override def leftIdentity[A](x: F[A]): IsEq[F[A]] = {
    (F.empty <|> x) <-> x
  }

  /** Right Identity
    * x <|> empty = x
    */
  override def rightIdentity[A](x: F[A]): IsEq[F[A]] = {
    (x <|> F.empty) <-> x
  }
}

object AlternativeLaws {
  def apply[F[_]](using alternative: Alternative[F]): AlternativeLaws[F] = new {
    given F: Alternative[F] = alternative
  }
}
