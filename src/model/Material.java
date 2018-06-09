package model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class that holds information about a material a user may choose to add
 * to their project from the shop.
 * 
 * @author David Guerrero
 * @author Jim Phan - added a comparator & encapsulation
 * 
 * @version May 25, 2018
 */
public class Material implements Serializable{

    /**
     * generated serial id.
     */
    private static final long serialVersionUID = -8314551894492343040L;

    private String myName;

    private Double myPrice;

    private Measurement myMeasurement;

    private int myAmount;

    /**
     * Constructor that creates a new Material of amount 1
     * 
     * @param theName
     * @param thePrice
     * @param measurement
     * 
     * @author David
     */
    public Material(String theName, double thePrice, Measurement measurement) {
        this(theName, thePrice, measurement, 1);
    }
    
    /**
     * Constructor that creates a new Material with specified amount
     * 
     * @param theName
     * @param thePrice
     * @param theMeasurement
     * @param theAmount
     * 
     * @author David
     */
    public Material(String theName, double thePrice, Measurement theMeasurement,
                    int theAmount) {
        myName = theName;
        myPrice = thePrice;
        myMeasurement = theMeasurement;
        myAmount = theAmount;
    }

    /**
     * @author David
     */
    public String toString() {
        return  myName + "\nCost = $" + myPrice + "\nMeasurment = " +
                        myMeasurement.toString() + "\n\n";
    }

    /**
     * @return the measurements in String format
     * 
     * @author David
     */
    public String getMeasurements() {
        if(myMeasurement != null) {
            return myMeasurement.toString();
        } else {
            return "";
        }
    }
    
    /**
     * Returns the price of the material.
     * 
     * @return The price of the material.
     * 
     * @author Jim
     */
    public double getPrice() {
        return myPrice;
    }
    
    /**
     * Returns the amount of materials there are.
     * 
     * @return The amount of materials.
     * 
     * @author Jim
     */
    public int getAmount() {
        return myAmount;
    }
    
    /**
     * Set the amount of materials to the object.
     * 
     * @param amount The amount.
     * 
     * @author Jim
     */
    public void setAmount(int amount) {
        myAmount = amount;
    }
    
    /**
     * Gets the total price of all of this material
     * 
     * @return the price of one * the amount
     * 
     * @author David
     */
    public double totalCost() {
        return myPrice * myAmount;
    }
    
    /**
     * @return the name
     * 
     * @author Jim
     */
    public String getName() {
        return myName;
    }
    
    public Material clone() {
        Material clone = new Material(myName, myPrice, myMeasurement, 1);
        return clone;
    }
    
    /**
     * Returns a comparator object used to sort the materials.
     * 
     * @return A comparator object.
     * 
     * @author Jim
     */
    public static Comparator<Material> getComparator() {
        return new MaterialComparator();
    }
    
    /**
     * Utility method used to compare two values with each other.
     * 
     * @param arg0
     * @param arg1
     * @return
     * 
     * @author Jim
     */
    public static int compareTo(Material arg0, Material arg1) {
        //Compare the two names to see if there's a difference.
        int compareString = arg0.getName().compareTo(arg1.getName());
        //If the two strings are the same, check measurements if they're different.
        if(compareString == 0) {
            compareString = arg0.getMeasurements().compareTo(arg0.getMeasurements());
        }
        //If the two measurements are the same, finally check the prices.
        if(compareString == 0) {
            double price1 = arg0.getPrice();
            double price2 = arg1.getPrice();
            if(price1 < price2) {
                compareString = -1;
            } else if(price1 > price2) {
                compareString = 1;
            } else {
                compareString = 0;
            }
        }
        return compareString;
    }
    
    /**
     * The comparator class used to sort Material objects.
     * 
     * @author Jim
     * 
     * @version April 4, 2018
     */
    private static class MaterialComparator implements Comparator<Material> {

        @Override
        public int compare(Material arg0, Material arg1) {
            return Material.compareTo(arg0, arg1);
        }
    }
}
