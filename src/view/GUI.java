package view;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * The graphical user interface used to show the user.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * 
 * @version Apr 26, 2018
 */
public class GUI {
	
    /**
     * The width of the frame.
     */
    private static final int WIDTH = 1000;
    
    /**
     * The height of the frame.
     */
    private static final int HEIGHT = 600;
    
    /**
     * The Title of the Frame.
     */
    private static final String TITLE = "DIY Project";
	
	/**
	 * The JFrame used to display to the user.
	 */
	private JFrame myFrame;
	
	/**
	 * The constructor. Used to starts the frame.
	 * @throws IOException 
	 */
	public GUI() throws IOException {
		myFrame = new MainFrame(WIDTH, HEIGHT);
	}
	
	/**
	 * Initialize the frame and starts the program.
	 */
	public void start() {
		myFrame.setTitle(TITLE);
	}
	
	/**
	 * Ends and dispose of the frame.
	 */
	public void end() {
		myFrame.dispose();
		System.exit(0);
	}
}
