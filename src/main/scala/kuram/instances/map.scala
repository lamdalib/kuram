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
package instances

object map {
    // Apply
    given mapApply[K]: Apply[[V] =>> Map[K, V]] with {
        extension [A](fa: Map[K, A]) {
        def map[B](f: A => B): Map[K, B] = fa.map((k, v) => (k, f(v)))

        def ap[B](ff: Map[K, A => B]): Map[K, B] = for {
            (k, f) <- ff
            a <- fa.get(k)
        } yield (k, f(a))
        }
    }

    // Functor
    given mapFunctor[K]: Functor[[V] =>> Map[K, V]] with {
      extension [A](fa: Map[K, A]) {
        def map[B](f: A => B): Map[K, B] =
          fa.map((k, v) => (k, f(v)))
      }
    }

    // Monoid
    given mapMonoid[K, V: Monoid]: Monoid[Map[K, V]] with {
      def empty: Map[K, V] = Map.empty[K, V]
      def combine(a: Map[K, V], b: Map[K, V]): Map[K, V] =
        (a.keySet ++ b.keySet).foldRight(Map.empty[K, V]) { (key, acc) =>
          acc.updated(key, Monoid[V].combine(a.getOrElse(key, Monoid[V].empty), b.getOrElse(key, Monoid[V].empty)))
        }
    }
}