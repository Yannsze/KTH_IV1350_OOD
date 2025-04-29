package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.Receipt;
import se.kth.iv1350.pointOfSale.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    @Test
    void testPrintReceiptReturnsNonEmptyString() {
        Sale sale = new Sale();
        sale.pay(500);
        Receipt receipt = new Receipt(sale);
        assertFalse(receipt.printReceipt().isEmpty(), "Receipt printout should not be empty.");
    }
}
