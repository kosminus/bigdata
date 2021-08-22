package parser

class RawModelApplication[T](parser: Parser[T]) {
  def write(file: String): Unit = {
  System.out.println(s"Got the following data ${parser.parse(file)}")
}
}

