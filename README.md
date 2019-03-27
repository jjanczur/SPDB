# Chameleon clustering algorithm

## Description of the algorithm

The following figure shows three steps of the algorithm's operation, which are described in detail in Figure 1. The algorithm's operation consists of three phases.

![chameleon1](chameleon1.png)

1. Step one
    * A graph is created containing all points from the test set
    * Leaving the edge according to the KNN algorithm
    * Breakdown into initial clusters by finding coherent components in the graphs - Depth-first search(DFS).

![KNN](knn.png)

2. Step two:
    * Until the initial number of groups has been reached, the vertices of the largest cluster are divided into two parallel parts
    * Choosing the largest cluster
    * Cluster division into two parallel subgraphs depending on the distribution of points

3. Until the specified number k of clusters has been reached at the final input
    * Calculation of binding coefficient metrics (RI and RC) for all cluster pairs
        * EC (Ci) - average weight value (1 / distance) between vertices in the graph
        * RI (Ci, Cj) - coefficient of internal relations in the cluster(Relative Inter-Connectivity)

        ```Java
        RI = bothClustersEC * (firstClusterPointsNr + secondClusterPointsNr) /    (secondClusterPointsNr * firstClusterEC + firstClusterPointsNr * secondClusterEC)
        ```

        * RC (Ci, Cj) - coefficient of connection between two clusters (Relative Closeness)

        ```Java
        RC = bothClustersEC * (firstClusterPointsNr + secondClusterPointsNr) /
            (secondClusterPointsNr * firstClusterEC + firstClusterPointsNr * secondClusterEC)
        ```

    * For each pair of clusters, the product of RI times RC is calculated.
    * The pair with the smallest value of the product RI times RC are joined
    * Adding a new cluster to the list of clusters and deleting old clusters

## Data

A set of cities has been selected with an administrative division into states - https://simplemaps.com/data/us-cities

The data file contains 4 data for each city.
These are the city (city) name, state (state_name), longitude (Ing) and latitude (lat).

The data in the us-cities set is stored in the .csv text format

![Data](data.png)


## Compilation
`
javac Combiner.java
`

## Running

The jar should be run with 4 arguments:

1. File name with input data (including extension)
2. Parameter k (integer)
3. The initial number of clusters (integer)
4. Expected number of clusters at the program output (integer)

**Example:**

```bash
java Combiner myFileName.csv 3 15 3
```

## Example jar for this project 

The data folder in which the .csv files will be stored should be in the same location as the SPDB.jar file <br>

* Location  `/SPDB_jar/SPDB.jar`
 * File with an example data to run the project: `/SPDB_jar/data/utah_ariz_newMexico_colorado.csv`

 ```bash
 java -jar SPDB.jar utah_ariz_newMexico_colorado.csv 4 8 4 
 ```
 
 
