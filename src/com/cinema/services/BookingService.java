package com.cinema.services;

import com.cinema.models.Movie;
import com.cinema.models.Seat;
import com.cinema.ui.ConsoleUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * All business logic relating to seat reservations.
 */
public class BookingService {

    /**
     * Record representing the outcome of a booking attempt.
     */
    public record BookingResult(boolean success, String message, double totalCost) {}

    /**
     * Attempts to book multiple seats at once for a specific movie.
     *
     * @param movie       movie to book for
     * @param seatNumbers the desired seat numbers e.g. [1, 2, 5]
     * @return outcome record containing success state and receipt info
     */
    public BookingResult processBooking(Movie movie, int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length == 0) {
            return new BookingResult(false, "Invalid seat selection.", 0);
        }

        Seat[] hallSeats = movie.getSeats();
        List<Seat> targetSeats = new ArrayList<>();
        double totalTicketPrice = 0;

        // Validation Phase 1: Range Check
        for (int seatNum : seatNumbers) {
            if (seatNum < 1 || seatNum > hallSeats.length) {
                return new BookingResult(false, "Seat number " + seatNum + " is invalid for this hall.", 0);
            }
            targetSeats.add(hallSeats[seatNum - 1]);
        }

        // Validation Phase 2: Availability Check
        for (Seat seat : targetSeats) {
            if (seat.isBooked()) {
                return new BookingResult(false, "Seat " + seat.getSeatNumber() + " is already taken.", 0);
            }
        }

        // Execution Phase
        for (Seat seat : targetSeats) {
            seat.book();
            totalTicketPrice += seat.getPrice();
        }

        return new BookingResult(
            true, 
            String.format("Successfully booked %d seat(s) for '%s'.", 
            targetSeats.size(), movie.getTitle()), 
            totalTicketPrice
        );
    }
}
