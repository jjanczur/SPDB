package helpers;

import calculators.CoordinatesCalculator;
import model.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParser {

    private static final String SEPARATOR = ",";

    /**
     * Method to read points from csv file it reads the file line by line and creates the points
     * @param inputFilePath path to the input .csv file
     * @return  list of the points created based on the input file
     * @throws IOException
     */
    public List<Point> readPoints(String inputFilePath) throws IOException {
        File inputFile = new File(inputFilePath);
        InputStream inputStream = new FileInputStream(inputFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> csvLines = bufferedReader.lines()
                .skip(1)
                .collect(Collectors.toList());

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < csvLines.size(); i++) {
            Point point = createPointFromCsvLine(csvLines.get(i), i);
            points.add(point);
        }

        bufferedReader.close();
        return points;
    }

    /**
     *  Method to create point object from single csv line
     * @param csvLine line read from .csv file
     * @param idx index of the new point
     * @return line converted to the point
     */
    private Point createPointFromCsvLine(String csvLine, int idx) {
        String[] csvCells = csvLine.split(SEPARATOR);
        Point point = new Point();
        point.setId(idx);
        point.setOriginalCluster(csvCells[1]);
        point.setLatitude(Double.valueOf(csvCells[2]));
        point.setY(CoordinatesCalculator.convertLatitudeToY(point.getLatitude()));
        point.setLongitude(Double.valueOf(csvCells[3]));
        point.setX(CoordinatesCalculator.convertLongitudeToX(point.getLongitude()));

        return point;
    }
}
