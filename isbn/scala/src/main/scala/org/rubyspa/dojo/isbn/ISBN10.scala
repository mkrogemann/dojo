package org.rubyspa.dojo.isbn

import ISBN._

object ISBN10 {

  def apply(isbn: String): String = {
    def valueAt(c: Char, pos: Int): Int = Integer.parseInt(String.valueOf(c)) * (pos + 1)
    val checksum = normalize(isbn).toSeq.zipWithIndex.map{ case(c, i) => valueAt(c, i) }.fold(0)(_+_)
    checksum % 11 match {
      case 10 => "X"
      case cs => String.valueOf(cs)
    }
  }

}
