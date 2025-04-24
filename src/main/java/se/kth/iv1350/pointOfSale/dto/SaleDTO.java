package se.kth.iv1350.pointOfSale.dto;

/**
 * Continas information about the sale.
 */
public class SaleDTO {
    private final double totalPrice;
    private final double taxRate;
    private final double change;
    private final double amountPaid;

    /**
     * Creates a new instance of one entire sale.
     * @param totalPrice The total price shows the accumulated price of the entire sale.
     * @param taxRate The tax rate is the procentage applied to the entire sale.
     * @param change The change is the amount that need to be given back to the customer.
     * @param amountPaid The amount paid by the customer, in cash.
     */
    public SaleDTO(double totalPrice, double taxRate, double change
            , double amountPaid) {
        this.totalPrice = totalPrice;
        this.taxRate = taxRate;
        this.change = change;
        this.amountPaid = amountPaid;
    }

    /**
     * Get the total price of the sale.
     * @return total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Get the tax rate of the sale.
     * @return tax rate in procentage.
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Get the change.
     * @return the change.
     */
    public double getChange() {
        return change;
    }

    /**
     * Retrieves the amount that is paid.
     * @return amount paid, in cash.
     */
    public double getAmountPaid() {
        return amountPaid;
    }
}
