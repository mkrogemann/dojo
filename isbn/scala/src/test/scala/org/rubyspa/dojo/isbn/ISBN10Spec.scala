package org.rubyspa.dojo.isbn

import org.scalatest.{Matchers, FlatSpec}

class ISBN10Spec extends FlatSpec with Matchers {

  "ISBN10" should "produce a check digit for ISBN-10 codes, ignoring spaces and hyphens" in {
    assert(ISBN10("047195869") == "7")
    assert(ISBN10("0 471 60695") == "2")
    assert(ISBN10("0-470-84525") == "2")
    assert(ISBN10("0-321-14653") == "0")
    assert(ISBN10("067973225") == "X")
  }

}
