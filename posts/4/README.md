# Post 4

## Prepare local development environment to play Apache Spark


We will create simple REST web service project which is more simple for examples.


We will use Spring Boot framework and add simple java dependecies on project.


Open https://start.spring.io

- Project 		: Maven
- Language		: Java
- Spring Boot	: 3.1.0
- Group 		: com.spark
- Artifact 		: demo
- Name			: demo
- Package		: com.spark.demo
- Packaging		: Jar
- Java			: 17
- Add Dependencies : SpringWeb 


Click Generate Button and open project with your favorite IDE.(Intellij, Eclipse etc.)


### Run Project

you can build and run on terminal 

```SHELL
cd /demo

mvn clean install

mvn spring-boot:run
```

Open another terminal and check result with below command

```SHELL
curl http://localhost:8080
```


### Adds apache spark specific dependencies

Look at the pom.xml file what we added as dependencies.

```XML
<!-- https://mvnrepository.com/artifact/org.apache.spark/spark-core -->
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-core_2.13</artifactId>
    <version>3.3.2</version>
</dependency>



<!-- https://mvnrepository.com/artifact/org.apache.spark/spark-sql -->
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-sql_2.13</artifactId>
    <version>3.3.2</version>
    <scope>provided</scope>
</dependency>


<!-- https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-hdfs -->
<dependency>
    <groupId>org.apache.hadoop</groupId>
    <artifactId>hadoop-hdfs</artifactId>
    <version>3.3.2</version>
    <scope>test</scope>
</dependency>
```