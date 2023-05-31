# Post 3 

## Spark Architecture and RDDs

Spark architecture includes driver/master node and many worker nodes.

When the code deployed the spark, master node will build execution plan. 

Worker nodes are can be physically seperated computers.


When execution plan is complate, you are going to be loading big data.

You can use a Hadoop file system, but Hadoop isn't redundant.  Here, we are using map-reduce part of Hadoop.

We can read data from something like Amazon S3. 


Lets' look at what will happen when data distributed across the nodes

In distributed systems, nodes can be pieace of partitions.

Patitioning is a strategy used to balance data accross the nodes.



A partition is a block of data, it is not a node.

The execution plan is running is master will be responsible for farming out to worker nodes or sending across the netowork any (java) function that need to be executed against this data.

These functions reach the worker nodes, then the functions will be executed against the partition.

The work is going to use as many parallel threads of execution as  it has available.



Another piece of terminology called a task  which is when we have a function exection against a partition.

A partition is a just the "data" and a "task" is a java code that is executing on that partition.






there is another jargon, it is the most important concept in spark.

RDD stands for resilient distributed dataset. 

it is also talking about data, but resilient means that if any of nodes fails, that the data can be recovered and recreated.
RDD is a abstraction, it is not really exists.

You can think like big data is a single array and this is resilient distributed data sets.

What is really happening in spark java code,
you are building execution plan step by step with java code and spark runs this exectuion plan, not to run directly java code.





Execution plan is commonly called the Dagg, which stands for Directed Acyclic Graph.

This is simple jargon to demonstrate for execution plan.











