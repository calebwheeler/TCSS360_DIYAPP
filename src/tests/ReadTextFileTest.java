package tests;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import main.ReadTextFile;

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
     */
    @Test
    public void testValidFilename() {
        boolean thrown;
        try {
            new ReadTextFile(validFilename);
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
     */
    @Test//(expected = IOException.class)
    public void testBadFilename() {
        boolean thrown;
        try {
            new ReadTextFile(badFilename);
            thrown = false;
        }
        catch (FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}
