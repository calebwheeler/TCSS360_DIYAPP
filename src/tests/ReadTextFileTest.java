package tests;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import model.Utility;

/**
 * JUnit tests for the class ReadTextFile
 *
 * @author Michelle Brown
 * 
 * @version Apr 29, 2018
 */
public class ReadTextFileTest {

    String validFilename = "testAbout.txt";

    /**
     * Makes sure the method doesn't throw a FileNotFoundException when the file exists
     * 
     * @author Michelle
     */
    @Test
    public void testValidFilename() {
        boolean thrown;
        try {
            Utility.ReadTextFile(validFilename);
            thrown = false;
        }
        catch (FileNotFoundException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }


    String badFilename = "notAbout.txt";

    /**
     * Checks if the method throws FileNotFoundException when given a bad filename
     * 
     * @author Michelle
     */
    @Test//(expected = IOException.class)
    public void testBadFilename() {
        boolean thrown;
        try {
            Utility.ReadTextFile(badFilename);
            thrown = false;
        }
        catch (FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}
