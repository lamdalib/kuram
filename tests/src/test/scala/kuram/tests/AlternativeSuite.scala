package kuram.tests

import kuram.data.Id
import kuram.laws.AlternativeLaws

class AlternativeSuite extends munit.FunSuite {
  private val laws = AlternativeLaws[Id]

  test("leftIdentity") {
    val isEq = laws.leftIdentity(Id(1))
    assert(isEq.a === isEq.b)
  }
}
