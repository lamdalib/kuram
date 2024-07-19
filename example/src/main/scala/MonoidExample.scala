package kuram.example


@main def monoidExample1: Unit =
  def mergeIntervals(intervals: List[(Int, Int)]): List[(Int, Int)] =
    ???

  def run(): Unit = 
    val obtained1 = mergeIntervals(List((1, 3), (2, 6), (8, 10), (15, 18)))
    val expected1 = List((1, 6), (8, 10), (15, 18))
    assert(obtained1.equals(expected1))
    println("PASS 1")
    
    val obtained2 = mergeIntervals(List((1, 4), (4, 5)))
    val expected2 = List((1, 5))
    assert(obtained2.equals(expected2))
    println("PASS 2")


  run()
