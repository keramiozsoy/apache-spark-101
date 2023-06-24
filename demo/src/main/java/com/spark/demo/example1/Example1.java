package com.spark.demo.example1;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/example1")
public class Example1 {

    @GetMapping
    String example() {
        final List<Double> inputData = Arrays.asList(1.223, 2.41333, 3.6, 7.5);

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/SparkConf.html
        // local[*] means, use all cores on the machine
        final SparkConf conf = new SparkConf().setAppName("Example Spark App").setMaster("local[*]");

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/api/java/JavaSparkContext.html
        // Context is a connection to spark cluster.
        final JavaSparkContext context = new JavaSparkContext(conf);

        // parallelize() converts pure data as RDD.
        // JavaRDD class is a wrapper which invokes Scala code because Apache Spark is written by Scala Language.
        // At this point, We have not loaded an RDD, we have added to the "execution plan".
        final JavaRDD<Double> parallelize = context.parallelize(inputData);

        System.out.println("At this line, Add breakpoint and look at Spark Web UI > http://192.168.10.158:4040");

        // context closed.
        context.close();

        return "Example 1 is worked.";
    }
}