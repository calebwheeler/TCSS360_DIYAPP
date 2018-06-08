package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Material;
import model.Measurement;

public class MaterialTest {

    @Before
    public void setUp() throws Exception {
        
    }

    /**
     * @author David Guerrero
     */
    @Test
    public void testTotalCost() {
        Material mat = new Material("test",5.00, new Measurement(0,0,0,0));
        // this makes sure the constructor worked
        assertTrue(mat.getName().equals("test"));
        assertTrue(mat.getPrice() == 5.00);
        assertTrue(5.00 == mat.totalCost());
        assertTrue(1 == mat.getAmount());

        mat.setAmount(3);
        // check that changing myAmount is working and the new total cost
        assertTrue(15.00 == mat.totalCost());
        assertTrue(3 == mat.getAmount());
        
    }

    @Test
    public void testGetName() {
        fail("Not yet implemented");
    }

}
