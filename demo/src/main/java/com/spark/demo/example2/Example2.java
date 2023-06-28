package com.spark.demo.example2;

import com.twitter.chill.Tuple10Serializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scala.Tuple2;
import scala.Tuple22;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/example2")
public class Example2 {

    @GetMapping
    String example() {
        final List<Integer> inputData = Arrays.asList(4, 16, 25, 36);

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/SparkConf.html
        // local[*] means, use all cores on the machine
        final SparkConf conf = new SparkConf()
                .setAppName("Example Spark App")
                .setMaster("local[*]")
                .set("spark.driver.allowMultipleContexts", "true");

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/api/java/JavaSparkContext.html
        // Context is a connection to spark cluster.
        final JavaSparkContext context = new JavaSparkContext(conf);

        // parallelize() converts pure data as RDD.
        // JavaRDD class is a wrapper which invokes Scala code because Apache Spark is written by Scala Language.
        // At this point, We have not loaded an RDD, we have added to the "execution plan".
        final JavaRDD<Integer> rdd = context.parallelize(inputData);

        System.out.println("At this line, Add breakpoint and look at Spark Web UI > http://0.0.0.0:4040");

        final JavaRDD<Double> result = rdd.map(Math::sqrt);

        System.out.println("=== count of elements : ".concat(String.valueOf(result.count())).concat(" === "));


        // While we're working with real data, the code is running on a cluster,
        // it means there is not a terminal that can be show output of the code.
        // The common solution is writing output to file. Instead of writing to a file,
        // we can solve this need by writing to a file system, such as Apache HDFS.
        // ---
        // I was getting java.io.NotSerializableException: java.io.PrintStream
        // I prefer to write
        // - result.collect().forEach(System.out::println);
        // instead of
        // - result.foreach(System.out::println);
        System.out.println("=== elements === ");
        result.collect().forEach(System.out::println);

        // context closed.
        context.close();

        return "Example is worked.";
    }
}