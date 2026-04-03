# Cinema Management System

A professionally refactored Cinema Management System developed as a varsity project in Java. This version unifies multiple development stages into a single, clean, production-ready codebase with a robust Object-Oriented structure.

## Features

- **Multi-Movie Support:** Choose from several blockbuster movies with unique showtimes.
- **Visual Seat Booking:** View available and booked seats in a high-fidelity ASCII grid layout.
- **Bulk Booking:** Book multiple seats by entering comma-separated numbers (e.g., `1,2,5`).
- **Dynamic Pricing:** Automatically calculates costs based on seat position (Front vs. Back).
- **Integrated Snack Menu:** Add snacks like Popcorn and Drinks to your order with ease.
- **Admin Reporting:** Generate daily sales reports including total revenue breakdown and movie performance.
- **Modern Java Logic:** Utilizes Java 17 features like Records and Enhanced Switch expressions.

## Pricing Table

| Item | Price | Details |
| :--- | :--- | :--- |
| **Front Seat** | 500 Taka | Rows 1-2 (Seats 1-20) |
| **Back Seat** | 450 Taka | Rows 3-4 (Seats 21-40) |
| **Popcorn** | 100 Taka | Large bucket |
| **Drink** | 50 Taka | Chilled soda |
| **Nachos** | 80 Taka | With cheese dip |
| **Water** | 30 Taka | Mineral water |

## How to Run

1.  **Compile:**
    ```sh
    javac -d out -sourcepath src src/com/cinema/Main.java
    ```
2.  **Run:**
    ```sh
    java -cp out com.cinema.Main
    ```

## Project Structure

- `com.cinema.models` - Core entities (`Movie`, `Seat`, `SnackOrder`).
- `com.cinema.services` - Business logic handlers (`Booking`, `Snack`, `Report`).
- `com.cinema.ui` - Interface and rendering logic (`Menu`, `SeatGrid`, `ConsoleUtils`).
- `com.cinema.utils` - Support classes (`Constants`, `InputValidator`, `DataSeeder`).

## Author

- **Primary Developer:** [Md. Abu Zihad](https://github.com/AZtheE1)
- **Email:** azihad783@gmail.com
- **GitHub:** [AZtheE1](https://github.com/AZtheE1)


*Note: This application is a protected varsity project. Unauthorized distribution or claiming authorship is strictly prohibited.*
