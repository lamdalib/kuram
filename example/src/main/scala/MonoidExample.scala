package kuram.example

import kuram.*
import SemigroupSyntax.*
import MonoidInstances.given

import FoldableInstances.given
import FoldableSyntax.*

object MonoidExample:
  @main def sumOfIntegers: Unit =
    def solve(ints: List[Int]): Int =
      ints @>> (Monoid[Int].empty, (_ |+| _))

    val inputs = List(
      (List(1, 2, 3, 4), 10),
      (List(10, 20, 30), 60),
    )

    assertAndPrint(inputs, solve)

  @main def concatStrings: Unit =
    def solve(strings: List[String]): String =
      strings @>> (Monoid[String].empty, (_ |+| _))

    val inputs = List(
      (List("hello", " ", "", "world"), "hello world"),
      (List("a", "b", " c"), "ab c"),
    )

    assertAndPrint(inputs, solve)

  @main def unionSets: Unit = 
    def solve(sets: List[Set[Int]]): Set[Int] =
      sets @>> (unionSetMonoid.empty, ((a, b) => unionSetMonoid.combine(a, b)))

    val inputs = List(
      (List(Set(1, 2), Set(2, 3), Set(4)), Set(1, 2, 3, 4)),
      (List(Set(10, 20), Set(30), Set(10, 40)), Set(10, 20, 30, 40)),
    )

    assertAndPrint(inputs, solve)

  @main def intersectSets: Unit = 
    def solve(sets: List[Set[Int]]): Set[Int] =
      // @>> alias for foldRight
      sets @>> (sets.head, ((a, b) => intersectSetMonoid.combine(a, b)))

    val inputs = List(
      (List(Set(1, 2, 3), Set(2, 3, 4), Set(2, 5)), Set(2)),
      (List(Set(10, 20), Set(20, 30), Set(20, 40)), Set(20)),
      (List(Set(10, 20), Set()), Set()),
    )

    assertAndPrint(inputs, solve)

  @main def combineMaps: Unit = 
    def solve(maps: List[Map[String, Int]]): Map[String, Int] =
      maps @>> (Monoid[Map[String, Int]].empty, (_ |+| _))

    val inputs = List(
      (List(Map("a" -> 1, "b" -> 2), Map("b" -> 3, "c" -> 4)), Map("a" -> 1, "b" -> 5, "c" -> 4)),
      (List(Map("x" -> 10), Map("x" -> 20, "y" -> 30)), Map("x" -> 30, "y" -> 30)),
    )

    assertAndPrint(inputs, solve)

  @main def combineOptions: Unit = 
    def solve(options: List[Option[Int]]): Option[Int] =
      options @>> (Monoid[Option[Int]].empty, (_ |+| _))

    val inputs = List(
      (List(Some(1), None, Some(2), Some(3)), Some(6)),
      (List(None, Some(10), None, Some(20)), Some(30)),
      (List(None, None, None), None)
    )

    assertAndPrint(inputs, solve)

  @main def flattenLists: Unit = 
    def solve(lists: List[List[Int]]): List[Int] =
      lists @>> (Monoid[List[Int]].empty, (_ |+| _))

    val inputs = List(
      (List(List(1, 2), List(3, 4), List(5)), List(1, 2, 3, 4, 5)),
      (List(List(10), List(), List(20, 30)), List(10, 20, 30)),
    )

    assertAndPrint(inputs, solve)
