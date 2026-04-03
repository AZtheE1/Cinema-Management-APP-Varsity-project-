package com.cinema.ui;

import com.cinema.models.Movie;
import com.cinema.models.Seat;
import com.cinema.utils.Constants;

/**
 * All console-based UI Logic for rendering the visual seat grid.
 */
public class SeatGridUI {

    /**
     * Renders a cinema-style hall map for the specified movie's show.
     *
     * @param movie current movie selection
     */
    public void render(Movie movie) {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("SEAT LAYOUT - " + movie.getTitle().toUpperCase());
        ConsoleUtils.printInfo(" [O] = Available  |  [X] = Booked");
        ConsoleUtils.printDivider();

        Seat[] seats = movie.getSeats();
        int seatsPerRow = Constants.SEATS_PER_ROW;

        System.out.println("  --- FRONT SEATS (" + Constants.FRONT_SEAT_PRICE + " " + Constants.CURRENCY_LABEL + ") ---");
        renderSubset(seats, 0, Constants.FRONT_SEAT_COUNT, seatsPerRow);

        ConsoleUtils.printDivider();

        System.out.println("  --- BACK SEATS (" + Constants.BACK_SEAT_PRICE + " " + Constants.CURRENCY_LABEL + ") ---");
        renderSubset(seats, Constants.FRONT_SEAT_COUNT, Constants.TOTAL_SEATS_PER_MOVIE, seatsPerRow);

        ConsoleUtils.printDivider();
        System.out.println("      AVAILABLE SEATS: " + movie.getAvailableSeatCount() + " / " + Constants.TOTAL_SEATS_PER_MOVIE);
        ConsoleUtils.printDivider();
    }

    private void renderSubset(Seat[] seats, int start, int end, int perRow) {
        for (int i = start; i < end; i++) {
            Seat s = seats[i];
            String symbol = s.isBooked() ? "[X]" : "[O]";
            String color = s.isBooked() ? ConsoleUtils.RED : ConsoleUtils.GREEN;
            
            System.out.printf(" %s%s:%-2d%s ", color, symbol, s.getSeatNumber(), ConsoleUtils.RESET);
            
            if ((i + 1 - start) % perRow == 0) {
                System.out.println();
            }
        }
    }
}
