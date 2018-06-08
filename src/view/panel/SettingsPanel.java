package view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.Setting;
import model.Utility;

/**
 * Panel to display the current user settings. Gives an option to change them
 * and export to file to sync to other devices at a later point.
 * 
 * @author Michelle Brown
 * 
 * @version May 21, 2018
 */
public class SettingsPanel extends JPanel {

    /**
     * Serial code of the class.
     */
    private static final long serialVersionUID = 7580098800162278826L;

    /**
     * Used to set the frame's title.
     */
    public static final String FRAME_TITLE = "Settings";

    /**
     * The user's settings recovered from a local json file
     */
    Setting userSettings;

    /**
     * The user's saved name
     */
    private String name;

    /**
     * The user's saved email
     */
    private String email;



    /**
     * The constructor that creates the panel where the user can
     * see all of their custom user settings
     * 
     * @param size
     */
    public SettingsPanel (Dimension size) {
        this.setLayout(new BorderLayout());
        userSettings = new Setting();
        name = userSettings.myFirstName;
        email = userSettings.myEmail;
        //CURRENT SETTINGS
        String text = "CURRENT SETTINGS\n\nName: ";
        StringBuilder sb = new StringBuilder(text);
        sb.append(name);
        sb.append("\nEmail: ");
        sb.append(email);
        sb.append("\n\n\n\n");
        //needs to be converted to HTML to display new lines
        text = Utility.convertToHTML(sb.toString());
        JLabel savedSettings = new JLabel(text);
        this.add(savedSettings,BorderLayout.NORTH);
        //NEW SETTINGS
        JPanel newSettings = new JPanel(new BorderLayout());
        newSettings.add(new JLabel(Utility.convertToHTML("CHANGE SETTINGS\n\n")), BorderLayout.NORTH);
        JTextField nameField = new JTextField(name, 10);
        newSettings.add(nameField, BorderLayout.CENTER);
        JTextField emailField = new JTextField(userSettings.myEmail, 10);
        newSettings.add(emailField, BorderLayout.SOUTH);
        this.add(newSettings, BorderLayout.CENTER);
        //SAVE BUTTON
        JButton saveButton = new JButton("save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSettings.saveSettings(nameField.getText(), emailField.getText());
            }
        });
        this.add(saveButton,BorderLayout.SOUTH);
        this.setBorder(new LineBorder(Color.BLACK));
    }

}
