package com.cinema;

import com.cinema.models.Movie;
import com.cinema.ui.MenuUI;
import com.cinema.utils.DataSeeder;
import java.util.List;

/**
 * Entry point for the Cinema Management System.
 * 
 * @author Md. Abu Zihad (AZtheE1)
 * @email azihad783@gmail.com
 */
public class Main {
    /**
     * Main method - Initializes system data and starts the UI loop.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize Data
        List<Movie> movieListing = DataSeeder.seedMovies();

        // Start UI Loop
        MenuUI menu = new MenuUI(movieListing);
        menu.start();
    }
}
