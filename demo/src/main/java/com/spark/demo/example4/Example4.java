package com.spark.demo.example4;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/example4")
public class Example4 {

    @GetMapping
    String example() {
        final List<String> inputData = Arrays.asList(
                "WARN : Tuesday 4 September 0405",
                "                                ",
                "WARN : Tuesday 4 September 0406",
                "                              ",
                "ERROR : Tuesday 4 September 0408",
                "                               ",
                "FATAL : Wednesday 5 September 1632",
                "                                  ",
                "ERROR : Friday 7 September 1854",
                "                               ",
                "WARN : Saturday 8 September 1942"
        );

        final String path = "src" + File.separator + "example3output.txt";
        Path output = Paths.get(path);
        try {
            Files.write(output, inputData);
            System.out.println(output.toFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/SparkConf.html
        // local[*] means, use all cores on the machine
        final SparkConf conf = new SparkConf()
                .setAppName("Example 3 Spark App")
                .setMaster("local[*]")
                .set("spark.driver.allowMultipleContexts", "true");

        // https://spark.apache.org/docs/latest/api/java/index.html?org/apache/spark/api/java/JavaSparkContext.html
        // Context is a connection to spark cluster.
        final JavaSparkContext context = new JavaSparkContext(conf);

        // Read data from the file.
        final JavaRDD<String> rdd = context.textFile(path);

        System.out.println("=====> At this line, Add breakpoint and look at Spark Web UI > http://0.0.0.0:4040");


        // Replace character
        final JavaRDD<String> replacedSharpRDD = rdd.map(sentence -> sentence.replace(":", "#"));

        // Remove blank lines
        final JavaRDD<String> removedBlankLinesRDD = replacedSharpRDD.filter(sentence -> sentence.trim().length() > 0);

        // It converts sentences to words.
        final JavaRDD<String> wordsRDD = removedBlankLinesRDD.flatMap(sentence -> Arrays.asList(sentence.split(" ")).iterator());

        // There is a map to show, word and same word of count
        final Map<String, Long> stringLongMap = wordsRDD.countByValue();

        stringLongMap.forEach((key, count) -> System.out.println("=====> " + key + " - " + count));


        try {
            // Delete file.
            Files.delete(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // context closed.
        context.close();
// Result
//
// =====> 8-1
// =====> 4-3
// =====> 1632-1
// =====> 5-1
// =====> ERROR-2
// =====> Wednesday-1
// =====> 0408-1
// =====> FATAL-1
// =====> 1942-1
// =====> Saturday-1
// =====> 0405-1
// =====> WARN-3
// =====> Tuesday-3
// =====> Friday-1
// =====> #-6
// =====> 1854-1
// =====> September-6
// =====> 7-1
// =====> 0406-1

        return "Example is worked.";
    }
}