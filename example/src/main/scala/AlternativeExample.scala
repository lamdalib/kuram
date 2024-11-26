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

package example

import kuram.Alternative
import kuram.instances.list.given

object AlternativeExample {
  // A farmer has a wolf, a goat, and a cabbage that he wishes to transport
  // across a river. Unfortunately, his boat can carry only one thing at a
  // time with him. He can’t leave the wolf alone with the goat, or the wolf
  // will eat the goat. He can’t leave the goat alone with the cabbage, or the
  // goat will eat the cabbage. How can he properly transport his belongings
  // to the other side one at a time, without any disasters?
  @main def wolfGoatCabbage: Unit = {
    sealed trait Character
    case object Farmer extends Character
    case object Goat extends Character
    case object Wolf extends Character
    case object Cabbage extends Character

    sealed trait Position
    case object West extends Position
    case object East extends Position

    case class Move(x: Character)

    val possibleMoves = Alternative[List].map(List(Farmer, Wolf, Goat, Cabbage))(Move(_))

    def filterA[F[_], A](fa: F[A])(cond: A => Boolean)(using Alternative[F]): F[A] = {
      var acc = Alternative[F].empty[A]
      Alternative[F].map(fa) { a =>
        if cond(a) then {
          acc = Alternative[F].combineK(acc, Alternative[F].pure(a))
        } else ()
      }

      acc
    }

    def positionOf(p: List[Move], c: Character): Position = {
      val positionFromCount: Int => Position = (n: Int) => {
        if n % 2 == 0 then West else East
      }

      c match {
        case Farmer => positionFromCount(p.size)
        case other  => positionFromCount(filterA(p)(_ == Move(other)).size)
      }
    }

    def moveLegal(p: List[Move], m: Move): Boolean = {
      positionOf(p, Farmer) == positionOf(p, m.x)
    }

    def safePlan(p: List[Move]): Boolean = {
      val posGoat = positionOf(p, Goat)
      val posFarmer = positionOf(p, Farmer)
      val safeGoat = posGoat != positionOf(p, Wolf)
      val safeCabbage = positionOf(p, Cabbage) != posGoat
      (posFarmer == posGoat) || (safeGoat && safeCabbage)
    }

    def isSolution(p: List[Move]): Boolean = {
      val pos = Alternative[List].map2(List(p), possibleMoves) { (p, m) =>
        positionOf(p, m.x)
      }
      filterA(pos)(_ == West).isEmpty
    }

    def makeMove(ps: List[List[Move]]): List[List[Move]] = {
      Alternative[List].map2(ps, possibleMoves) { (p, m) =>
        if !moveLegal(p, m) then Nil
        else if !safePlan(Alternative[List].combineK(List(m), p)) then Nil
        else Alternative[List].combineK(List(m), p)
      }
    }

    def makeNMoves(n: Int): List[List[Move]] = {
      n match {
        case 0          => Nil
        case 1          => makeMove(List(Nil))
        case n if n > 1 => makeMove(makeNMoves(n - 1))
      }
    }

    def solve(n: Int): List[List[Character]] = {
      Alternative[List].map(filterA(makeNMoves(n))(isSolution)) { ms =>
        Alternative[List].map(ms)(_.x)
      }
    }

    val inputs = List(
      (
        7,
        List(
          List(Goat, Farmer, Cabbage, Goat, Wolf, Farmer, Goat),
          List(Goat, Farmer, Wolf, Goat, Cabbage, Farmer, Goat)
        )
      ),
      (
        8,
        List(
          List(Goat, Farmer, Cabbage, Goat, Wolf, Farmer, Goat),
          List(Goat, Farmer, Wolf, Goat, Cabbage, Farmer, Goat),
          List(Goat, Farmer, Cabbage, Goat, Wolf, Farmer, Goat),
          List(Goat, Farmer, Wolf, Goat, Cabbage, Farmer, Goat),
          List(Goat, Farmer, Cabbage, Goat, Wolf, Farmer, Goat),
          List(Goat, Farmer, Wolf, Goat, Cabbage, Farmer, Goat)
        )
      )
    )

    assertAndPrint(inputs, solve)
  }

}
