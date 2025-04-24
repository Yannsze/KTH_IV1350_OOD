package se.kth.iv1350.pointOfSale.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A single receipt for one sale.
 */
public class Receipt {
    private Sale sale;
    private ArrayList<SoldItem> soldItems;

    /**
     * This creates an instance of receipt.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
        this.soldItems = sale.getCurrentSaleList();
    }

    public String printReceipt() {
        StringBuilder builder = new StringBuilder();
        builder.append("-----   Receipt   -----\n");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatter = dateTime.format(dateAndTimeFormatter);

        builder.append("Time of Sale: " + formatter + "\n\n");

        for (SoldItem soldItem : soldItems) {
            String name = soldItem.getItem().getName();
            int quantity = soldItem.getItemQuantity();
            double unitPrice = soldItem.getItem().getItemPrice();
            double totalPrice = soldItem.getSubTotal();

            builder.append(String.format("%-25s %2dx %.2f   %.2f SEK\n",
                    name, quantity, unitPrice, totalPrice));
        }

        builder.append(String.format("\n%-20s %18.2f SEK\n", "Total:", sale.getTotalPriceInclVAT()));
        builder.append(String.format("%-20s %18.2f SEK\n", "VAT:", (sale.getTotalPriceInclVAT() - sale.getRunningTotal())));

        if (sale.getTotalDiscount() > 0) {
            builder.append(String.format("%-20s %18.2f SEK\n", "Discount:", -sale.getTotalDiscount()));
            builder.append(String.format("%-20s %18.2f SEK\n", "Total after discount:", sale.getDiscountedTotalPrice()));
        }

        builder.append(String.format("%-20s %18.2f SEK\n", "Cash:", sale.getPayment().getAmountPaid()));
        builder.append(String.format("%-20s %18.2f SEK\n", "Change:", sale.getPayment().getChange()));

        builder.append("----- End receipt -----");

        return builder.toString();
    }
}