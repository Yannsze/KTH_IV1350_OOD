package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.model.SoldItem;

import java.util.ArrayList;

/**
 * This is the external inventory system that stores information about items. Name, ID, description + (tax rate)
 * and price.
 */
public class InventorySystem {

    ItemDTO itemOne = new ItemDTO("Bigwheel Oatmeal", 123,
            "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free", 29.90, 0.06);
    ItemDTO itemTwo = new ItemDTO("YouGoGo Blueberry", 456,
            "YouGoGo Blueberry 240 g, low sugar youghurt, blueberry flavour", 14.90, 0.06);

    private final ItemDTO[] itemList = {itemOne, itemTwo};

    /**
     * The method looks through number of items in the inventory system and check if it's in the database.
     * @param itemIdentity takes the input parameter of the item from user interface.
     * @return the itemDTO if the item is found, else null.
     */
    public ItemDTO itemFound (int itemIdentity) {
        for (ItemDTO item: itemList) {
            if (itemIdentity == item.getItemID()) {
                return item;
            }
        }
        return null;
    }

    public void updateInventorySystem(ArrayList<SoldItem> soldItems) {

    }
}