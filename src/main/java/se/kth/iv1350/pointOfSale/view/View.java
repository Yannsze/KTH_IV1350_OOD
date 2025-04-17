package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;

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

    public void runInitializeSale() {
        contr.initializeSale();
        System.out.println("A new sale has been initialized.");
    }
}
