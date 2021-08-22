package parser

import com.github.tototoshi.csv.CSVReader
import model.RawModel

import java.io.InputStreamReader

class CSVParser extends Parser[RawModel] {

  override def parse(file: String): List[RawModel] = {
    CSVReader.open(new
        InputStreamReader(this.getClass.getResourceAsStream(file))).all.map {
      case List(name,
      miles_per_gallon,
      cylinders,
      displacement,
      horsepower,
      weight_in_lbs,
      acceleration,
      year,
      origin) =>
        RawModel(name,
          Option(miles_per_gallon.toDouble),
          Option(cylinders.toLong),
          Option(displacement.toDouble),
          Option(horsepower.toLong),
          Option(weight_in_lbs.toLong),
          Option(acceleration.toDouble),
          year,
          origin)
    }
  }
}



