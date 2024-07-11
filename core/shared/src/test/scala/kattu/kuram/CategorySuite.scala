package kuram

import CategoryInstances.*

class CategorySuite extends munit.FunSuite:

  // id . f == f
  // f . id == f
  test("identity law"):
    val f: Int => String = (i: Int) => (i * 2).toString
    val g: String => Boolean = (s: String) => s.length > 2

    val expected1 = f(5)
    val obtained1 = FunctionCategory.compose(
      FunctionCategory.id[String], f
    )(5)

    val expected2 = g("test")
    val obtained2 = FunctionCategory.compose(
      FunctionCategory.id[Boolean], g
    )("test")

    assertEquals(obtained1, expected1)
    assertEquals(obtained2, expected2)

  // h == g . f
  test("composition law"):
    val f: Int => String = (i: Int) => (i * 2).toString
    val g: String => Boolean = (s: String) => s.length > 2
    val h: Int => Boolean = FunctionCategory.compose(g, f)

    val expected = g(f(5))
    val obtained = h(5)

    assertEquals(obtained, expected)

  // h . (g . f) == (h . g) . f
  test("associativity of composition"):
    val f: Int => String = (i: Int) => (i * 2).toString
    val g: String => Boolean = (s: String) => s.length > 2
    val h: Boolean => Int = (b: Boolean) => if b then 0 else 1

    val comp1 = FunctionCategory.compose(h, FunctionCategory.compose(g, f))
    val comp2 = FunctionCategory.compose(FunctionCategory.compose(h, g), f)

    assertEquals(comp1(5), comp2(5)) 
    
end CategorySuite
