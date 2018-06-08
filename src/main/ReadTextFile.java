package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The main method that runs the program.
 *
 * @author TODO fill in_________
 * 
 * @version Apr 29, 2018 <--- ?? TODO fill in_________
 */
public class ReadTextFile {

    public String myText = "";

    /**
     * Reads the contents of a file, and stores them in a public variable.
     * 
     * @param FileName
     * @throws FileNotFoundException
     */
    public ReadTextFile(String FileName) throws FileNotFoundException {
        FileReader inputStream = null;

        if (!(new File(FileName)).exists()) {
            throw new FileNotFoundException();
        } else { inputStream = new FileReader(FileName); }

        try {
            BufferedReader bufferedStream = new BufferedReader(inputStream);
            String line;
            while ((line = bufferedStream.readLine()) != null) {
                myText += line + "\n";
            }
            bufferedStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
