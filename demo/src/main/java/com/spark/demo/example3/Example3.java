package com.spark.demo.example3;

import com.google.common.collect.Iterables;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/example3")
public class Example3 {

    @GetMapping
    String example() {
        final List<String> inputData = Arrays.asList(
                "WARN : Tuesday 4 September 0405",
                "WARN : Tuesday 4 September 0406",
                "ERROR : Tuesday 4 September 0408",
                "FATAL : Wednesday 5 September 1632",
                "ERROR : Friday 7 September 1854",
                "WARN : Saturday 8 September 1942"
        );

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/SparkConf.html
        // local[*] means, use all cores on the machine
        final SparkConf conf = new SparkConf()
                .setAppName("Example 3 Spark App")
                .setMaster("local[*]")
                .set("spark.driver.allowMultipleContexts", "true");

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/api/java/JavaSparkContext.html
        // Context is a connection to spark cluster.
        final JavaSparkContext context = new JavaSparkContext(conf);

        // parallelize() converts pure data as RDD.
        // JavaRDD class is a wrapper which invokes Scala code because Apache Spark is written by Scala Language.
        // At this point, We have not loaded an RDD, we have added to the "execution plan".
        final JavaRDD<String> rdd = context.parallelize(inputData);

        System.out.println("=====> At this line, Add breakpoint and look at Spark Web UI > http://0.0.0.0:4040");


        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/api/java/JavaPairRDD.html
        // JavaPairRDD seems like java.util.Map. but it is not exactly same.
        // JavaPairRDD uses key-value pair to handle data, but it can be same key multiple time.

        final JavaPairRDD<String, Long> pairRDD = rdd.mapToPair(row -> {
            final String[] columns = row.split(":");
            final String key = columns[0];

            // Tuple is a concept in Scala language. Instead of use the tuple, you have to create custom java class.
            return new Tuple2<>(key, 1L);
        });



        final JavaPairRDD<String, Long> reducedRDD = pairRDD.reduceByKey((value1, value2) -> value1 + value2);

        reducedRDD.foreach(tuple -> System.out.println("=====> " + tuple._1() + " has " + tuple._2() + " values "));
        // context closed.
        context.close();


//      RESULT
//
//        WARN has 3 values
//        ERROR has 2 values
//        FATAL has 1 values

        return "Example is worked.";
    }
}