package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Main frame the user sees. It houses the home screen and its buttons.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Apr 26, 2018
 */
public class MainFrame extends JFrame {

	/**
	 * Serial code of the class.
	 */
	private static final long serialVersionUID = -7259295803716311757L;

	/**
	 * The JPanel that's displayed to the user.
	 */
	private JPanel displayPanel;
	
	/**
	 * The JPanel that handles the change of the display panel.
	 */
	private JPanel dynamicPanel;
	
	/**
	 * The constructor. Initialize the values of the frame and sets up the panels.
	 * 
	 * @param width Width of the frame.
	 * @param height Height of the frame.
	 */
	public MainFrame(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(new Dimension(width, height));
		dynamicPanel = new JPanel();
		dynamicPanel.setSize(new Dimension(width, height));
		displayPanel = new JPanel(); //Replace with Caleb's code.
		displayPanel.setSize(new Dimension(width, height));
		add(dynamicPanel);
		dynamicPanel.add(displayPanel);
	}
}