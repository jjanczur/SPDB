package calculators;


import model.Point;


public class CoordinatesCalculator {

    private final static double DEGREES_TO_RADIANS = Math.PI / 180.0;
    private final static double EARTH_RADIUS_KM = 6371.0;

    // Constants used for Mercator projection
    private final static double MAP_WIDTH = 2000.0;
    private final static double MAP_HEIGHT = 1000.0;


    /** Method to calculate distance between two points on a sphere.<br>
     * It implements Haversine formula<br>
     * https://en.wikipedia.org/wiki/Haversine_formula<br>
     * @param first first point with it's coordinates
     * @param second second point with it's coordinates
     * @return distance between two points
     */
    public static double getDistance(Point first, Point second) {
        double degreesLongitude = (second.getLongitude() - first.getLongitude()) * DEGREES_TO_RADIANS;
        double degreesLatitude = (second.getLatitude() - first.getLatitude()) * DEGREES_TO_RADIANS;

        double firstLatitude = first.getLatitude() * DEGREES_TO_RADIANS;
        double secondLatitude = second.getLatitude() * DEGREES_TO_RADIANS;

        double a = Math.pow(Math.sin(degreesLatitude / 2), 2) +
                Math.pow(Math.sin(degreesLongitude / 2), 2) * Math.cos(firstLatitude) * Math.cos(secondLatitude);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return c * EARTH_RADIUS_KM;
    }

    /*

     */

    /** Method to convert longitude to X coordinate.<br>
     * It implements Mercator projection.<br>
     * https://en.wikipedia.org/wiki/Mercator_projection<br>
     * @param longitude longitude of a point
     * @return longitude as a X coordinate
     */
    public static double convertLongitudeToX(double longitude) {
        return (longitude + 180.0) * (MAP_WIDTH / 360);
    }


    /** Method to convert longitude to Y coordinate.<br>
     * It implements Mercator projection.<br>
     * https://en.wikipedia.org/wiki/Mercator_projection<br>
     * @param latitude latitude of the point
     * @return latitude as an Y coordinate
     */
    public static double convertLatitudeToY(double latitude) {
        double mercator = Math.log(Math.tan((Math.PI / 4) + ((latitude * DEGREES_TO_RADIANS) / 2)));
        return (MAP_HEIGHT / 2) - (MAP_WIDTH * mercator / (2 * Math.PI));
    }
}