package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.Register;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.model.Sale;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    Register register = new Register();
    Printer printer = new Printer();

    private Sale sale;

    /**
     * Initialize a new sale. This method is called before the entire sale process.
     */
    public void initializeSale() {
        sale = new Sale();
    }

    private int itemQuantity = 1;

    /**
     * The method fecthes data from inventory system.
     * @param inputItemID The variable that is used to identify the item.
     * @param inputItemQuantity The variable that is 1 in default and can be changeble depending on item purchased.
     * @return The method returns a variable itemInformation containing information about the item in the type ItemDTO.
     */
    /*
    public ItemDTO fetchesItemData(int inputItemID, int inputItemQuantity) {
        ItemDTO iteminformation = new ItemDTO();

        return itemInformation;
    }

    public void addAnItem(ItemDTO itemInformation) {

    }

     */
}
