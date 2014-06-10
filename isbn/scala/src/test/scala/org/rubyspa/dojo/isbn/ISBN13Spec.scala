package org.rubyspa.dojo.isbn

import org.scalatest.{Matchers, FlatSpec}

class ISBN13Spec extends FlatSpec with Matchers {

  "ISBN13" should "produce a check digit for ISBN-13 codes, ignoring spaces and hyphens" in {
    assert(ISBN13("978047005902") == "9")
    assert(ISBN13("978 0 471 48648") == "0")
    assert(ISBN13("978-059680948") == "5")
    assert(ISBN13("978-0-13-149505") == "0")
    assert(ISBN13("978-0-262-13472") == "9")
  }

}
