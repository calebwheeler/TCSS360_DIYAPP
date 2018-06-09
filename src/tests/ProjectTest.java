package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sun.accessibility.internal.resources.accessibility;

import model.Material;
import model.Measurement;
import model.Project;
import model.Receipt;

/**
 * The JUnit test for the Project class.
 * 
 * @author Michelle Brown
 * 
 * @version June 7, 2018
 */
public class ProjectTest {

    Project testProject = new Project("Test Project");
    
    private List<Material> testMaterials;
    
    private List<Receipt> testReceipts;
    
    /**
     * Sets up the Project
     * 
     * @throws Exception
     * 
     * @author Michelle
     */
    @Before
    public void setUp() throws Exception {
        testMaterials = new ArrayList<Material>();
        testReceipts = new ArrayList<Receipt>();
        //Add materials
        testMaterials.add(new Material("Board", 15.00, new Measurement(1.0, 2.0, 3.0, 0.0)));
        testMaterials.add(new Material("Tile", 10.00, new Measurement(4.0, 3.0, 1.0, 0.0)));
        //Add receipts
        testReceipts.add(new Receipt("Planks", 19.99, new Date(), "Hardware store purchase"));
        testReceipts.add(new Receipt("Nails", 2.99, new Date(), "Hardware store purchase"));
    }

    /**
     * Project String check
     * 
     * @author Michelle
     */
    @Test
    public void testProjectString() {
        testProject.addMaterial(testMaterials.get(0));
        testProject.addReceipt(testReceipts.get(0));
        String projectString = testProject.toString();
        String string = "Title: " + testProject.getTitle() + " Material: " + testMaterials.get(0) +
                        " Receipt: " + testReceipts.get(0);
        assertTrue(projectString.equals(string));
    }

    /**
     * Title value check
     * 
     * @author Michelle
     */
    @Test
    public void testGetTitle() {
        String title = "This Project";
        Project newProject = new Project(title);
        assertEquals(newProject.getTitle(), title);
    }

    /**
     * Checks to see if materials returned are the ones it was given
     * 
     * @author Michelle
     */
    @Test
    public void testGetMaterials() {
        testProject.addMaterial(testMaterials.get(0));
        testProject.addMaterial(testMaterials.get(1));
        List<Material> theMaterials = testProject.getMaterials();
        for (int i = 0; i < theMaterials.size(); i++) {
            assertEquals(testMaterials.get(i), theMaterials.get(i));
        }
    }

    /**
     * Checks to see if receipts returned are the ones it was given
     * 
     * @author Michelle
     */
    @Test
    public void testGetReceipts() {
        testProject.addReceipt(testReceipts.get(0));
        testProject.addReceipt(testReceipts.get(1));
        List<Receipt> theReceipts = testProject.getReceipts();
        for (int i = 0; i < theReceipts.size(); i++) {
            assertEquals(testReceipts.get(i), theReceipts.get(i));
        }
    }

    /**
     * Check if the title was changed
     * 
     * @author Michelle
     */
    @Test
    public void testChangeProjectTitle() {
        String title = "This Project";
        testProject.changeProjectTitle(title);
        assertEquals(testProject.getTitle(), title);
    }

    /**
     * Check if material was removed
     * 
     * @author Michelle
     */
    @Test
    public void testRemoveMaterial() {
        Material materialPointer = testMaterials.get(0);
        testProject.addMaterial(testMaterials.get(0));
        testProject.removeMaterial(testMaterials.get(0));
        List<Material> theMaterials = testProject.getMaterials();
        for (int i = 0; i < theMaterials.size(); i++) {
            assertNotEquals(theMaterials.get(i), materialPointer);
        }
    }
    
    /**
     * Check if material was added
     * 
     * @author Michelle
     */
    @Test
    public void testAddMaterial() {
//      Material material = new Material("Paint", 15.00, new Measurement(0.0, 0.0, 0.0, 16.0));
//      testProject.addMaterial(material);
//      testProject.addReceipt(new Receipt(material));
        testProject.addMaterial(testMaterials.get(0));
        Material theMaterial = testProject.getMaterials().get(0);
        assertEquals(testMaterials.get(0).getAmount(), theMaterial.getAmount());
        assertEquals(testMaterials.get(0).getMeasurements(), theMaterial.getMeasurements());
        assertEquals(testMaterials.get(0).getName(), theMaterial.getName());
        assertTrue(testMaterials.get(0).getPrice() == theMaterial.getPrice());
    }

    /**
     * Check if receipt was removed
     * 
     * @author Michelle
     */
    @Test
    public void testRemoveReceipt() {
        Receipt receiptPointer = testReceipts.get(0);
        testProject.addReceipt(testReceipts.get(0));
        testProject.removeReceipt(testReceipts.get(0));
        List<Receipt> theReceipts = testProject.getReceipts();
        for (int i = 0; i < theReceipts.size(); i++) {
            assertNotEquals(theReceipts.get(i), receiptPointer);
        }
    }

    /**
     * Check if receipt was added
     * 
     * @author Michelle
     */
    @Test
    public void testAddReceipt() {
        testProject.addReceipt(testReceipts.get(0));
        Receipt theReceipt = testProject.getReceipts().get(0);
        assertEquals(testReceipts.get(0).getCost(), theReceipt.getCost());
        assertEquals(testReceipts.get(0).getDate(), theReceipt.getDate());
        assertEquals(testReceipts.get(0).getNote(), theReceipt.getNote());
        assertEquals(testReceipts.get(0).getTitle(), theReceipt.getTitle());
    }

    /**
     * Check if the total cost was calculated correctly
     * 
     * @author Michelle
     */
    @Test
    public void testEstimateTotal() {
        testProject.addMaterial(testMaterials.get(0));
        testProject.addMaterial(testMaterials.get(1));
        testProject.addReceipt(testReceipts.get(0));
        testProject.addReceipt(testReceipts.get(1));
        double cost = testMaterials.get(0).getPrice() + testMaterials.get(1).getPrice() +
                        testReceipts.get(0).getCost() + testReceipts.get(1).getCost();
        assertTrue(testProject.estimateTotal() == cost);
    }

}
