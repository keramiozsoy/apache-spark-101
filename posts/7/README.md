# Post 7

## Apache Spark Java read fake log file format with method of PairRDD example 


Let's say we have got a log file for our app. 
We want to find out how many erros or warnings are present in the file.

The file has a format like below.

```SHELL
WARN : Tuesday 4 September 0405
WARN : Tuesday 4 September 0406
ERROR : Tuesday 4 September 0408
FATAL : Wednesday 5 September 1632
ERROR : Friday 7 September 1854
WARN : Saturday 8 September 1942
```


https://github.com/keramiozsoy/apache-spark-101/blob/main/demo/src/main/java/com/spark/demo/example3/Example3.java


Output

```SHELL
WARN has 3 values
ERROR has 2 values
FATAL has 1 values
```