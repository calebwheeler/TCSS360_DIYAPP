package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

/**
 * Everything the user purchases for a project can be made into a receipt.
 * A user can get a receipt from a Material they purchased in the shop,
 * or they can enter the information from an item purchased elsewhere.
 * 
 * @author Michelle Brown
 * @author Jim Phan - added a comparator & encapsulation
 * 
 * @version May 29, 2018
 */
public class Receipt implements Serializable {
    
    /**
     * generated serial id.
     */
    private static final long serialVersionUID = -8491951796110188642L;

    private String myTitle;

    private double myCost;
    
    private LocalDate myDate;
    
    private String myNote;
    
    /**
     * Constructor for a Material's receipt bought in the shop.
     * 
     * @param material
     */
    public Receipt(Material material) {
        myTitle = material.getName();
        myCost = material.totalCost();
        myDate = LocalDate.now();
        myNote = "measurement: " + material.getMeasurements() +
                        "\nquantity: " + myCost;
    }
    
    /**
     * Constructor for something that was bought outside of the shop.
     * 
     * @param title
     * @param cost
     * @param date
     * @param note
     * 
     * @author Caleb
     */
    public Receipt(String title, double cost, Date date, String note) {
        this(title, cost, new SimpleDateFormat("MM/dd/yyyy").format(date), note);
    }
    
    /**
     * Constructor for user inputting a material
     * not bought in the program's store feature.
     * 
     * @param cost
     * @param note
     * 
     * @author Michelle
     */
    public Receipt(String title, double cost, String date, String note) {
        //date in the form MM/dd/yyyy
        myTitle = title;
        myCost = cost;
        myDate = LocalDate.now().minusDays(0/*days to subtract to get the actual time purchased.
        should use the string passed in to determine how long ago it was*/);
        //TODO figure this out
        myNote = note;
    }
    
    /**
     * @return the cost of the receipt
     * 
     * @author Jim
     */
    public Double getCost() {
        return myCost;
    }
    
    /**
     * @return the title of the receipt
     * 
     * @author Jim
     */
    public String getTitle() {
        return myTitle;
    }
    
    /**
     * Return the date of the receipt.
     * 
     * @return The date of the receipt.
     * 
     * @author Jim
     */
    public LocalDate getDate() {
        return myDate;
    }
    
    /**
     * Return the note of the receipt.
     * 
     * @return the note of the receipt.
     * 
     * @author Jim
     */
    public String getNote() {
        return myNote;
    }
    
    public String toString() {
        return "Material: title\n cost: " + myCost + "\n date purchased: " +
                        myDate.toString() + "\n note:\n" + myNote;
    }
    
    /**
     * Returns a comparator used to sort the receipts.
     * 
     * @return A comparator used to sort the receipts.
     * 
     * @author Jim
     */
    public static Comparator<Receipt> getComparator() {
        return new ReceiptComparator();
    }
    
    /**
     * Comparator class used to compare the Receipts.
     * 
     * @author Jim
     * 
     * @version 6/4/2018
     */
    private static class ReceiptComparator implements Comparator<Receipt> {
        @Override
        public int compare(Receipt o1, Receipt o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}