package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaterialTest {

    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void testTotalCost() {
        Material mat = new Material("test",5.00, new Measurement(0,0,0,0));
        // this makes sure the constructor worked
        assertTrue(mat.myName.equals("test"));
        assertTrue(mat.myPrice == 5.00);
        assertTrue(5.00 == mat.totalCost());
        assertTrue(1 == mat.myAmount);

        mat.myAmount = 3;
        // check that changing myAmount is working and the new total cost
        assertTrue(15.00 == mat.totalCost());
        assertTrue(3 == mat.myAmount);
        
    }

    @Test
    public void testGetName() {
        fail("Not yet implemented");
    }

}
