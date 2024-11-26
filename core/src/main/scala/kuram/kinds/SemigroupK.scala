/*
 * Copyright (c) 2024 lamdalib
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
package kinds

/** SemigroupK is same as Semigroup that it is not just work with type, but type constructors.
  *
  * They have common combine function for some types but different purposes. For example,
  * for Int type, both results are same. But for Option types,
  * Semigroup combine inner values of the Option whereas SemigroupK pick valid side (i.e, if left side is `None` then will pick right side's result otherwise will pick left side's result).
  *
  * Therefore, you should use Semigroup or SemigroupK based on your problem.
  */
trait SemigroupK[F[_]] {
  def combineK[A](a: F[A], b: F[A]): F[A]
}

object SemigroupK {
  def apply[F[_]](using instance: SemigroupK[F]): SemigroupK[F] = instance
}
