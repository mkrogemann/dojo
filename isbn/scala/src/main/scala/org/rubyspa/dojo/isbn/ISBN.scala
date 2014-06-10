package org.rubyspa.dojo.isbn

trait ISBN {
  def normalize(isbn: String): String
}

object ISBN extends ISBN {
  override def normalize(isbn: String): String = isbn.replaceAll(" ", "").replaceAll("-", "")
}
