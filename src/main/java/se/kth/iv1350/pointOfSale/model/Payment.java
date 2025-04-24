package se.kth.iv1350.pointOfSale.model;

/**
 * A single payment for one single sale.
 */
public class Payment {
    private double amountPaid;
    private double change;

    /**
     * Creates an instance of one payment.
     *
     * @param amountPaid represent the amount that is paid.
     */
    public Payment(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}