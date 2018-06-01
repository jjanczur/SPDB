package calculators;

import model.Cluster;
import model.Results;
import model.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsCalculator {

    private static final int MIN_CLUSTER_OCCURRENCE = 3;

    /**
     * Method to calculate metrics of clustering
     * @param clusters list of clusters
     * @return Result object with fields such as nrOfPoints, nrOfPositiveClassifiedPoints, accuracy, averagePurity
     */
    public Results calculate(List<Cluster> clusters) {
        int nrOfPoints = 0;
        int nrOfPositiveClassifiedPoints = 0;
        double totalPurity = 0;

        for (Cluster cluster : clusters) {
            Results clusterResults = calculateClusterMetrics(cluster);
            cluster.setResults(clusterResults);
            nrOfPoints += clusterResults.getNrOfPoints();
            nrOfPositiveClassifiedPoints += clusterResults.getNrOfPositiveClassifiedPoints();
            totalPurity += clusterResults.getPurity();
        }

        double accuracy = (double) nrOfPositiveClassifiedPoints / (double) nrOfPoints;
        double averagePurity = totalPurity / (double) clusters.size();

        return new Results(nrOfPoints, nrOfPositiveClassifiedPoints, accuracy, averagePurity);
    }

    /**
     * Method to calculate metrics for each cluster
     * @param cluster cluster for which metrics are going to be calculated
     * @return Result object with fields such as nrOfPoints, nrOfPositiveClassifiedPoints, accuracy, averagePurity
     */
    private Results calculateClusterMetrics(Cluster cluster) {
        int nrOfPoints = cluster.getPoints().size();
        int nrOfPositiveClassifiedPoints = 0;
        String clusterName = cluster.getName();
        // Map (originalClusterName, count of occurrences)
        Map<String, Integer> occurrences = new HashMap<>();

        for (Point point : cluster.getPoints()) {
            String originalClusterName = point.getOriginalCluster();
            if (clusterName.equals(originalClusterName)) {
                nrOfPositiveClassifiedPoints++;
            }

            if (occurrences.containsKey(originalClusterName)) {
                occurrences.merge(originalClusterName, 1, Integer::sum);
            } else {
                occurrences.put(originalClusterName, 1);
            }
        }

        double accuracy = (double) nrOfPositiveClassifiedPoints / (double) nrOfPoints;
        double purity = occurrences.entrySet().stream()
                .filter(x -> x.getValue() >= MIN_CLUSTER_OCCURRENCE)
                .count();

        return new Results(nrOfPoints, nrOfPositiveClassifiedPoints, accuracy, purity);
    }
}