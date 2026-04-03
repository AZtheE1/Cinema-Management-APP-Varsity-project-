package com.cinema.models;

import java.util.Arrays;

/**
 * Represent a movie listing with title, showtime, and associated seats.
 */
public class Movie {
    private final int id;
    private final String title;
    private final String showtime;
    private final Seat[] seats;

    /**
     * Constructs a Movie instance with predefined seats.
     *
     * @param id       unique identifier
     * @param title    movie title
     * @param showtime movie showtime
     * @param seats    the array of seats for this movie's show
     */
    public Movie(int id, String title, String showtime, Seat[] seats) {
        this.id = id;
        this.title = title;
        this.showtime = showtime;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShowtime() {
        return showtime;
    }

    public Seat[] getSeats() {
        return seats;
    }

    /**
     * Calculates number of currently available seats.
     *
     * @return count of unbooked seats
     */
    public long getAvailableSeatCount() {
        return Arrays.stream(seats).filter(s -> !s.isBooked()).count();
    }

    /**
     * Calculates total revenue generated for this movie from tickets.
     *
     * @return sum of booked seat prices
     */
    public double getTotalRevenue() {
        return Arrays.stream(seats)
                     .filter(Seat::isBooked)
                     .mapToInt(Seat::getPrice)
                     .sum();
    }
}
