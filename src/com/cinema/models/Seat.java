package com.cinema.models;

import com.cinema.utils.Constants;

/**
 * Represents a single seat in a movie hall.
 */
public class Seat {
    /**
     * Enum for seat placement.
     */
    public enum SeatPosition {
        FRONT,
        BACK
    }

    private final int seatNumber;
    private final SeatPosition position;
    private boolean isBooked;

    /**
     * Constructs a new Seat.
     *
     * @param seatNumber the number assigned to the seat
     * @param position   physical placement in the hall (FRONT/BACK)
     */
    public Seat(int seatNumber, SeatPosition position) {
        this.seatNumber = seatNumber;
        this.position = position;
        this.isBooked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatPosition getPosition() {
        return position;
    }

    public boolean isBooked() {
        return isBooked;
    }

    /**
     * Marks the seat as booked.
     *
     * @return true if successful, false if already booked
     */
    public boolean book() {
        if (!isBooked) {
            isBooked = true;
            return true;
        }
        return false;
    }

    /**
     * Returns the price of the seat based on its position.
     *
     * @return seat price in Taka
     */
    public int getPrice() {
        return position == SeatPosition.FRONT ? Constants.FRONT_SEAT_PRICE : Constants.BACK_SEAT_PRICE;
    }
}
