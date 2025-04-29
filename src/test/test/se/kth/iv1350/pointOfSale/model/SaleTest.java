package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale sale;
    private ItemDTO item;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item = new ItemDTO("Test Item", 111, "Test description", 10.0, 0.06);
    }

    @Test
    void testAddItemUpdatesRunningTotal() {
        sale.addItem(item, 2);
        assertTrue(sale.getRunningTotal() > 0, "Running total should increase after adding items.");
    }

    @Test
    void testDiscountCalculation() {
        sale.addItem(item, 2);
        sale.discount(12345);
        assertTrue(sale.getDiscountedTotalPrice() < sale.getTotalPriceInclVAT(), "Discount should lower total price.");
    }

    @Test
    void testPayCreatesPaymentObject() {
        sale.pay(500);
        assertNotNull(sale.getPayment(), "Payment object should not be null after paying.");
    }

    @Test
    void testCreateReceiptNotNull() {
        assertNotNull(sale.createReceipt(), "Receipt should not be null after sale creation.");
    }
}
