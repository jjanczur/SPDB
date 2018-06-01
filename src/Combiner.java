import algorithm.ChameleonAlgorithm;
import calculators.ResultsCalculator;
import graphics.ResultsVisualization;
import model.Cluster;
import model.Results;
import model.Point;
import helpers.CsvParser;

import java.io.IOException;
import java.util.List;

public class Combiner {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("Arguments: fileName, k, initNrOfClusters, resultNrOfClusters");
            return;
        }

        String fileName = args[0];
        int k = Integer.parseInt(args[1]);
        int initNrOfClusters = Integer.parseInt(args[2]);
        int resultNrOfClusters = Integer.parseInt(args[3]);

        CsvParser csvParser = new CsvParser();
        List<Point> points = null;
        try {
            points = csvParser.readPoints("dataset/" + fileName);
        } catch (IOException e) {
            System.out.println("Could not read input file: " + fileName);
            System.exit(1);
        }

        System.out.println("Number of points: " + points.size());

        // Run ChameleonAlgorithm algorithm
        ChameleonAlgorithm chameleonAlgorithm = new ChameleonAlgorithm(k, initNrOfClusters, resultNrOfClusters, points);
        List<Cluster> clusters = chameleonAlgorithm.run();

        // Compute results
        ResultsCalculator resultsCalculator = new ResultsCalculator();
        Results results = resultsCalculator.calculate(clusters);

        // Visualize results // Nie działa poprawnie
        //TODO poprawić wizualizajce - rysowanie punktów
        //ResultsVisualization visualization = new ResultsVisualization(clusters);
        //visualization.drawImage(fileName.replace(".csv", ".png"));

        // Print results
        System.out.println("Results: ");
        System.out.println(results);
        clusters.forEach(x -> System.out.println(x.printMetrics()));
    }
}