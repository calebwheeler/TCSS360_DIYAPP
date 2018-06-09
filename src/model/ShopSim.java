package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Simulation of a shop that contains materials.
 * Could be replaced by a "real" shop that has materials offered through online service.
 * 
 * @author David Guerrero
 * 
 * @version May 31, 2018
 */
public class ShopSim {
    
    static List<Material> myMaterials = new ArrayList<Material>();
    
    public String myText = "";
 
    private static final String STORE_SAVEFILE = "simstore.CSV";
    
    /**
     * Constructor
     * 
     * @author David
     */
    public ShopSim() {
        try {
            getMaterials(STORE_SAVEFILE);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    
    /**
     * Reads the contents of a file, and stores them in a public variable.
     * 
     * @param FileName
     * @throws FileNotFoundException
     * 
     * @author David
     */
    public static void getMaterials(String FileName) throws FileNotFoundException {
        
        FileReader inputStream = null;
        String[] theLineSplit;

        if (!(new File(FileName)).exists()) {
            throw new FileNotFoundException();
        } else { inputStream = new FileReader(FileName); }

        try {
            BufferedReader bufferedStream = new BufferedReader(inputStream);
            String line = bufferedStream.readLine();
            while ((line = bufferedStream.readLine()) != null) {
                theLineSplit = line.split(",");
                myMaterials.add(new Material(theLineSplit[0],Double.valueOf(theLineSplit[1]),
                                             new Measurement(Double.valueOf(theLineSplit[2]),
                                                             Double.valueOf(theLineSplit[3]),
                                                             Double.valueOf(theLineSplit[4]),
                                                             Double.valueOf(theLineSplit[5]))));
            }
            bufferedStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*for (Material mat: myMaterials) {
            System.out.println(mat.toString());
        }*/
    }
    
    /**
     * 
     * @return the list of materials
     * 
     * @author David
     */
    public List<Material> getMyMaterials() {
        return myMaterials;
    }
    
    /**
     * @author David
     */
    public String toString() {
        String result = "";
        for (Material mat: myMaterials) {
            result += mat.toString();       
        }
        return result;
    }
}
