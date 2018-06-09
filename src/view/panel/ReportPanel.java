package view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Material;
import model.Project;
import model.Receipt;
import model.Utility;

/**
 * The panel that displays a report of all relevant information on the user's current project.
 * 
 * @author Jim Phan
 * 
 * @version May 27, 2018
 */
public class ReportPanel extends JPanel {

    /**
     * Generated serial code.
     */
    private static final long serialVersionUID = -9126931117846577155L;

    /**
     * The currently selected project
     */
    private Project reportProject;
    
    /**
     * The report converted into a String value.
     */
    private String myReport;
    
    private List<Material> reportMaterials;
    
    private List<Receipt> reportReports;
    
    /**
     * The Constructor. Used to set up a report.
     * 
     * @param project The current project selected.
     * 
     * @author Jim
     */
    public ReportPanel(Project project) {
        reportProject = project;
        //Sort the materials and the receipts.
        reportMaterials = reportProject.getMaterials();
        reportReports = reportProject.getReceipts();
        reportMaterials.sort(Material.getComparator());
        reportReports.sort(Receipt.getComparator());
        buildReport();
        JLabel label = new JLabel();
        label.setText(Utility.convertToHTML(toString()));
        add(label);
        add(createExportButton());
    }
    
    /**
     * Returns the String representation of the full project report.
     * 
     * @author Jim
     */
    @Override
    public String toString() {
        return myReport.toString();
    }
    
    /**
     * Method used to build the report's string.
     * 
     * @author Jim
     */
    private void buildReport() {
        if(reportProject == null) {
            myReport = "No project available";
        } else if(reportProject.getReceipts().size() == 0 &&
                        reportProject.getMaterials().size() == 0) {
            myReport = "No information available";
        } else {
            StringBuilder report = new StringBuilder();
            
            report.append("Date: " + reportProject.getDateCreated().toString() + "\n");
            report.append("Project Name: " + reportProject.getTitle() + "\n");

            //Set up the material set.
            if(reportMaterials.size() != 0) {
                report.append("\nMaterials\n");
            }
            Map<String, Material> map = countMaterials();
            for(String material : map.keySet()) {
                report.append(map.get(material).getName() + ": " +
                                map.get(material).getMeasurements() + ", Price: $" +
                                map.get(material).getPrice() * map.get(material).getAmount() + 
                    ", Amount:" + map.get(material).getAmount() + "\n");
            }
            
            //Set up the receipt set.
            int year = 0, month = 0, day = 0;
            for(Receipt entry : reportProject.getReceipts()) {
                LocalDate date = entry.getDate();
                if(date.getYear() != year || date.getMonthValue() != month 
                                || date.getDayOfMonth() != day) {
                    year = date.getYear();
                    month = date.getMonthValue();
                    day = date.getDayOfMonth();
                    report.append("\nDate of Purchase: " + month + "/" 
                                    + day + "/" + year + "\n");
                }
                //report.append(entry.getTitle() + ": $" + entry.getCost() + "\n");
                report.append("Price: $" + entry.getCost() + "\n");
                if(entry.getNote() == null || !entry.getNote().equals("")) {
                    report.append("Note: " + entry.getNote() + "\n");
                }
            }
            
            myReport = report.toString();
        }
    }
    
    /**
     * Helps the set up of the report building with materials.
     * 
     * @return A map of the materials.
     * 
     * @author Jim
     */
    private Map<String, Material> countMaterials() {
        Map<String, Material> map = new HashMap<String, Material>();
        for(Material material : reportMaterials) {
            Material check = map.get(material.getName());
            if(check == null) {
                Material copy = material.clone();
                map.put(material.getName(), copy);
            } else {
                int amount = map.get(material.getName()).getAmount();
                map.get(material.getName()).setAmount(amount + 1);
            }
        }
        return map;
    }
    
    /**
     * Create a JButton used to export the data.
     * 
     * @return A JButton.
     * 
     * @author Jim
     */
    private JButton createExportButton() {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                writeToFile();
            }
        });
        button.setText("Export");
        return button;
    }
    
    /**
     * Helper method to write the file to a text.
     * 
     * @author Jim
     */
    private void writeToFile() {
        String title = reportProject.getTitle();
        if(title != null && !title.equals("")) {
            title += ".txt";
        }
        try {
            System.out.println(title);
            BufferedWriter out = new BufferedWriter(new FileWriter(title));
            out.write(toString());
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
