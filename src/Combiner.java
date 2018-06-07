import algorithm.ChameleonAlgorithm;
import calculators.ResultsCalculator;
import graphics.ResultsVisualization;
import helpers.CsvParser;
import model.Cluster;
import model.Point;
import model.Results;

import java.io.IOException;
import java.util.List;

public class Combiner {

    public static void main(String[] args) {

//        if (args.length != 4) {
//            System.out.println("Arguments: fileName, k, initNrOfClusters, resultNrOfClusters");
//            return;
//        }


        String fileName = "all2.csv";//args[0];
        int k = 54;//Integer.parseInt(args[1]);
        int initNrOfClusters = 100;//Integer.parseInt(args[2]);
        int resultNrOfClusters = 54;//Integer.parseInt(args[3]);

        CsvParser csvParser = new CsvParser();
        List<Point> points = null;
        try {
            points = csvParser.readPoints("data/" + fileName);
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


        ResultsVisualization visualization = new ResultsVisualization(clusters);
        visualization.drawImage(fileName.replace(".csv", ".png"));

        // Print results
        System.out.println("Results: ");
        System.out.println(results);
        clusters.forEach(x -> System.out.println(x.printMetrics()));
    }
}