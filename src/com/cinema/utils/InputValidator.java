package com.cinema.utils;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Static utility for all input validation and parsing.
 */
public final class InputValidator {
    private InputValidator() {}

    /**
     * Parses a single integer with error handling.
     *
     * @param scanner input scanner
     * @return parsed integer or -1 if invalid
     */
    public static int getValidIntegerInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks if a choice is within a specified numeric range.
     *
     * @param choice user input choice
     * @param min    inclusive minimum
     * @param max    inclusive maximum
     * @return true if valid
     */
    public static boolean isInRange(int choice, int min, int max) {
        return choice >= min && choice <= max;
    }

    /**
     * Parses comma-separated seat numbers string into an int array.
     *
     * @param input raw comma-separated string e.g. "1, 2, 5"
     * @return array of parsed integers, empty if selection was invalid
     */
    public static int[] parseBulkSeats(String input) {
        if (input == null || input.isBlank()) {
            return new int[0];
        }
        try {
            return Arrays.stream(input.split(","))
                         .map(String::trim)
                         .filter(s -> !s.isEmpty())
                         .mapToInt(Integer::parseInt)
                         .toArray();
        } catch (NumberFormatException e) {
            return new int[0];
        }
    }

    /**
     * Validates if a seat number is valid for the current system.
     *
     * @param seatNumber the number to check
     * @return true if in seat range
     */
    public static boolean isValidSeatNumber(int seatNumber) {
        return seatNumber >= 1 && seatNumber <= Constants.TOTAL_SEATS_PER_MOVIE;
    }
}
