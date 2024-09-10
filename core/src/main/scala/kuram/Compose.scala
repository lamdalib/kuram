/*
 * Copyright (c) 2024 kattulib
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package kuram

trait Compose[F[_, _]] {

  /** Composing two instances of given type, with this function applied last
    *
    * Example:
    * {{{
    * scala> import kuram.Compose
    * scala> import kuram.instances.int.given
    * scala> import kuram.syntax.*
    *
    * scala> val addOne: Int => Int = _ + 1
    * val addOne: Int => Int = Lambda$XXXX

    * scala> val multiplyByTwo: Int => Int = _ * 2
    * val multiplyByTwo: Int => Int = Lambda$XXXX
    *
    * scala> val addThenMultiply = addOne >>> multiplyByTwo
    * val addThenMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addThenMultiply(1)
    * val res0: Int = 4
    *
    * scala> val addComposeMultiply = addOne <<< multiplyByTwo
    * val addComposeMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addComposeMultiply(1)
    * val res0: Int = 3
    * }}}
    */
  def compose[A, B, C](f: F[B, C], g: F[A, B]): F[A, C]

  /** Composing two instances of given type, with this function applied first
    *
    * Example:
    * {{{
    * scala> import kuram.Compose
    * scala> import kuram.istances.int.given
    * scala> import kuram.syntax.*
    *
    * scala> val addOne: Int => Int = _ + 1
    * val addOne: Int => Int = Lambda$XXXX

    * scala> val multiplyByTwo: Int => Int = _ * 2
    * val multiplyByTwo: Int => Int = Lambda$XXXX
    *
    * scala> val addThenMultiply = addOne >>> multiplyByTwo
    * val addThenMultiply: Int => Int = scala.Function1$$Lambda$XXXX
    *
    * scala> addThenMultiply(1)
    * val res0: Int = 4
    * }}}
    */
  def andThen[A, B, C](f: F[A, B], g: F[B, C]): F[A, C] = compose(g, f)
}

object ComposeOps {
  extension [F[_, _], A, B, C](f: F[B, C]) {
    def <<<(g: F[A, B])(using c: Compose[F]): F[A, C] = c.compose(f, g)
  }

  extension [F[_, _], A, B, C](f: F[A, B]) {
    def >>>(g: F[B, C])(using c: Compose[F]): F[A, C] = c.andThen(f, g)
  }
}

object Compose {
  def apply[F[_, _]](using instance: Compose[F]): Compose[F] = instance
}
