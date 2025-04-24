package se.kth.iv1350.pointOfSale.integration;

import java.util.ArrayList;

/**
 * This is the external discount database that stores information about the customer.
 * It contains three types of discount that can be applied, one is to the item, second is to the total sale and
 * the third is to the customer.
 */
public class DiscountDatabase {
    Integer customerIDOne = new Integer(12345);
    Integer customerIDTwo = new Integer (56789);
    Integer customerIDThree = new Integer (11213);
    Integer customerIDFour = new Integer (14151);

    private final ArrayList<Integer> customerList = new ArrayList<Integer>();

    public ArrayList<Integer> getCustomerList() {
        return customerList;
    }

    /**
     * The method creates a new instance of the discount database.
     */
    public DiscountDatabase() {
        customerList.add(customerIDOne);
        customerList.add(customerIDTwo);
        customerList.add(customerIDThree);
        customerList.add(customerIDFour);
    }

    public double getCustomerDiscount(int customerID) {
        if (customerList.contains(customerID)) {
            return 0.10;
        }
        return 0.0;
    }

    public double getItemDiscount(int itemID) {
        if (itemID == 123) {
            return 0.05;
        }
        return 0.0;
    }

    public double getTotalDiscount(double totalPrice) {
        if (totalPrice > 500) {
            return 0.20;
        }
        return 0.0;
    }
}