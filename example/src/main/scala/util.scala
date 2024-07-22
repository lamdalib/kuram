package kuram.example

def assertAndPrint[T](inputs: List[(List[T], T)], solve: List[T] => T): Unit =
  inputs.foreach: (input, expected) =>
    val obtained = solve(input)
    println(s"Obtained: $obtained, Expected: $expected")
    assert(obtained == expected)
