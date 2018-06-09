package view.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Receipt;

/**
 * The JPanel used to display the graph.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * 
 * @version May 24, 2018
 */
public class GraphPanel extends JPanel {

    /**
     * Generated serial code.
     */
    private static final long serialVersionUID = 5701627559948147299L;
    
    private List<Receipt> graphReceipts;
    
    /**
     * Constructor.
     * 
     * @param size
     * @param receipts
     * 
     * @author Jim
     */
    public GraphPanel(Dimension size, List<Receipt> receipts) {
        int width = (int) (size.width * 0.9);
        int height = (int) (size.height * 0.9);
        this.setPreferredSize(new Dimension(width, height));
        if(receipts == null || receipts.size() < 2) {
            
        } else {
            graphReceipts = new ArrayList<Receipt>();
            for(Receipt entry : receipts) {
                graphReceipts.add(entry);
                
            }
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
    }
}
