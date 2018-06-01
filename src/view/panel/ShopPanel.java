package view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import model.Material;
import model.Project;
import model.ProjectManager;
import model.ShopSim;
import model.Utility;

/**
 * 
 * @author David
 */
public class ShopPanel extends JPanel {

    /**
     * Serial code of the class.
     */
    private static final long serialVersionUID = 7999942536513877098L;
    
    /**
     * Used to set the frame's title.
     */
    public static final String FRAME_TITLE = "Shop Sim";
    
    /**
     * Takes in text to display in a panel and a minimum size the panel can be displayed in.
     * @param aboutText the text to display
     * @param width minimum width of the panel
     * @param height minimum height of the panel
     * @author David
     * @author caleb
     */
    public ShopPanel(ShopSim testStore) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameDimension = new Dimension((int)(size.getWidth() * .75),
                                       (int)(size.getHeight() * .75));
        this.setPreferredSize(new Dimension((int)(frameDimension.getWidth()*(1-.10*1.4)) + 30,
                                            (int)(frameDimension.getHeight()*(.75) + 120)));
        
        String materialText;
        this.setLayout(new GridLayout(1,1));
        JPanel materialsScrollPanel = new JPanel();
        materialsScrollPanel.setBorder(BorderFactory.createBevelBorder(0));
        JPanel materialsDisplayPanel = new JPanel(new GridLayout(0,2));
        JScrollPane materialScrollPane = new JScrollPane(materialsScrollPanel,
                                                         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        for (Material mat: testStore.getMyMaterials()) {
            materialText = Utility.convertToHTML(mat.toString());
            JButton btnMaterial = new JButton("ADD");
            btnMaterial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer index = ProjectManager.getCurrentProjectIndex();
                    Project p = ProjectManager.getProject(index);
                    p.addMaterial(mat);
                    ProjectManager.updateProject(index, p.getTitle(), p.getMaterials(), p.getReceipts());
                }
                
            });
            JPanel materialPanel = new JPanel(new GridLayout(1,0));
            JLabel matLabel = new JLabel(materialText);
            materialPanel.add(matLabel);      
            materialPanel.add(btnMaterial);
            materialsDisplayPanel.add(materialPanel);
        }
        materialsScrollPanel.add(materialsDisplayPanel);
        this.add(materialScrollPane);
    }
    
}

