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

    public double getRunningTotal () {
        return this.runningTotal;
    }

    public double getTotalPriceInclVAT() {
        return this.totalPriceInclVAT;
    }

    public DiscountDatabase getDiscountDatabase() {
        return this.discountDatabase;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }

    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Adds an item to the current sale. If the item already exists, its quantity is increased.
     * @param item is the item being added to the sale.
     * @param quantity the number of units of the item to add.
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
     * The method gets the last item added to the list.
     * @return the sold item of the last added object.
     */
    public SoldItem lastAdded () {
        return currentSaleList.get(currentSaleList.size() - 1);
    }

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

    public void pay(double amountPaid) {
        this.payment = new Payment(amountPaid);
    }

    public Receipt createReceipt() {
        return new Receipt(this);
    }

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