package view.panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The home panel that the user will see when they first open the program.
 * 
 * @author Michelle Brown
 * 
 * @version May 28, 2018
 */
public class HomePanel extends JPanel {

    private static final long serialVersionUID = -1648973452334682043L;

    /**
     * Constructor
     * 
     * @author Michelle
     */
    public HomePanel() {
        JLabel imageLabel = new JLabel();
        ImageIcon home_lightsoff = null;
        ImageIcon home_lightson = null;
        try {
            home_lightsoff = new ImageIcon(ImageIO.read(new File("images/house_lightsoff.png")));
        } catch (IOException e) { e.printStackTrace(); }
        imageLabel.setIcon(home_lightsoff);
        try {
            home_lightson = new ImageIcon(ImageIO.read(new File("images/house_lightson.png")));
        } catch (IOException e) { e.printStackTrace(); }
        imageLabel.addMouseListener(new Hover(home_lightsoff, home_lightson));
        this.add(imageLabel);
    }
    
    /**
     * A private MouseAdapter class that helps change an image
     * based on if the mouse is hovering over it or not.
     * 
     * @author Michelle
     */
    private class Hover extends MouseAdapter {

        ImageIcon imageOne;
        
        ImageIcon imageTwo;
        
        public Hover(ImageIcon one, ImageIcon two) {
            imageOne = one;
            imageTwo = two;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel lbl = (JLabel) e.getComponent();
            lbl.setIcon(imageTwo);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel lbl = (JLabel) e.getComponent();
            lbl.setIcon(imageOne);
        }
    }
}