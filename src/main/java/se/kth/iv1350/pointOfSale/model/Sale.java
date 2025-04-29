package se.kth.iv1350.pointOfSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;

/**
 * One single sale made by one customer, and paid with one payment.
 */
public class Sale {
    private double runningTotal;
    private double VAT;
    private double totalPriceInclVAT;
    private double totalDiscount = 0.0;
    private double discountedTotalPrice = 0.0;
    private DiscountDatabase discountDatabase;
    private Payment payment;
    private ArrayList<SoldItem> currentSaleList;
    private LocalDateTime dateTime;

    /**
     * Creates a new instance of Sale with an empty list, containing items.
     */
    public Sale() {
        currentSaleList = new ArrayList<SoldItem>();
        dateTime = LocalDateTime.now();
        VAT = 0.06;
        this.discountDatabase = new DiscountDatabase();
    }

    /**
     * The method provides access to the current sale list.
     * @return current sale list containing array list of sold items.
     */
    public ArrayList<SoldItem> getCurrentSaleList() {
        return currentSaleList;
    }

    /**
     * Gets the running total of the sale, excluding VAT.
     * @return the running total price without VAT.
     */
    public double getRunningTotal () {
        return this.runningTotal;
    }

    /**
     * Gets the total price including VAT.
     * @return the total price with VAT included.
     */
    public double getTotalPriceInclVAT() {
        return this.totalPriceInclVAT;
    }

    /**
     * Gets the discount database used to calculate discounts.
     * @return the discount database.
     */
    public DiscountDatabase getDiscountDatabase() {
        return this.discountDatabase;
    }

    /**
     * Gets the total discount applied to the sale.
     * @return the total discount amount.
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Gets the total price after all discounts have been applied.
     * @return the discounted total price.
     */
    public double getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }

    /**
     * Gets the payment associated with this sale.
     * @return the payment object.
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Adds an item to the current sale.
     * If the item already exists in the list, its quantity is increased instead of adding a new entry.
     *
     * @param item the item being added to the sale.
     * @param quantity the quantity of the item to add.
     */
    public void addItem (ItemDTO item, int quantity) {
        for (int i = 0; i < currentSaleList.size(); i++) {
            SoldItem soldItem = currentSaleList.get(i);
            if (soldItem.getItem().getItemID() == item.getItemID()) {
                soldItem.increaseQuantity(quantity);
                runningTotal += soldItem.getSubTotal();
                totalPriceInclVAT = runningTotal + runningTotal*VAT;
                return;
            }
        }

        SoldItem newItem = new SoldItem(item, quantity);
        currentSaleList.add(newItem);
        runningTotal += newItem.getSubTotal();
        totalPriceInclVAT = runningTotal + runningTotal*VAT;
    }

    /**
     * Retrieves the last item that was added to the sale.
     * @return the last added sold item.
     */
    public SoldItem lastAdded () {
        return currentSaleList.get(currentSaleList.size() - 1);
    }


    /**
     * Applies discounts to the sale based on customer ID and item-specific rules.
     * @param customerID the ID of the customer eligible for discount.
     */
    public void discount(int customerID) {
        double totalBeforeDiscount = this.runningTotal;
        double customerDiscountRate = discountDatabase.getCustomerDiscount(customerID);
        double customerDiscount = totalBeforeDiscount * customerDiscountRate;

        double itemDiscount = 0.0;
        for (SoldItem soldItem: getCurrentSaleList()) {
            int itemID = soldItem.getItem().getItemID();
            double itemDiscountRate = discountDatabase.getItemDiscount(itemID);
            itemDiscount += soldItem.getSubTotal() * itemDiscountRate;
        }

        double totalPriceDiscountRate = discountDatabase.getTotalDiscount(runningTotal);
        double totalPriceDiscount = runningTotal * totalPriceDiscountRate;

        totalDiscount = customerDiscount + itemDiscount + totalPriceDiscount;
        discountedTotalPrice = totalPriceInclVAT - totalDiscount;
    }

    /**
     * Registers a payment for the sale.
     * @param amountPaid the amount paid by the customer.
     */
    public void pay(double amountPaid) {
        this.payment = new Payment(amountPaid);
    }

    /**
     * Creates a receipt for the completed sale.
     * @return the generated receipt.
     */
    public Receipt createReceipt() {
        return new Receipt(this);
    }

    /**
     * Prints detailed information about a sold item, including its price, VAT, and description.
     * @param soldItem the sold item to print information about.
     * @return a formatted string containing the sold item details.
     */
    public String printSoldItemInformation(SoldItem soldItem) {
        return "Add 1 item with item id: " + soldItem.getItem().getItemID() + "\n" +
                "ItemID: " + soldItem.getItem().getItemID() + "\n" +
                "Item name: " + soldItem.getItem().getName() + "\n" +
                "Item cost: " + String.format("%.2f", soldItem.getItem().getItemPrice()) + " SEK\n" +
                "VAT: " + soldItem.getItem().getVAT() + "\n" +
                "Item description: " + soldItem.getItem().getItemDescription() + "\n\n" +
                "Total cost (incl VAT): " + String.format("%.2f", totalPriceInclVAT) + " SEK\n" +
                "Total VAT: " + String.format("%.2f", soldItem.getVATAmount()) + " SEK\n\n";
    }
}