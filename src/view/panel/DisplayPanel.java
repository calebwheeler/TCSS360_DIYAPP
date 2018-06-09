package view.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * The JPanel class used to display different panels.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * 
 * @version May 17, 2018
 */
public class DisplayPanel extends JPanel {

    /**
     * generated serial id.
     */
    private static final long serialVersionUID = -641610218954440272L;

    /**
     * Constructor
     * 
     * @param color
     * @param dimension
     * 
     * @author Jim
     */
    public DisplayPanel(Color color, Dimension dimension) {
        this.setBackground(color);
        int width = (int) (dimension.width * 0.85);
        int height = (int) (dimension.height);
        //int width = 500, height = 500;
        this.setPreferredSize(new Dimension(width, height));
    }
}
