package com.maghrebtrip.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationParser {

    public static void main(String[] args) {
        String input = "34°1'3.601'N, 6°49'51.413'W";

        String[] coordinates = input.split(", ");
        String latitude = coordinates[0];
        String longitude = coordinates[1];

        double lat = convertToDecimalDegrees(latitude);
        double lng = convertToDecimalDegrees(longitude);

        System.out.println("Latitude: " + lat);
        System.out.println("Longitude: " + lng);
    }

    public static double[] parseLocation(String location) {
        String[] coordinates = location.split(", ");
        String latitude = coordinates[0];
        String longitude = coordinates[1];

        double lat = convertToDecimalDegrees(latitude);
        double lng = convertToDecimalDegrees(longitude);

        return new double[]{lat, lng};
    }

    private static double convertToDecimalDegrees(String coord) {
        String[] parts = coord.split("[°'\"]");

        double degrees = Double.parseDouble(parts[0]);
        double minutes = Double.parseDouble(parts[1]);
        double seconds = Double.parseDouble(parts[2]);
        char direction = coord.charAt(coord.length() - 1);

        double decimalDegrees = degrees + (minutes / 60) + (seconds / 3600);

        // Adjust for the direction
        if (direction == 'S' || direction == 'W') {
            decimalDegrees *= -1;
        }

        return decimalDegrees;
    }
}
