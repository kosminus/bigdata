import Application.spark
import com.datastax.oss.driver.api.core.cql.Statement
import com.datastax.oss.driver.api.mapper.annotations.Insert
import com.datastax.oss.driver.api.querybuilder.QueryBuilder
import com.datastax.spark.connector.cql.CassandraConnector
import common.Car
import common.CassandraColumns._
import org.apache.spark.SparkConf
import org.apache.spark.sql.{ForeachWriter, Row}

class CassandraSink(sparkConf: SparkConf) extends ForeachWriter[Car] {
  val keyspace = "cars"
  val table = "cars"
  val connector = CassandraConnector(spark.sparkContext.getConf)

  def open(partitionId: Long, version: Long): Boolean = {
    println("open connection")
    true
  }

  def process(car: Car) = {

    connector.withSessionDo { session =>
      session.execute(
        s"""insert into $keyspace.$table("name","acceleration","cylinders","displacement","horsepower","miles_per_gallon","origin","weight_in_lbs","year")
           | values ('${car.Name}',
           |  ${car.Acceleration.getOrElse(0)},
           |  ${car.Cylinders.getOrElse(0)},
           |  ${car.Displacement.getOrElse(0)},
           |  ${car.Horsepower.getOrElse(0)},
           |  ${car.Miles_per_Gallon.getOrElse(0)},
           |  '${car.Origin}',
           |  ${car.Weight_in_lbs.getOrElse(0)},
           |  '${car.Year}')
      """.stripMargin
      )
    }
  }

  def close(errorOrNull: Throwable) = Unit


}