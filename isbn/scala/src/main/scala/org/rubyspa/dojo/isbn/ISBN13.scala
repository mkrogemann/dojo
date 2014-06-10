package org.rubyspa.dojo.isbn

import ISBN._

object ISBN13 {

  def apply(isbn: String): String = {
    def valueAt(c: Char, pos: Int): Int = Integer.parseInt(String.valueOf(c)) * (if ((pos + 1) % 2 == 0) 3 else 1)
    val checksum = normalize(isbn).toSeq.zipWithIndex.map { case (c, i) => valueAt(c, i)}.fold(0)(_ + _)
    String.valueOf((10 - checksum % 10) % 10)
  }

}
