##only 1 sparkcontext can exists in an application
sc.=SparkContext.getOrCreate()
sc
sc.version
help(sc)

 sc.appName
u'PySparkShell'
>>> sc.uiWebUrl
u'http://sandbox-hdp.hortonworks.com:4040'
>>> sc.applicationId
u'application_1587794116660_0004'

#take data from csv
rdd = sc.textFile('/user/maria_dev/ml-100k/u.user')
 for l in rdd.collect():
     print l
	 
rddwords=rdd.map(lambda x:x.split(' '))
rddwords2=rdd.flatMap(lambda line: line.split(' '))
countwords=rddwords.map(lambda x:(x,1))
reducewords=countwords.reduceByKey(lambda x,y:(x+y))
wordsfilter=countwords.filter(f_contains).collect()
wordssorted=reducewords.sortBy(lambda a: a[1],False)
wordssorted2=reducewords.map(lambda (x,y):(y,x))
wordssorted=reducewords.sortBy(lambda (x,y): ~y).collect()

def f_contains(word):
...     return 'student' in word
wordsfilter=countwords.filter(f_contains).collect()



#save to file
rdd1.saveAsTextFile('/user/maria_dev/ml-100k/asd')





rdd1 = sc.parallelize([1,2,3,4])
rdd1.collect()
rdd1.take(10)

-get number of partition of rdd
rdd1.getNumPartitions() 
rdd1.repartition(10)
rdd1.coalesce(10)
rdd1.glom().collect()

rdd1.sum() 
rdd1.max()
rdd1.count()

rdd1 = sc.parallelize(range(1,100))

#access hive
spark.sql('select * from kosmin.ratings').show(10)


#list all rdds names
from pyspark import RDD
>>> for (k,v) in locals().items():
...     if isinstance(v,RDD):
...             print k

#map, reduce,filter, lineage 
rdd1 = sc.parallelize([1,2,3,4])
rdd1=sc.parallelize(['ana','maria','cosmin','ana','laura','laura','ana'])
rdd2=rdd1.map(lambda x:(x,1))
[('ana', 1), ('maria', 1), ('cosmin', 1), ('ana', 1), ('laura', 1), ('laura', 1), ('ana', 1)]
rdd3=rdd2.reduceByKey(lambda x,y:x+y)
[('laura', 2), ('cosmin', 1), ('ana', 3), ('maria', 1)]
rdd4=rdd3.sortByKey().map(lambda (x,y) : (y,x))
print rdd4.toDebugString()
rdd4.prev

rdd2=rdd1.map(lambda x:x+1)
rdd3=rdd1.filter(lambda x:x%2=1)


rdd = sc.textFile('/user/maria_dev/kosmin/asd.txt')
>>> a=sc.parallelize([('ana',1),('maria',2),('laura',3)])
>>> b=sc.parallelize([('ana',4),('miruna',3)])
#union
a.union(b).collect()
#join
a.join(b).collect()
#outer join
 a.fullOuterJoin(b).collect()
 #left join
 a.leftOuterJoin(b).collect()
 #cartesian
 a.cartesian(b).collect()
#group
 d=c.groupByKey()
 
###dataframes
from pyspark.sql import SparkSession
spark=SparkSession.builder.getOrCreate()
#create
df= spark.createDataFrame([(1,'ana'),(2,'laura'),(3,'alexandra')])

from pyspark.sql import Row

df.show()
dir(df)
df.limit(1).show()
df3=df.toDF('id','nume')
df3=rdd.toDF()
>>> df3.show()
df.printSchema()
df.schema
df.dtypes
df.columns
#csv
df = spark.read.format('text').load('/user/maria_dev/kosmin/asd.txt')
df = spark.read.csv('/user/maria_dev/kosmin/occupation-2018-census-csv.csv')
 df = spark.read.csv('/user/maria_dev/kosmin/occupation-2018-census-csv.csv', inferSchema=True)
df = spark.read.csv('/user/maria_dev/kosmin/occupation-2018-census-csv.csv', inferSchema=True,sep=',').printSchema()
df = spark.read.csv('/user/maria_dev/kosmin/occupation-2018-census-csv.csv', inferSchema=True,sep=',',header=True).printSchema()

from pyspark.sql.types import *
ocSchema=StructType([StructField("Code",StringType()),nStructField("Occupation",StringType()),StructField("Employed_census_usually_resident_population_count_aged_15_years_and_over",StringType())])
df = spark.read.schema(ocSchema).csv('/user/maria_dev/kosmin/occupation-2018-census-csv.csv')

##parquet - default file format, binary saved
import pandas as pd
df = pd.read_csv('/home/cosmin/Downloads/joaca/usa-cers-dataset/USA_cars_datasets.csv')
df.to_parquet('/home/cosmin/Downloads/joaca/usa-cers-dataset/output.parquet')
cars_parquet=spark.read.parquet('/home/cosmin/Downloads/joaca/usa-cers-dataset/output.parquet')
cars_parquet.printSchema()
