package kuram.example

def assertAndPrint[A, B](inputs: List[(A, B)], solve: A => B): Unit =
  inputs.foreach: (input, expected) =>
    val obtained = solve(input)
    println(s"Obtained: $obtained, Expected: $expected")
    assert(obtained == expected)
