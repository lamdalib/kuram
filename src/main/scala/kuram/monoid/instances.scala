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
package monoid

package object instances:
  given Monoid[Int] with
    def empty: Int = 0
    def combine(a: Int, b: Int): Int = a + b

  given Monoid[String] with
    def empty: String = ""
    def combine(a: String, b: String): String = a + b

  given disjunctionBooleanMonoid: Monoid[Boolean] with
    def empty: Boolean = true
    def combine(a: Boolean, b: Boolean): Boolean = a && b

  given conjuctionBooleanMonoid: Monoid[Boolean] with
    def empty: Boolean = false
    def combine(a: Boolean, b: Boolean): Boolean = a || b

  given [A]: Monoid[List[A]] with
    def empty: List[A] = Nil
    def combine(a: List[A], b: List[A]): List[A] = a ++ b

  given unionSetMonoid[A]: Monoid[Set[A]] with
    def empty: Set[A] = Set.empty[A]
    def combine(a: Set[A], b: Set[A]): Set[A] = a | b
  
  given intersectSetMonoid[A]: Monoid[Set[A]] with
    def empty: Set[A] = Set.empty[A]
    def combine(a: Set[A], b: Set[A]): Set[A] = a & b

  given [A: Monoid]: Monoid[Option[A]] with
    def empty: Option[A] = None
    def combine(a: Option[A], b: Option[A]): Option[A] = (a, b) match
      case (Some(a1), Some(b1)) => Some(Monoid[A].combine(a1, b1))
      case (a1, None) => a1
      case (None, b1) => b1

  given [K, V: Monoid]: Monoid[Map[K, V]] with
    def empty: Map[K, V] = Map.empty[K, V]
    def combine(a: Map[K, V], b: Map[K, V]): Map[K, V] =
      (a.keySet ++ b.keySet).foldRight(Map.empty[K, V]): (key, acc) =>
        acc.updated(key, Monoid[V].combine(
                          a.getOrElse(key, Monoid[V].empty),
                          b.getOrElse(key, Monoid[V].empty)))
