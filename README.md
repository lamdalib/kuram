# Kuram

<p>
    <img src="/docs/icon.png" width="256" height="256" />
</p>

Kuram is a minimal functional programming library for Scala3
as like [Cats](https://github.com/typelevel/cats) or [Scalaz](https://github.com/scalaz/scalaz).

> [!WARNING]
> I aiming to understand functional programming better. Hence, I'm developing this library. 
Please do not use Kuram in production use Cats or Scalaz instead.

## Getting Kuram
Right now, Kuram is not a published project. If you want to use the library,
you should clone the project and publish it locally as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> publishLocal
...
[info]  published ivy to /home/USER/.ivy2/local/io.github.lamdalib/kuram_3/0.1.0-SNAPSHOT

# on bash
$ sbt publishLocal
...
[info]  published ivy to /home/USER/.ivy2/local/io.github.lamdalib/kuram_3/0.1.0-SNAPSHOT
```

Then you can add the library in the library dependencies as others.
```scala
libraryDependencies += "io.github.lamdalib" %% "kuram" % "0.1.0-SNAPSHOT"
```

## Run Tests
You can run all tests as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> tests/test

# on bash
$ sbt tests/test
```

If you want to run specific test:
```bash
# on interactive sbt shell
sbt:kuram> tests/testOnly *MonoidSuite

# on bash
$ sbt "tests/testOnly *MonoidSuite"
```

## Run Examples
You can run all examples as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> example/run
Multiple main classes detected. Select one to run:
 [1] kuram.example.combineMaps
 [2] kuram.example.combineOptions

# on bash
$ sbt "example/run"
```

If you want to run specific example:
```bash
# on interactive sbt shell
sbt:kuram> example/runMain kuram.example.combineOptions
[info] running kuram.example.combineOptions
Obtained: Some(6), Expected: Some(6)
Obtained: Some(30), Expected: Some(30)
Obtained: None, Expected: None

# on bash
$ sbt "example/runMain kuram.example.combineOptions"
```

## Quick Start
Let's create matrix addition Monoid and use it our matrix operations.

```scala
import kuram.Monoid
// you can access combine alias (|+|) under syntax.
import kuram.syntax.*

// you can access int monoid
import kuram.instances.int.given
// or, if you want to access list, string etc.
// import kuram.instances.string.given
// import kuram.instances.list.given

// or, you can import all givens as follows:
// import kuram.instances.all.given

import kuram.instances.list.given

import Matrix.*

object Matrix {
    type Matrix[A] = List[List[A]]
    
    def apply[A](instance: List[List[A]]): Matrix[A] = instance

    def zero[A: Monoid](row: Int, col: Int): Matrix[A] =
        Matrix(List.fill[A](row, col)(Monoid[A].empty))

    extension [A: Monoid](m1: Matrix[A])
        def +(m2: Matrix[A]): Matrix[A] = apply:
          for (rowLeft, rowRight) <- m1 zip m2 yield
            for (row, col) <- rowLeft zip rowRight yield
              row |+| col
}

def matrixMonoid(row: Int, col: Int): Monoid[Matrix[Int]] = new {
    def empty: Matrix[Int] = Matrix.zero(row, col)
    def combine(a: Matrix[Int], b: Matrix[Int]): Matrix[Int] = a + b
}

@main def start: Unit = 
    val m1 = Matrix(List(List(1, 1), List(7, 3)))
    val m2 = Matrix(List(List(2, 2), List(3, 4)))

    val matrices = List(m1, m2)
    val (row, col) = (2, 2)

    val expected = Matrix(List(List(3, 3), List(10, 7)))
    val obtained = matrices.foldMap(identity)(using matrixMonoid(row, col))

    println(s"Obtained: $obtained\nExpected: $expected")
    assert(obtained == expected, "wrong!")
```

## Type classes
```mermaid
flowchart BT
    %% Typeclasses
        Semigroup["Semigroup (combine)"]
        Monoid["Monoid (empty)"]

        Monad["Monad"]

        Alternative["Alternative"]

        Applicative["Applicative (pure)"]
        FlatMap["FlatMap (flatMap)"]

        Apply["Apply (ap)"]

        Semigroupal["Semigroupal (product)"]
        Functor["Functor (map)"]

        Compose["Compose (compose)"]
        Foldable["Foldable (foldRight)"]
        Traverse["Traverse (traverse)"]
    %% %



    %% Relations
        Monoid --> Semigroup

        Monad --> Applicative & FlatMap --> Apply --> Semigroupal & Functor
        Alternative --> Applicative

        Traverse --> Foldable & Functor

        Compose
    %% %
```

## Data types
```mermaid
flowchart BT
    State --> StateT
    Eval
    Ref
    Id
```

## Effects
```mermaid
flowchart BT
    IO
```

## Type class instances

| Type              | Functor | Apply    | Applicative | Monad      | Monoid     | Semigroup  | Foldable | FlatMap |
| ----------------- | ------- | -------- | ----------- | ---------- | ---------- | ---------- | -------- | ------- |
| List[A]           | ✔       | ✔        | ✔           | ✔          | ✔          | ✔          | ✔        | ✔       |
| Map[K, A]         | ✔       | ✔        | ✗           | ✗          | ✔          | ✗          | ?        | ✔       |
| Option[A]         | ✔       | ✔        | ✔           | ✔          | ✔          | ✔          | ?        | ✔       |
| Either[A]         | ?       | ?        | ?           | ?          | ?          | ?          | ?        | ?       |
| Id[A]             | ✔       | ✔        | ✔           | ✔          | ✗          | ✗          | ?        | ✔       |
| Eval[A]           | ✔       | ✔        | ✔           | ✔          | ?          | ?          | ?        | ✔       |
| State[A]          | ?       | ?        | ?           | ?          | ?          | ?          | ?        | ?       |
| IO[A]             | ?       | ?        | ?           | ✔          | ?          | ?          | ?        | ?       |
| Ref[M, A]         | ?       | ?        | ?           | ?          | ?          | ?          | ?        | ?       |

## Laws and Tests

| Type Class        | Laws  | Tests  |
| ----------------- | ----- | ------ |
| Functor           | ✔     | ✔      |
| Semigroupal       | ✔     | ✔      |
| Apply             | ✔     | ✔      |
| Applicative       | ✔     | ✔      |
| FlatMap           | ✔     | ✔      |
| Monad             | ✔     | ✔      |
| Semigroup         | ✔     | ✔      |
| Monoid            | ✔     | ✔      |
| Alternative       | ✔     | ✔      |
| SemigroupK        | ✔     | ✔      |
| MonoidK           | ✔     | ✔      |
