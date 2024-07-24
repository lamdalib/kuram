package example

import kuram.monoid.*
import kuram.monoid.instances.given
import kuram.semigroup.syntax.*

import kuram.foldable.* 
import kuram.foldable.instances.given

object MonoidExample:
  @main def sumOfIntegers: Unit =
    def solve(ints: List[Int]): Int =
      ints.foldRight(Monoid[Int].empty)(_ |+| _)

    val inputs = List(
      (List(1, 2, 3, 4), 10),
      (List(10, 20, 30), 60),
    )

    assertAndPrint(inputs, solve)

  @main def concatStrings: Unit =
    def solve(strings: List[String]): String =
      strings.foldRight(Monoid[String].empty)(_ |+| _)

    val inputs = List(
      (List("hello", " ", "", "world"), "hello world"),
      (List("a", "b", " c"), "ab c"),
    )

    assertAndPrint(inputs, solve)

  @main def unionSets: Unit = 
    def solve(sets: List[Set[Int]]): Set[Int] =
      sets.foldRight(unionSetMonoid.empty)((a, b) => unionSetMonoid.combine(a, b))

    val inputs = List(
      (List(Set(1, 2), Set(2, 3), Set(4)), Set(1, 2, 3, 4)),
      (List(Set(10, 20), Set(30), Set(10, 40)), Set(10, 20, 30, 40)),
    )

    assertAndPrint(inputs, solve)

  @main def intersectSets: Unit = 
    def solve(sets: List[Set[Int]]): Set[Int] =
      sets.foldRight(sets.head)((a, b) => intersectSetMonoid.combine(a, b))

    val inputs = List(
      (List(Set(1, 2, 3), Set(2, 3, 4), Set(2, 5)), Set(2)),
      (List(Set(10, 20), Set(20, 30), Set(20, 40)), Set(20)),
      (List(Set(10, 20), Set()), Set()),
    )

    assertAndPrint(inputs, solve)

  @main def combineMaps: Unit = 
    def solve(maps: List[Map[String, Int]]): Map[String, Int] =
      maps.foldRight(Monoid[Map[String, Int]].empty)(_ |+| _)

    val inputs = List(
      (List(Map("a" -> 1, "b" -> 2), Map("b" -> 3, "c" -> 4)), Map("a" -> 1, "b" -> 5, "c" -> 4)),
      (List(Map("x" -> 10), Map("x" -> 20, "y" -> 30)), Map("x" -> 30, "y" -> 30)),
    )

    assertAndPrint(inputs, solve)

  @main def combineOptions: Unit = 
    def solve(options: List[Option[Int]]): Option[Int] =
      options.foldRight(Monoid[Option[Int]].empty)(_ |+| _)

    val inputs = List(
      (List(Some(1), None, Some(2), Some(3)), Some(6)),
      (List(None, Some(10), None, Some(20)), Some(30)),
      (List(None, None, None), None)
    )

    assertAndPrint(inputs, solve)

  @main def flattenLists: Unit = 
    def solve(lists: List[List[Int]]): List[Int] =
      lists.foldRight(Monoid[List[Int]].empty)(_ |+| _)

    val inputs = List(
      (List(List(1, 2), List(3, 4), List(5)), List(1, 2, 3, 4, 5)),
      (List(List(10), List(), List(20, 30)), List(10, 20, 30)),
    )

    assertAndPrint(inputs, solve)

  @main def isForbidden: Unit =
    def isUpper(ch: Char): Boolean = ch.isUpper
    def isDigit(ch: Char): Boolean = ch.isDigit

    val rules = List(isUpper, isDigit)

    def solve(text: String): Boolean = 
      text.foldRight(false)((ch, acc) => {
        acc || rules.foldMap(f => f(ch))(using conjuctionBooleanMonoid)
      })


    val inputs = List(
      ("1", true),
      ("hello worlD", true),
      ("hello 1 world", true),
      ("hello world", false),
      ("HELLO", true),
      ("HELLO111000", true),
    )

    assertAndPrint(inputs, solve)

  @main def bag: Unit =
    def solve[A](as: IndexedSeq[A]): Map[A, Int] = 
      as.foldMap(a => Map(a -> 1)) 

    val inputs = List(
      (Vector("a", "rose", "is", "a", "rose"), Map("a" -> 2, "rose" -> 2, "is" -> 1)),
      (Vector("a", "a", "a", "a", "a"), Map("a" -> 5)),
      (Vector(1, 3, 3, 2, 1), Map(1 -> 2, 2 -> 1, 3 -> 2)),
    )

    assertAndPrint(inputs, solve)

  @main def countChar: Unit = 
    def solve(as: String): Map[Char, Int] = 
      as.toList.foldMap(a => Map(a -> 1))

    val inputs = List(
      ("hello", Map('h' -> 1, 'e' -> 1, 'l' -> 2, 'o' -> 1)),
    )

    assertAndPrint(inputs, solve)

  @main def countWords: Unit = 
    def solve(sentence: String): Map[String, Int] =
      sentence.split(" ").toList.foldMap(a => Map(a -> 1))

    val inputs = List(
      ("this is a test this is only a test", Map("this" -> 2, "is" -> 2, "a" -> 2, "test" -> 2, "only" -> 1)),
      ("hello world hello", Map("hello" -> 2, "world" -> 1))
    )

    assertAndPrint(inputs, solve)

  @main def summingUpMatrices: Unit = 
    object Matrix:
      type Matrix[A] = List[List[A]]

      def apply[A](instance: List[List[A]]): Matrix[A] = instance

      def zero[A: Monoid](row: Int, col: Int): Matrix[A] =
        Matrix(List.fill[A](row, col)(Monoid[A].empty))

      extension [A: Monoid](m1: Matrix[A])
        def +(m2: Matrix[A]): Matrix[A] = apply:
          for (rowLeft, rowRight) <- m1 zip m2 yield
            for (row, col) <- rowLeft zip rowRight yield
              row |+| col
              

    import Matrix.*

    // create matrix monoid
    def matrixMonoid(row: Int, col: Int): Monoid[Matrix[Int]] = new:
      def empty: Matrix[Int] = Matrix.zero(row, col)
      def combine(a: Matrix[Int], b: Matrix[Int]): Matrix[Int] = a + b

    def solve(matrices: List[Matrix[Int]], row: Int, col: Int): Matrix[Int] =
      matrices.foldMap(identity)(using matrixMonoid(row, col))

    val inputs = List(
      ( 
        // input
        List(
          Matrix(List(List(1, 1), List(1, 1))),
          Matrix(List(List(2, 2), List(3, 3))),
        ),
        // result
        Matrix(List(List(3, 3), List(4, 4))),
      ),

      ( 
        // input
        List(
          Matrix(List(List(1, 1, 2), List(2, 3, 3))),
          Matrix(List(List(2, 2, 3), List(3, 3, 4))),
        ),
        // result
        Matrix(List(List(3, 3, 5), List(5, 6, 7))),
      ),

      ( 
        // input
        List(
          Matrix(List(List(1, 1, 2), List(2, 3, 3))),
          Matrix(List(List(2, 2, 3), List(3, 3, 4))),
          Matrix(List(List(2, 2, 3), List(3, 3, 4))),
        ),
        // result
        Matrix(List(List(5, 5, 8), List(8, 9, 11))),
      ),
    )

    inputs.foreach { case (matrices, expected) =>
      val result = solve(matrices, expected.size, expected.head.size)
      assert(result == expected, s"Expected $expected but got $result")
      println(s"Result: $result")
    }

