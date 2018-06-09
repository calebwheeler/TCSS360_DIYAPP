package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A class to hold methods that can be considered basic "utility" methods than can be
 * used throughout the application
 * 
 * @author Caleb Wheeler
 * @author David Guerrero
 * 
 * @version May 21, 2018
 */
public class Utility {

    
    private Utility() {} //helper class don't instantiate
    
    /**
     * Reads the contents of a file, and returns them in a String.
     * 
     * @param FileName
     * @return the string read from the file
     * @throws FileNotFoundException
     * 
     * @author David
     */
    public static String ReadTextFile(String FileName) throws FileNotFoundException {
        String myText = "";
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
        return myText;
    }    
    
    /**
     * load a file that contains a json object and return the json object saved in that file.
     * look at https://www.mkyong.com/java/json-simple-example-read-and-write-json/ 
     * for info on how to parse the json object.
     * 
     * @return the json object
     * 
     * @author Caleb
     */
    public static JSONObject loadJSONFile(String theFilePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(theFilePath));
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if(obj != null) {
            jsonObject = (JSONObject) obj;
        }
        
        return jsonObject;
    }

    /**
     * look at https://crunchify.com/how-to-write-json-object-to-file-in-java/
     * for how to create a json object to be saved.
     * 
     * @author Caleb
     */
    public static void saveJSONFile(JSONObject theObjectToSave, String theFilePath) {
        System.out.println("saving:"+theObjectToSave);
        System.out.println("saving jString:"+theObjectToSave.toJSONString());
        try(FileWriter file = new FileWriter(theFilePath)){
            file.write(theObjectToSave.toJSONString());
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Converts a regular String to html format to be displayed
     * within a Component because certain things such as new line characters
     * will not be displayed properly otherwise.
     * 
     * @param aboutText the text to show 
     * @return returns an html 
     * 
     * @author Caleb
     */
    public static String convertToHTML(String aboutText) {
        return "<html>" + aboutText
                        .replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\n", "<br/>")
                        + "</html>";
    }
    
}
