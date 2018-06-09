package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Material;
import model.Measurement;
import model.MeasurementType;
import model.Receipt;

/**
 * The Receipt JUnit test.
 * 
 * @author Jim Phan
 * 
 * @version June 5, 2018
 */
public class ReceiptTest {

    /**
     * The objects used for testing.
     */
    private Receipt testReceipt;
    
    private Material testMaterial;
    
    private Measurement testMeasurement;
    
    /**
     * Sets up the Receipt.
     * 
     * @throws Exception
     * 
     * @author Jim
     */
    @Before
    public void setUp() throws Exception {
        testMeasurement = new Measurement(MeasurementType.w_h_d, 1.0, 2.0, 3.0, 0.0);
        testMaterial = new Material("Board", 15.00, testMeasurement, 5);
        testReceipt = new Receipt(testMaterial);
    }

    /**
     * Material to receipt check
     * 
     * @author Jim
     */
    @Test
    public void testReceiptMaterial() {
        Measurement measurement = new Measurement(MeasurementType.w_h_d, 1.0, 2.0, 3.0, 0.0);
        Material material = new Material("Board", 15.00, measurement, 5);
        Receipt receipt = new Receipt(material);
        assertTrue(receipt.getCost() == 15.00 * 5);
        assertTrue(receipt.getTitle().equals(testReceipt.getTitle()));
        assertTrue(receipt.getDate().isEqual(testReceipt.getDate()));
        assertTrue(receipt.getNote().equals(testReceipt.getNote()));
    }

    /**
     * Cost value check
     * 
     * @author Jim
     */
    @Test
    public void testGetCost() {
        Receipt receipt = new Receipt("Test", 2500.00, LocalDate.now().toString(), "note");
        assertTrue(receipt.getCost() == 2500.00);
    }

    /**
     * Constructor check
     * 
     * @author Jim
     */
    @Test
    public void testInputReceiptConstructor() {
        Receipt receipt = new Receipt("Test Title", 300.00, LocalDate.now().toString(), "note!");
        assertTrue(receipt.getTitle().equals("Test Title"));
        assertTrue(receipt.getCost() == 300.00);
        assertTrue(receipt.getDate().isEqual(LocalDate.now()));
        assertTrue(receipt.getNote().equals("note!"));
    }
}
