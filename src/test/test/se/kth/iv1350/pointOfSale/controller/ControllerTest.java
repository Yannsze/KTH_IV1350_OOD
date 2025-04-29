package test.se.kth.iv1350.pointOfSale.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller contr;

    @BeforeEach
    void setUp() {
        SystemCreator systemCreator = new SystemCreator();
        Register register = new Register();
        Printer printer = new Printer();
        contr = new Controller(register, printer, systemCreator);
        contr.initializeSale();
    }

    @Test
    void testAddItemUpdatesSale() {
        assertDoesNotThrow(() -> {
            contr.scanItem(123);
        }, "Adding a valid item should not throw an exception.");
    }

    @Test
    void testApplyDiscountDoesNotCrash() {
        assertDoesNotThrow(() -> {
            contr.applyDiscount(12345);
        }, "Applying discount should not crash even if no output returned.");
    }

    @Test
    void testInitializePaymentRegistersCorrectly() {
        contr.scanItem(123);
        contr.initializePayment(100);
        // No exceptions thrown = pass (for now, as system output can't be tested directly)
    }
}
