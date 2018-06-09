package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information for a graph to display receipts based on date.
 * Was going to be implemented, but failed due to issues considering certain aspects of
 * time and uncertainties about the rest of the code.
 * 
 * @author Jim Phan
 * 
 * @version May 24, 2018
 */
public class Graph {
    
    private List<Receipt> graphReceipt;
    
    /**
     * Creates a graph based on a list of receipts
     * 
     * @param receipts
     * 
     * @author Jim
     */
    public Graph(List<Receipt> receipts) {
        graphReceipt = new ArrayList<Receipt>();
        if(receipts != null) {
            for(Receipt entry : receipts) {
                graphReceipt.add(entry);
            }
        }
    }
    
}
