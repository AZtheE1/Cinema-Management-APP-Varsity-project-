package com.cinema.utils;

import com.cinema.models.Movie;
import com.cinema.models.Seat;
import com.cinema.models.Seat.SeatPosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Static seeder for initial data setup on startup.
 */
public final class DataSeeder {
    private DataSeeder() {}

    /**
     * Seeds the system with the predefined movie set and their respective halls.
     *
     * @return listing of all Movies ready for ticketing
     */
    public static List<Movie> seedMovies() {
        List<Movie> movies = new ArrayList<>();
        
        movies.add(createMovie(1, "Avengers: Endgame", "7:00 PM"));
        movies.add(createMovie(2, "Inception", "9:30 PM"));
        movies.add(createMovie(3, "Interstellar", "5:00 PM"));
        movies.add(createMovie(4, "The Dark Knight", "3:00 PM"));
        movies.add(createMovie(5, "Oppenheimer", "6:30 PM"));

        return movies;
    }

    private static Movie createMovie(int id, String title, String showtime) {
        Seat[] seats = new Seat[Constants.TOTAL_SEATS_PER_MOVIE];
        for (int i = 0; i < Constants.TOTAL_SEATS_PER_MOVIE; i++) {
            SeatPosition pos = (i < Constants.FRONT_SEAT_COUNT) ? SeatPosition.FRONT : SeatPosition.BACK;
            seats[i] = new Seat(i + 1, pos);
        }
        return new Movie(id, title, showtime, seats);
    }
}
