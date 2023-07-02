# Post 4

## Java local development environment to learn Apache Spark

We will create simple REST web service project which is more simple for examples. The project is built with Spring Boot  (https://spring.io/projects/spring-boot) and add simple java dependencies on the project.

* Open https://start.spring.io

* Project : Maven

* Language : Java

* Spring Boot : 2.2.5.RELEASE

* Group : com.spark

* Artifact : demo

* Name : demo

* Package : com.spark.demo

* Packaging : Jar

* Java : 8

* Add Dependencies : SpringWeb

Create and open project with your favorite IDE.

* IntelliJ Idea

* VSCode

* Eclipse

Open pom.xml file and add additional apache spark dependencies.

```XML
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.10</artifactId>
            <version>2.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-sql_2.10</artifactId>
            <version>2.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-hdfs</artifactId>
            <version>2.2.0</version>
        </dependency>
```

Let’s check last status on terminal.

```SHELL
cd /demo

mvn clean install

mvn spring-boot:run
```

Everything seems ok.