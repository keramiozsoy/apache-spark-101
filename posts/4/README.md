# Post 4

## Local development environment to learn Apache Spark


We will create simple REST web service project which is more simple for examples.


We will use Spring Boot framework and add simple java dependecies on project.


Open https://start.spring.io

- Project 		: Maven
- Language		: Java
- Spring Boot	: 2.2.5.RELEASE
- Group 		: com.spark
- Artifact 		: demo
- Name			: demo
- Package		: com.spark.demo
- Packaging		: Jar
- Java			: 8
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