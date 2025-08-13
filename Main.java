import java.util.Scanner;

class Movie {
    private final String name;
    private final String time;

    public Movie(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void displayDetails() {
        System.out.println("Movie: " + name);
        System.out.println("Time: " + time);
    }
}

class Seat {
    private boolean isBooked;
    private final String position;
    private final int seatNumber;

    public Seat(String position, int seatNumber) {
        this.isBooked = false;
        this.position = position;
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getPosition() {
        return position;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void bookSeat() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Seat " + seatNumber + " successfully booked!");
        } else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }
}

class CinemaManagementSystem {
    private static final int TOTAL_SEATS = 10;
    private static final int ROWS = 2;
    private static final int COLUMNS = TOTAL_SEATS / ROWS;
    private final Movie movie;
    private final Seat[] seats;
    private double dailySales;

    // Static Scanner object to avoid closing it prematurely
    private static final Scanner scanner = new Scanner(System.in);

    public CinemaManagementSystem() {
        movie = new Movie("Avengers: Endgame", "7:00 PM");
        seats = new Seat[TOTAL_SEATS];
        for (int i = 0; i < TOTAL_SEATS; i++) {
            String position = (i < TOTAL_SEATS / 2) ? "Front" : "Back";
            seats[i] = new Seat(position, i + 1);
        }
        dailySales = 0;
    }

    public void showMovieDetails() {
        movie.displayDetails();
    }

    public void showAvailableSeats() {
        System.out.println("Available Seats (Grid View):");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int seatIndex = i * COLUMNS + j;
                String display = seats[seatIndex].isBooked() ? "[X]" : "[O]";
                System.out.print(display + "(" + seats[seatIndex].getSeatNumber() + ") ");
            }
            System.out.println();
        }
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            System.out.println("Invalid seat number.");
            return;
        }
        Seat seat = seats[seatNumber - 1];
        if (!seat.isBooked()) {
            seat.bookSeat();
            double price = seat.getPosition().equals("Front") ? 500 : 450;
            dailySales += price;
            System.out.println("Ticket Price: " + price + " Taka");

            // Add-on snacks selection
            System.out.println("Would you like to add snacks to your booking? (1: Popcorn, 2: Drink, 3: Both, 4: None): ");
            int snackChoice = scanner.nextInt();
            double snackCost = 0;
            switch (snackChoice) {
                case 1 -> {
                    snackCost = 100;
                    System.out.println("You added Popcorn (100 Taka).");
                }
                case 2 -> {
                    snackCost = 50;
                    System.out.println("You added a Drink (50 Taka).");
                }
                case 3 -> {
                    snackCost = 140;
                    System.out.println("You added Popcorn and a Drink (140 Taka).");
                }
                case 4 -> System.out.println("No snacks added.");
                default -> System.out.println("Invalid choice. No snacks added.");
            }
            dailySales += snackCost;
            if (snackCost > 0) {
                System.out.println("Total with snacks: " + (price + snackCost) + " Taka");
            }
        } else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }

    public void showDailyStatus() {
        int bookedSeats = 0;
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                bookedSeats++;
            }
        }
        System.out.println("Total Booked Seats: " + bookedSeats);
        System.out.println("Daily Sales: " + dailySales + " Taka");
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Cinema Management System ---");
            System.out.println("1. Show Movie Details");
            System.out.println("2. Show Available Seats");
            System.out.println("3. Book a Seat");
            System.out.println("4. Show Daily Status (Admin)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showMovieDetails();
                case 2 -> showAvailableSeats();
                case 3 -> {
                    System.out.print("Enter seat number to book: ");
                    int seatNumber = scanner.nextInt();
                    bookSeat(seatNumber);
                }
                case 4 -> showDailyStatus();
                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CinemaManagementSystem cms = new CinemaManagementSystem();
        cms.run();
    }
}
