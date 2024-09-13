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

/** In Scala2 `==` and `!=` operators doesn't check types of
  * values. 
  * For example:
  *   scala> "Hello" == 1
  *   val res0: Boolean = false
  *
  * But in Scala3, if types of values are not same then 
  * code won't compile and will throw `Type Error`. 
  * For example:
  *   scala> "hello" == 1
  *   -- [E172] Type Error: ----------------------------------------------------------
  *   1 |"hello" == 1
  *     |^^^^^^^^^^^^
  *     |Values of types String and Int cannot be compared with == or !=
  *   1 error found
  *
  * Actually, we don't need to use [[Eq]] because Scala3 compiler
  * does this purpose for us, but you can use it anyway.
  */
trait Eq[T] {
  def eqv(a: T, b: T): Boolean
  def neqv(a: T, b: T): Boolean = !eqv(a, b)
}

object Eq {
  def apply[T](using instance: Eq[T]): Eq[T] = instance
}
