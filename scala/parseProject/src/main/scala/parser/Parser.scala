package parser

import model.RawModel

trait Parser[T] {
  def parse(file:String): List[T]

}

object Parser {
  def apply(filename:String):Parser[RawModel]=
    filename match {
    case f if f.endsWith(".json") => new JsonParser
    case f if f.endsWith(".csv") => new CSVParser
    case f => throw new RuntimeException(s"Unknow format $f")
    }
}


