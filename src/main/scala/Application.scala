import org.apache.spark.sql.SparkSession

object Application {

  def main(args: Array[String]): Unit = {
    val logFile = "readme.md"
    val spark = SparkSession.builder.config("spark.master", "local").appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}