package com.cinema.services;

import com.cinema.models.SnackOrder;
import com.cinema.ui.ConsoleUtils;
import com.cinema.utils.Constants;
import com.cinema.utils.InputValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service for handling snack menu display and orders.
 */
public class SnackService {
    private final List<SnackOrder> globalHistory = new ArrayList<>();

    /**
     * Records an order after successful verification and user choice.
     *
     * @param scanner user input scanner
     * @return a single SnackOrder created sessionally
     */
    public SnackOrder purchaseSnack(Scanner scanner) {
        ConsoleUtils.printHeader("SNACK MENU");
        List<String> items = new ArrayList<>(Constants.SNACK_MENU.keySet());
        
        for (int i = 0; i < items.size(); i++) {
            String name = items.get(i);
            int price = Constants.SNACK_MENU.get(name);
            System.out.printf(" [%d] %-12s : %d %s\n", i + 1, name, price, Constants.CURRENCY_LABEL);
        }
        System.out.println(" [" + (items.size() + 1) + "] None / Back");
        ConsoleUtils.printDivider();
        System.out.print(" Select a snack by number: ");

        int choice = InputValidator.getValidIntegerInput(scanner);
        if (choice < 1 || choice > items.size()) {
            return null; // Return or go back
        }

        String itemName = items.get(choice - 1);
        int unitPrice = Constants.SNACK_MENU.get(itemName);

        System.out.print(" Enter quantity: ");
        int qty = InputValidator.getValidIntegerInput(scanner);
        if (qty <= 0) {
            ConsoleUtils.printError("Invalid quantity.");
            return null;
        }

        SnackOrder order = new SnackOrder(itemName, qty, unitPrice);
        globalHistory.add(order);
        return order;
    }

    /**
     * Calculates the total revenue generated from all snacks ordered in the session.
     *
     * @return grand total of all snack items
     */
    public double getTotalSnackRevenue() {
        return globalHistory.stream().mapToDouble(SnackOrder::getTotal).sum();
    }
}
