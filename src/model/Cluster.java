package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cluster {
    private String name; // Cluster name
    private List<Point> points; // List clustered points
    private Double[][] graph; // Complete graph of clustered points
    private double EC; // Internal inter - connectivity results
    private Results results; // Clustering results

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;

        Map<String, Long> originalClusterNamesFrequencies = points.stream()
                .map(Point::getOriginalCluster)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Name of the cluster is the most common original cluster name form list of clustered points
        this.name = originalClusterNamesFrequencies.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Double[][] getGraph() {
        return graph;
    }

    public void setGraph(Double[][] graph) {
        this.graph = graph;

        // Compute EC results - mean weight between vertexes
        double tempEC = 0.0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i < j) {
                    tempEC += graph[i][j];
                }
            }
        }
        this.EC = tempEC / points.size();
    }


    public double getEC() {
        return EC;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public void setEC(double EC) {
        this.EC = EC;
    }

    public String printMetrics() {
        return "Cluster: " + name + ", " + results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cluster cluster = (Cluster) o;

        if (Double.compare(cluster.EC, EC) != 0) return false;
        if (name != null ? !name.equals(cluster.name) : cluster.name != null) return false;
        if (points != null ? !points.equals(cluster.points) : cluster.points != null) return false;
        if (!Arrays.deepEquals(graph, cluster.graph)) return false;
        return results != null ? results.equals(cluster.results) : cluster.results == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(graph);
        temp = Double.doubleToLongBits(EC);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }
}