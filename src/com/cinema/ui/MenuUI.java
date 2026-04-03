package com.cinema.ui;

import com.cinema.models.Movie;
import com.cinema.models.SnackOrder;
import com.cinema.services.BookingService;
import com.cinema.services.ReportService;
import com.cinema.services.SnackService;
import com.cinema.utils.Constants;
import com.cinema.utils.InputValidator;
import java.util.List;
import java.util.Scanner;

/**
 * Main command loop for overall application interaction.
 */
public class MenuUI {
    private final Scanner scanner = new Scanner(System.in);
    private final BookingService bookingService = new BookingService();
    private final SnackService snackService = new SnackService();
    private final ReportService reportService = new ReportService();
    private final SeatGridUI seatGridUI = new SeatGridUI();
    private final List<Movie> movies;
    private Movie currentMovie;

    /**
     * Initializes UI with the injected data listing.
     *
     * @param movies list of all system cinema shows
     */
    public MenuUI(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Infinite entry-point loop for handling all menu navigation.
     */
    public void start() {
        while (true) {
            ConsoleUtils.printHeader("MAIN MENU");
            System.out.println(" [1] View Movies");
            System.out.println(" [2] Select Movie & Book Seats");
            System.out.println(" [3] Order Snacks");
            System.out.println(" [4] View Seat Grid (Selected Movie)");
            System.out.println(" [5] Admin Report (Daily Sales)");
            System.out.println(" [0] Exit");
            ConsoleUtils.printDivider();
            System.out.print(" Enter choice: ");

            int choice = InputValidator.getValidIntegerInput(scanner);
            switch (choice) {
                case 1 -> showMovies();
                case 2 -> selectAndBookMovie();
                case 3 -> handleSnacks();
                case 4 -> handleViewGrid();
                case 5 -> reportService.generateDailyReport(movies, snackService.getTotalSnackRevenue());
                case 0 -> {
                    ConsoleUtils.printInfo("Exiting. Thank you for using Cinema Management System!");
                    return;
                }
                default -> ConsoleUtils.printError("Invalid Choice. Please try again.");
            }
        }
    }

    private void showMovies() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("CINEMA MOVIE LISTINGS");
        for (Movie m : movies) {
            System.out.printf(" [%d] %-20s (%-10s) - Available Seats: %d\n", 
                m.getId(), m.getTitle(), m.getShowtime(), m.getAvailableSeatCount());
        }
        ConsoleUtils.printDivider();
    }

    private void selectAndBookMovie() {
        showMovies();
        System.out.print(" Select movie ID to book: ");
        int id = InputValidator.getValidIntegerInput(scanner);
        
        currentMovie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);

        if (currentMovie == null) {
            ConsoleUtils.printError("Invalid Movie ID.");
            return;
        }

        seatGridUI.render(currentMovie);
        System.out.print(" Enter seat number(s) [e.g. 1,2,5]: ");
        String seatStr = scanner.nextLine();
        int[] seatNums = InputValidator.parseBulkSeats(seatStr);

        BookingService.BookingResult result = bookingService.processBooking(currentMovie, seatNums);
        if (result.success()) {
            ConsoleUtils.printSuccess(result.message());
            System.out.println(" Total Ticket Cost: " + result.totalCost() + " " + Constants.CURRENCY_LABEL);
            
            // Snack prompt after successful booking
            System.out.print("\n Would you like to add snacks to this booking? (Y/N): ");
            String snackPrompt = scanner.nextLine().trim();
            if (snackPrompt.equalsIgnoreCase("Y")) {
                handleSnacks();
            }
        } else {
            ConsoleUtils.printError(result.message());
        }
    }

    private void handleSnacks() {
        SnackOrder order = snackService.purchaseSnack(scanner);
        if (order != null) {
            ConsoleUtils.printSuccess("Added " + order.quantity() + "x " + order.snackName() + " to your order.");
            System.out.println(" Total Snack Bill: " + order.getTotal() + " " + Constants.CURRENCY_LABEL);
        }
    }

    private void handleViewGrid() {
        if (currentMovie == null) {
            ConsoleUtils.printInfo("No movie selected. Please choose a movie first.");
            selectAndBookMovie();
        } else {
            seatGridUI.render(currentMovie);
        }
    }
}
