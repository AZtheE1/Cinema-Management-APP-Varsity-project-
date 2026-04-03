package com.cinema.services;

import com.cinema.models.Movie;
import com.cinema.ui.ConsoleUtils;
import com.cinema.utils.Constants;
import java.util.List;

/**
 * All production Logic for generating summaries and sales breakdowns.
 */
public class ReportService {

    /**
     * Generates a cleanly formatted report of all movies, seat sales, and snacks.
     *
     * @param movies            list of all system movies
     * @param snackTotalRevenue revenue from current session's snack purchases
     */
    public void generateDailyReport(List<Movie> movies, double snackTotalRevenue) {
        ConsoleUtils.printHeader("DAILY SALES SUMMARY");

        // Column Titles
        String format = " %-20s | %-10s | %-10s | %-15s \n";
        System.out.printf(format, "Movie Title", "Showtime", "Booked/Max", "Ticket Sales");
        ConsoleUtils.printDivider();

        double grandTicketTotal = 0;

        for (Movie m : movies) {
            long booked = Constants.TOTAL_SEATS_PER_MOVIE - m.getAvailableSeatCount();
            double rev = m.getTotalRevenue();
            grandTicketTotal += rev;

            System.out.printf(format, 
                m.getTitle(), 
                m.getShowtime(), 
                booked + "/" + Constants.TOTAL_SEATS_PER_MOVIE, 
                rev + " " + Constants.CURRENCY_LABEL
            );
        }

        ConsoleUtils.printDivider();
        System.out.printf(" %-40s : %10.2f %s\n", "Total Ticket Sales", grandTicketTotal, Constants.CURRENCY_LABEL);
        System.out.printf(" %-40s : %10.2f %s\n", "Total Snack Sales", snackTotalRevenue, Constants.CURRENCY_LABEL);
        ConsoleUtils.printDivider();
        System.out.printf(ConsoleUtils.BOLD + " %-40s : %10.2f %s\n" + ConsoleUtils.RESET, 
            "GRAND TOTAL REVENUE", (grandTicketTotal + snackTotalRevenue), Constants.CURRENCY_LABEL
        );
        ConsoleUtils.printDivider();
    }
}
