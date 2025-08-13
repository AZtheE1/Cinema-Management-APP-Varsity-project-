import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Version {
    public static void main(String[] args) {
        CinemaManagementSystem system = new CinemaManagementSystem();
        system.run();
    }
}

class Movie {
    private final String name;
    private final String time;
    private final Seat[] seats;

    public Movie(String name, String time, int totalSeats) {
        this.name = name;
        this.time = time;
        this.seats = new Seat[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = new Seat(i + 1);
        }
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void displayDetails() {
        System.out.println(" Movie: " + name);
        System.out.println(" Time: " + time);
    }
}

class Seat {
    private boolean isBooked;
    private final int seatNumber;

    public Seat(int seatNumber) {
        this.isBooked = false;
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean bookSeat() {
        if (isBooked) {
            System.out.println(" Seat " + seatNumber + " is already booked.");
            return false;
        }
        isBooked = true;
        System.out.println(" Seat " + seatNumber + " successfully booked!");
        return true;
    }
}

class CinemaManagementSystem {
    private static final int TOTAL_SEATS = 40;
    private final List<Movie> movies;
    private double dailySales;
    private Movie currentMovie;

    private static final Scanner scanner = new Scanner(System.in);

    public CinemaManagementSystem() {
        movies = new ArrayList<>();
        initializeMovies();
    }

    private void initializeMovies() {
        movies.add(new Movie("Avengers: Endgame", "7:00 PM", TOTAL_SEATS));
        movies.add(new Movie("Inception", "9:30 PM", TOTAL_SEATS));
        movies.add(new Movie("The Dark Knight", "5:30 PM", TOTAL_SEATS));
        movies.add(new Movie("Interstellar", "6:00 PM", TOTAL_SEATS));
    }

    public void showMovieList() {
        System.out.println("\n==================================");
        System.out.println("            MOVIE LIST            ");
        System.out.println("==================================");
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.printf(" %d. %s (%s)\n", i + 1, movie.getName(), movie.getTime());
        }
        System.out.println("==================================");
    }

    public void selectMovie() {
        while (true) {
            showMovieList();
            System.out.print(" Select a movie by entering its number: ");
            int choice = getValidInput(1, movies.size());
            currentMovie = movies.get(choice - 1);
            System.out.println("\n==================================");
            System.out.println(" Selected Movie:");
            currentMovie.displayDetails();
            System.out.println("==================================");
            break;
        }
    }

    public void showAvailableSeats() {
        if (currentMovie == null) {
            System.out.println(" Please select a movie first!");
            return;
        }

        System.out.println("\n==================================");
        System.out.println("          AVAILABLE SEATS         ");
        System.out.println("==================================");

        Seat[] seats = currentMovie.getSeats();

        System.out.print("      ");
        for (int col = 1; col <= 10; col++) {
            System.out.printf("  %2d ", col);
        }
        System.out.println();

        System.out.println("==================================");

        for (int row = 0; row < 4; row++) {
            System.out.printf("Row %d ", row + 1);
            for (int col = 0; col < 10; col++) {
                int seatIndex = row * 10 + col;
                String display = seats[seatIndex].isBooked() ? "[X]" : "[O]";
                System.out.printf(" %4s", display);
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    public void bookSeats(int[] seatNumbers) {
        if (currentMovie == null) {
            System.out.println(" Please select a movie before booking seats!");
            return;
        }

        Seat[] seats = currentMovie.getSeats();
        double totalTicketPrice = 0;

        System.out.println("\n==================================");
        System.out.println("          BOOKING SEATS           ");
        System.out.println("==================================");
        for (int seatNumber : seatNumbers) {
            if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
                System.out.println(" Invalid seat number: " + seatNumber);
                continue;
            }

            Seat seat = seats[seatNumber - 1];
            if (seat.bookSeat()) {
                double ticketPrice = seatNumber <= TOTAL_SEATS / 2 ? 500 : 450;
                totalTicketPrice += ticketPrice;
                System.out.println(" Seat " + seatNumber + " booked. Ticket Price: " + ticketPrice + " Taka");
            }
        }

        if (totalTicketPrice > 0) {
            double snackCost = handleSnackSelection();
            dailySales += totalTicketPrice + snackCost;
            System.out.println("==================================");
            System.out.println(" Total ticket cost: " + totalTicketPrice + " Taka");
            System.out.println(" Total snack cost: " + snackCost + " Taka");
            System.out.println(" Grand Total: " + (totalTicketPrice + snackCost) + " Taka");
            System.out.println("==================================");
        }
    }

    private double handleSnackSelection() {
        System.out.println("\n==================================");
        System.out.println("            SNACK MENU            ");
        System.out.println("==================================");
        System.out.println(" 1. Popcorn (100 Taka each)");
        System.out.println(" 2. Drink (50 Taka each)");
        System.out.println(" 3. Both (140 Taka per set)");
        System.out.println(" 4. None");
        System.out.println("==================================");
        System.out.print(" Enter your choice: ");
        int choice = getValidInput(1, 4);

        if (choice == 4) {
            return 0;
        }

        System.out.print(" Enter quantity: ");
        int quantity = getValidInput(1, 10);

        return switch (choice) {
            case 1 -> 100 * quantity;
            case 2 -> 50 * quantity;
            case 3 -> 140 * quantity;
            default -> 0;
        };
    }

    private int getValidInput(int min, int max) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print(" Invalid choice. Please select a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print(" Invalid input. Please enter a valid number: ");
            }
        }
    }

    public void showDailyStatus() {
        System.out.println("\n==================================");
        System.out.println("           DAILY STATUS           ");
        System.out.println("==================================");
        long bookedSeats = movies.stream()
                                  .flatMap(movie -> java.util.Arrays.stream(movie.getSeats()))
                                  .filter(Seat::isBooked)
                                  .count();
        System.out.println(" Total Booked Seats: " + bookedSeats);
        System.out.println(" Total Sales (Tickets + Snacks): " + dailySales + " Taka");
        System.out.println("\n Detailed Breakdown:");
        System.out.println("==================================");
        for (Movie movie : movies) {
            long movieBookedSeats = java.util.Arrays.stream(movie.getSeats())
                                                     .filter(Seat::isBooked)
                                                     .count();
            System.out.printf(" Movie: %s - Booked Seats: %d\n", movie.getName(), movieBookedSeats);
        }
        System.out.println("==================================");
    }

    public void run() {
        while (true) {
            System.out.println("\n==================================");
            System.out.println(" WELCOME TO THE CINEMA SYSTEM");
            System.out.println("==================================");
            System.out.println(" 1. Select Movie");
            System.out.println(" 2. Show Available Seats");
            System.out.println(" 3. Book Seats");
            System.out.println(" 4. Show Daily Status");
            System.out.println(" 5. Exit");
            System.out.println("==================================");
            System.out.print(" Enter your choice: ");
            int choice = getValidInput(1, 5);

            switch (choice) {
                case 1 -> selectMovie();
                case 2 -> showAvailableSeats();
                case 3 -> {
                    System.out.print(" Enter seat numbers to book (comma-separated): ");
                    int[] seatNumbers = java.util.Arrays.stream(scanner.nextLine().split(","))
                                                         .mapToInt(Integer::parseInt)
                                                         .toArray();
                    bookSeats(seatNumbers);
                }
                case 4 -> showDailyStatus();
                case 5 -> {
                    System.out.println(" Exiting the system. Thank you for using Cinema Management System!");
                    return;
                }
            }
        }
    }
}
