package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;

import static org.junit.jupiter.api.Assertions.*;

public class InventorySystemTest {

    @Test
    void testItemFound() {
        InventorySystem inventory = new InventorySystem();
        ItemDTO item = inventory.itemFound(123);
        assertNotNull(item, "Should find item with ID 123.");
    }

    @Test
    void testItemNotFound() {
        InventorySystem inventory = new InventorySystem();
        ItemDTO item = inventory.itemFound(999);
        assertNull(item, "Should not find item with unknown ID.");
    }
}
