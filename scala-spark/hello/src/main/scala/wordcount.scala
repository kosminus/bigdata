import org.apache.spark._
import org.apache.spark.SparkContext._
object WordCount {
    def main(args: Array[String]) {
      println("Hello World.")
      val conf = new SparkConf().setAppName("wordCount")
      // Create a Scala Spark Context.
      val sc = new SparkContext(conf)
      // Load our input data.
      val input =  sc.textFile("/user/maria_dev/kosmin/occupation.csv")
      // Split up into words.
      val words = input.flatMap(line => line.split(" "))
      // Transform into word and count.
      val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
      // Save the word count back out to a text file, causing evaluation.
      counts.saveAsTextFile("/user/maria_dev/kosmin/output")
    }
}
