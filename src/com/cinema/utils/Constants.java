package com.cinema.utils;

import java.util.Map;
import java.util.Collections;

/**
 * Global application constants for pricing, counts, and snack items.
 */
public final class Constants {
    private Constants() {} // Prevent instantiation

    // Pricing (in Taka)
    public static final int FRONT_SEAT_PRICE = 500;
    public static final int BACK_SEAT_PRICE = 450;
    
    // Seat Configuration
    public static final int TOTAL_SEATS_PER_MOVIE = 40;
    public static final int FRONT_SEAT_COUNT = 20;
    public static final int SEATS_PER_ROW = 10;

    // Snack Menu (Item Name -> Unit Price in Taka)
    public static final Map<String, Integer> SNACK_MENU = Collections.unmodifiableMap(Map.of(
        "Popcorn", 100,
        "Drink", 50,
        "Nachos", 80,
        "Water", 30
    ));

    // UI Formatting
    public static final String CURRENCY_LABEL = "Taka";
    public static final String DIVIDER = "══════════════════════════════════════════════════════";
}
