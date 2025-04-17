package se.kth.iv1350.pointOfSale.dto;

/**
 * Contains information about one specific item.
 */
public class ItemDTO {
    private final String name;
    private final int itemID;
    private final int itemQuantity;
    private final String itemDescription;
    private final int itemPrice;

    /**
     * Creates a new instance representing an item.
     * @param name The name of the item, e.g. <code>Potatoe</code>
     * @param itemID The identifying code for the item.
     * @param itemQuantity The count/amount of the item that is being purchased.
     * @param itemDescription The descriptive information about the item.
     * @param itemPrice The price of the item.
     */
    public ItemDTO(String name, int itemID, int itemQuantity, String itemDescription, int itemPrice){
        this.name = name;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    boolean matches(ItemDTO itemFound) {
        if(itemFound.getName() != null && !itemFound.getName().equals(name)) {
            return false;
        }
        if(!(itemFound.getItemID() < 0) && itemFound.getItemID() != itemID) {
            return false;
        }
        if(!(itemFound.getItemQuantity() < 0) && itemFound.getItemQuantity() != itemQuantity) {
            return false;
        }
        if(itemFound.getItemDescription() != null && !itemFound.getItemDescription().equals(itemDescription)) {
            return false;
        }
        if(!(itemFound.getItemPrice() < 0) && itemFound.getItemPrice() != itemPrice) {
            return false;
        }
        return true;
    }

    /**
     * Method retrieves the name of one item.
     * @return Method returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method retrieves the identify code of one item.
     * @return Method returns the itemID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Method retrieves the amount / count of one item.
     * @return Method returns the itemQuantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Method retrieves the description of one item.
     * @return Method returns the itemDescription.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Method retrieves the price of one item.
     * @return Method returns the item price.
     */
    public int getItemPrice() {
        return itemPrice;
    }
}
