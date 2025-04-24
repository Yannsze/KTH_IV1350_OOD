package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;

/**
 * This is placeholder for real view, the user interface of the program. It's not
 * required to design the view for this task. It only contains execution with calls to all system operations
 * in controller.
 */
public class View {
    private Controller contr;
    /**
     * Creates a new instance, that uses the controller for all calls to other layers.
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * A fake user face execution to the user interface that signals a sale has initialized.
     */
    public void runInitializeSale() {
        contr.initializeSale();
        System.out.println("A new sale has been initialized.");
        ItemDTO item = contr.scanItem(23456);

        item = contr.scanItem(123);
        System.out.println(contr.addItem(item, 1));
        item = contr.scanItem(456);
        System.out.println(contr.addItem(item, 1));
        item = contr.scanItem(987);
        item = contr.scanItem(23456);

        contr.applyDiscount(12345);
        contr.initializePayment(100);

    }
}