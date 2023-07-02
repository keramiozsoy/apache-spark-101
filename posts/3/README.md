# Post 3 

## Spark Architecture and RDDs

The Apache Spark architecture consists of a driver/master node and a large number of worker nodes. When the code is deployed, the master node creates an execution plan. Worker nodes are can be physically seperated computers.

When the execution plan is completed, you will be loading large amounts of data. You can use a Hadoop file system, but Whole Hadoop architecture is not required. Simplified, we are using map-reduce algorithm part of Hadoop.

We have to work with data and let’s look at what will happen when data is distributed across the nodes in distributed systems. Nodes can be piece of partitions.

Partitioning is a strategy used to balance data across the nodes. A partition is a block of data, it is not a real node.

The execution plan is running on master. It will be responsible for farming out to worker nodes or sending across the network any function that need to be executed against this data.

These functions reach the worker nodes, then the functions will be executed on the partition. The work is going to use as many parallel threads of execution as it has available.

Another piece of terminology called a task which is when we have a function execute against a partition.

There is another jargon, it is the most important concept in Apache Spark. 

It is RDD which stands for resilient distributed dataset.

RDD is also talking about data, but resilient means that if any of nodes fails, that the data can be recovered and recreated. RDD is an abstraction, it is not really exists.

You can think like big data is on a single array and this is resilient distributed data sets.  If you would like to do something on data there are terms.  Transformations and Actions terms are related to RDD.  

Transformation term, converts RDD to another RDD

( https://spark.apache.org/docs/latest/rdd-programming-guide.html#transformations ) 

Action term produce a result on RDD.

( https://spark.apache.org/docs/latest/rdd-programming-guide.html#actions )

Your java/scala/python code is typed and Apache Spark runs this code as an execution plan rather than immediately running java/scala/python code.

Another jargon is DAG stands for Directed Acyclic Graph. It helps represents the logical execution plan for a given sequence of transformations and actions on RDDs.

