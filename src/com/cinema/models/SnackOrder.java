package com.cinema.models;

/**
 * Record representing an individual snack order.
 *
 * @param snackName itemName from Constants.SNACK_MENU
 * @param quantity  number of items ordered
 * @param unitPrice cost per unit in Taka
 */
public record SnackOrder(String snackName, int quantity, int unitPrice) {
    /**
     * Returns the total cost for this snack line item.
     *
     * @return quantity multiplied by unitPrice
     */
    public int getTotal() {
        return quantity * unitPrice;
    }
}
