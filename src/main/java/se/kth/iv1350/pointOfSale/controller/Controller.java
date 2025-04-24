package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.Register;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.SystemCreator;
import se.kth.iv1350.pointOfSale.model.Receipt;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.model.Payment;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Register register;
    private Printer printer;
    private Payment payment;
    private Receipt receipt;
    private Sale sale;
    private SystemCreator systemCreator;

    /**
     * Create a new instance of Controller class with specified register and printer.
     * @param register is used to track the cash.
     * @param printer is used to print the receipt at the end of the sale.
     */
    public Controller(Register register, Printer printer, SystemCreator systemCreator) {
        this.register = register;
        this.printer = printer;
        this.systemCreator = systemCreator;
    }
    /**
     * Initialize a new sale. This method is called before the entire sale process.
     */
    public void initializeSale() {
        sale = new Sale();
    }

    /**
     * The method save the item from the inventory system.
     * @param itemID is the input parameter from user interface when identifying the items.
     * @return null if no such item exists in the inventory system, returns the ItemDTO to string if it's found.
     */
    public ItemDTO scanItem (int itemID) {
        ItemDTO item = systemCreator.getInventorySystem().itemFound(itemID);
        if (item == null) {
            return null;
        } else {
            return item;
        }
    }

    /**
     * The method returns the last added item.
     * @param item is the item scanned.
     * @param quantity is the amount of the same item.
     * @return a string representation of the sold item.
     */
    public String addItem (ItemDTO item, int quantity) {
        sale.addItem(item, quantity);
        return sale.printSoldItemInformation(sale.lastAdded());
    }

    /**
     * The method gets the running total of the sale.
     * @return the running total.
     */
    public double runningTotal() {
        return sale.getRunningTotal();
    }

    public double endSale() {
        return sale.getTotalPriceInclVAT();
    }

    public void applyDiscount(int customerID) {
        sale.discount(customerID);
    }

    public void initializePayment(double amountPaid) {
        sale.pay(amountPaid);
        systemCreator.getInventorySystem().updateInventorySystem(sale.getCurrentSaleList());
        systemCreator.getAccountingSystem().recordTransaction(sale.getCurrentSaleList());
        double change = register.registerPayment(amountPaid, sale);
        sale.getPayment().setChange(change);
        receipt = sale.createReceipt();
        printer.printReceipt(receipt);
    }
}