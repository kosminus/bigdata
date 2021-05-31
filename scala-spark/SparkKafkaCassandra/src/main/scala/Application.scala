import common.Car
import common.common.carsSchema
import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.dsl.expressions.StringToAttributeConversionHelper
import org.apache.spark.sql.functions.{col, expr, from_json}
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, ForeachWriter, SaveMode, SparkSession, functions}
import org.apache.spark.sql.cassandra._
import common.CassandraColumns

import scala.collection.Map

object Application  {


  val spark = SparkSession.builder()
    .appName("SparkKafkaCassandra")
    .master("yarn")
    //.master("local")
    .config("spark.cassandra.connection.host", "localhost")
    .getOrCreate()



  def main(args: Array[String]): Unit = {
    Utils.setupLogging();
    val df = readFromKafka()
    val schemaDF = readWithSchema(df)
    val countDF= countByState(schemaDF)
    //writeToConsole(countDF)
    val staticCars = loadStaticDF

    val joinCondition =Seq("Origin")

    val joinedCarsDF= joinDF(schemaDF,staticCars,joinCondition)
    //writeToConsole(joinedCarsDF)

    //saveToParquet(schemaDF)
   // saveToFile(schemaDF)

    //val x = getCarName(staticCars).foreach(println)

    //saveToCassandra(schemaDF)
    saveToCassandra2(schemaDF)



  }


  def readFromKafka(): DataFrame = {
     spark.readStream
      .format("kafka")
       .option("kafka.bootstrap.servers", "namenode.enisei:6667")
      //.option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "carsJson")
      .load()
  }

  def readWithSchema(df:DataFrame):DataFrame ={
    df.selectExpr("CAST(value AS STRING)")
      .select(from_json(col("value"), carsSchema).as("car"))
      .selectExpr("car.*")

  }

  def loadStaticDF (): DataFrame = {
    val rawJson = spark.read.json("src/main/resources/cars.json")
    rawJson
  }

  def joinDF(df1:DataFrame, df2:DataFrame,joinCondition:Seq[String]): DataFrame = {
    df1.join(df2,joinCondition,"leftsemi")
  }

  def countByState(df:DataFrame): DataFrame ={
      df.groupBy(col("Origin"))
        .count()
  }




    //val newDF = query.filter(col("Origin") === "USA")



    def writeToConsole(df : DataFrame) {
      df
        .writeStream //default in binary - value data
        .format("console")
        .outputMode("append")
        .start()
        .awaitTermination()
    }

  def getCarName(df: DataFrame): Map[String, Long] = {
    val result = df.rdd
      .map(line => line(5).toString)
      .zipWithIndex()
      .collectAsMap()
      .map(e => (e._1, e._2))

    result
  }

  def saveToParquet (df:DataFrame): Unit = {
    df.writeStream
      .format("parquet")
      .option("path", "src/main/output/parquet-cars")
      .option("checkpointLocation", "src/main/output/checkpoint")
      .outputMode(OutputMode.Append())
      .start()
      .awaitTermination()
  }

  def saveToFile (df:DataFrame): Unit = {
    df.repartition(10)
      .writeStream
      .format("csv")
      .option("path", "src/main/output/csv-cars")
      .option("checkpointLocation", "src/main/output/checkpoint")
      .outputMode(OutputMode.Append())
      .start()
      .awaitTermination()
  }


  def saveWithWatermark (df:DataFrame): Unit = {
    df.repartition(10)
      .writeStream
      .format("csv")
      .option("path", "src/main/output/csv-cars")
      .option("checkpointLocation", "src/main/output/checkpoint")
      .outputMode(OutputMode.Append())
      .start()
      .awaitTermination()
  }

  def saveToCassandra(df:DataFrame) =  {
      df.writeStream
        .format("org.apache.spark.sql.cassandra")
        .option("checkpointLocation", "checkpoint")
        .option("keyspace", "cars")
        .option("table", "cars")
        .outputMode(OutputMode.Update)
        .start()

  }
  import spark.implicits._

  def saveToCassandra2(df:DataFrame) = {
    val carDS =  df.as[Car]

    carDS
      .writeStream
      .foreach(new CassandraSink(spark.sparkContext.getConf))
      .start()
      .awaitTermination()
  }


  }


