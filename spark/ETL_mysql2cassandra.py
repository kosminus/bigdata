import os
from pyspark.sql import SQLContext
from pyspark import SparkContext

os.environ['PYSPARK_SUBMIT_ARGS'] = '--jars /usr/share/java/mysql-connector-java-8.0.20.jar  --packages com.datastax.spark:spark-cassandra-connector_2.12:2.5.0    --conf spark.cassandra.connection.host=127.0.0.1 pyspark-shell'



sc=SparkContext.getOrCreate()
sqlContext = SQLContext(sc)

def read_mysql():
    #Mysql DB Info
    hostname = "localhost" 
    dbname = "kosmin"
    jdbcPort = 3306
    username = "kosmin"
    password = ""
    
    jdbc_url = "jdbc:mysql://{0}:{1}/{2}?user={3}&password={4}&serverTimezone=UTC".format(hostname,jdbcPort, dbname,username,password)
    
    query = "(select * from t_crimes) t_crimes"
    #read into Dataframe
    df1 = sqlContext.read.format('jdbc').options(driver = 'com.mysql.jdbc.Driver',url=jdbc_url, dbtable=query ).load()
    df1.show()
    return df1

def write_cassandra(df1):
     # Write it into Cassandra
    df1.write\
        .format("org.apache.spark.sql.cassandra")\
        .mode('append')\
        .options(table="crimes", keyspace="crimes")\
        .save()
    
if __name__ == "__main__":
       df= read_mysql()
       write_cassandra(df)
