package com.cinema.ui;

import com.cinema.utils.Constants;

/**
 * Utility class for common console-based formatting and UI cleanup.
 */
public final class ConsoleUtils {
    private ConsoleUtils() {}

    // ANSI Escape Codes for CLI Colors
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BOLD = "\033[0;1m";

    /**
     * Clears the console screen using OS-specific commands.
     */
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print a spacer
            System.out.println("\n\n\n\n\n");
        }
    }

    /**
     * Prints a standardized visual divider for menu segments.
     */
    public static void printDivider() {
        System.out.println(CYAN + Constants.DIVIDER + RESET);
    }

    /**
     * Prints an emphasized header for a menu section.
     *
     * @param title the title to highlight
     */
    public static void printHeader(String title) {
        printDivider();
        System.out.println(BOLD + CYAN + "     " + title.toUpperCase() + RESET);
        printDivider();
    }

    /**
     * Prints a success message with green color.
     *
     * @param message the message to print
     */
    public static void printSuccess(String message) {
        System.out.println(GREEN + " [✔] " + message + RESET);
    }

    /**
     * Prints an error message with red color.
     *
     * @param message the message to print
     */
    public static void printError(String message) {
        System.out.println(RED + " [✘] " + message + RESET);
    }

    /**
     * Prints info with yellow color.
     *
     * @param message the message to print
     */
    public static void printInfo(String message) {
        System.out.println(YELLOW + " [!] " + message + RESET);
    }
}
