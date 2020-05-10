from pyspark import SparkContext
from pyspark.sql import SQLContext
from pyspark.sql.types import *


if __name__ == "__main__":
    sc = SparkContext(appName="CSV2Parquet")
    sqlContext = SQLContext(sc)

    ocSchema=StructType([StructField("Code",StringType()),StructField("Occupation",StringType()),StructField("Employed_census_usually_resident_population_count_aged_15_years_and_over",StringType())])

    rdd = sc.textFile("/user/maria_dev/kosmin/occupation-2018-census-csv.csv").map(lambda line: line.split(","))
    header=rdd.first()
    rdd2=rdd.filter(lambda row : row !=header)


    df = sqlContext.createDataFrame(rdd2, ocSchema)
    df.write.parquet('/user/maria_dev/kosmin/parquet-occ')
