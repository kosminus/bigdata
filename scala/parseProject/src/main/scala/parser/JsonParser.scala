package parser

import model.RawModel
import org.json4s.{DefaultFormats, StreamInput}
import org.json4s.jackson.JsonMethods

class JsonParser extends Parser[RawModel] {
  implicit val formats = DefaultFormats

  override def parse(file: String): List[RawModel] =
    JsonMethods.parse(StreamInput(this.getClass.getResourceAsStream(file))).extract[List[RawModel]]
}