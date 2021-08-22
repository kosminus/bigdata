
import parser.{Parser, RawModelApplication}

object Job extends App {

  val csvCars = Parser("cars.csv")
  val jsonCars = Parser("cars.json")
  val applicationCsv = new RawModelApplication(csvCars)
  val applicationJson = new RawModelApplication(jsonCars)
  System.out.println("Using the csv: ")
  applicationCsv.write("cars.csv")
  System.out.println("Using the json: ")
  applicationJson.write("cars.json")

}
