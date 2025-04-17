package se.kth.iv1350.pointOfSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pointOfSale.controller.Controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printOutSale; // store System print as an array to read.
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        Controller contr = new Controller();
        instanceToTest = new View(contr);

        printOutSale = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutSale);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;

        printOutSale = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunInitializeSale() {
        instanceToTest.runInitializeSale();
        String testPrintInitializeSale = printOutSale.toString();
        String expectedOutput = "initialized";
        assertTrue(testPrintInitializeSale.contains(expectedOutput), "UI did not start correctly");
    }
}