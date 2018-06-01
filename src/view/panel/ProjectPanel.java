package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import model.Material;
import model.Project;
import model.ProjectManager;
import model.Receipt;
import view.MainFrame;
import view.MainFrame.PAGE;

public class ProjectPanel extends JPanel implements ActionListener{

    /**
     * generated serial id.
     */
    private static final long serialVersionUID = 6230144897462569298L;

    private enum COMMAND {
        EDIT_PROJECT, EXISTING_PROJECT, MATERIALS, RECEIPTS, SAVE_PROJECT_EDIT, CLOSE_PANEL,
        PREFIX_EDIT_EXISTING_PROJECT, PREFIX_SELECT_PROJECT, PREFIX_REMOVE_MATERIAL,
        PREFIX_REMOVE_EXISTING_PROJECT, PREFIX_REMOVE_RECEIPT, PREFIX_ADD_MATERIAL/*for future button*/,
        PREFIX_ADD_RECEIPT;

        //return the command with the same name as the actionCommand or null
        public static COMMAND getCommand(String actionCommand) {
            for(COMMAND c :COMMAND.values()) {
                if(c.name() == actionCommand) {
                    return c;
                }
            }

            System.err.println("In ProjectPanel.java\nInvalid action command:" + actionCommand);
            return null;
        }
    }

    private JPanel displayPanel;


    /**
     * base panel to hold the display panels.
     * @param panelDimensions
     * @author caleb
     */
    public ProjectPanel(Dimension panelDimensions) {
        displayPanel = new ProjectAskPanel(this);
        this.setPreferredSize(panelDimensions);
        this.setLayout(new BorderLayout());

        this.add(displayPanel, BorderLayout.CENTER);
    }

    /**
     * panel for asking the user if they want a new project or existing project
     * @author caleb
     *
     */
    private class ProjectAskPanel extends JPanel {
        /**
         * generated serial id.
         */
        private static final long serialVersionUID = 3612278017012099089L;

        /**
         * panel to ask user if they want to use an existing or create a new project.
         * @param projectPanel
         * @author caleb
         * @author david add current project
         */
        private ProjectAskPanel(ProjectPanel projectPanel) {
            //create new project button
            JButton btnNewProject = new JButton("New Project");
            btnNewProject.setActionCommand(COMMAND.EDIT_PROJECT.name());
            btnNewProject.addActionListener(projectPanel);

            //create existing project button
            JButton btnExistingProject = new JButton("Existing Project");
            btnExistingProject.setActionCommand(COMMAND.EXISTING_PROJECT.name());
            btnExistingProject.addActionListener(projectPanel);

            //add buttons
            this.add(btnNewProject,BorderLayout.WEST);
            this.add(btnExistingProject, BorderLayout.EAST);
            
//            if (ProjectManager.getCurrentProjectIndex() != null ) {
//                JPanel test = new JPanel();
//                test.add(new ProjectEditPanel(projectPanel,ProjectManager.getProject(ProjectManager.getCurrentProjectIndex())));
//                this.add(test);
//            }

        }
    }

    /**
     * this panel allows viewing all receipts for the current project and removing receipts from the project.
     * @author caleb
     * @author Michelle
     */
    private class ProjectReceiptsPanel extends JPanel {

        /**
         * generated serial id.
         */
        private static final long serialVersionUID = 6567097730531420719L;

        private ProjectReceiptsPanel(ProjectPanel projectPanel, Project theProject) {
            //JPanel panel = new JPanel(new GridLayout(0,1));

            this.setLayout(new GridLayout(0,1));
            
            JPanel receiptsScrollPanel = new JPanel();
            receiptsScrollPanel.setBorder(BorderFactory.createBevelBorder(0));
            JPanel receiptsDisplayPanel = new JPanel(new GridLayout(0,1));
            JScrollPane receiptsScrollPane = new JScrollPane(receiptsScrollPanel,
                                                             JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            if(theProject != null) {
                List<Receipt> recs = theProject.getReceipts();
                for(Receipt rec : recs) {
                    String recText = rec.toString();
                    JButton btnRemove = new JButton("Remove");
                    btnRemove.addActionListener(projectPanel);
                    btnRemove.setActionCommand(COMMAND.PREFIX_REMOVE_RECEIPT.name() + recText);

                    JLabel recLabel = new JLabel(recText);
                    JPanel receiptPanel = new JPanel(new GridLayout(1,0));
                    receiptPanel.add(recLabel);
                    receiptPanel.add(btnRemove);
                    receiptsDisplayPanel.add(receiptPanel);
                }
            }
            receiptsScrollPanel.add(receiptsDisplayPanel);
            this.add(receiptsScrollPane);
            
            //Michelle's code
            JButton addReceipt = new JButton("Add Receipt");
            addReceipt.setActionCommand(COMMAND.PREFIX_ADD_RECEIPT.name());
            addReceipt.addActionListener(projectPanel);
            JPanel receiptOperations = new JPanel();
            receiptOperations.add(addReceipt);
            this.add(receiptOperations);
        }

    }
    
    /**
     * The panel that enables a user to add a new receipt to their project.
     * 
     * @author Michelle
     */
    private class AddReceiptPanel extends JPanel {
        
        private static final long serialVersionUID = -1659508405672285218L;

        private AddReceiptPanel() {
            this.setLayout(new BorderLayout());
            JPanel fieldsPanel = new JPanel();
            fieldsPanel.setBorder(BorderFactory.createBevelBorder(0));
            fieldsPanel.setLayout(new GridLayout(14,0));
            
            //TITLE
            JLabel titleLabel = new JLabel("Name of Item:");
            fieldsPanel.add(titleLabel);
            JTextField titleField = new JTextField("", 10);
            fieldsPanel.add(titleField);
            
            //COST
            JLabel costLabel = new JLabel("Price:");
            fieldsPanel.add(costLabel);
            JPanel dollarAndCents = new JPanel();
            dollarAndCents.add(new JLabel("$"), BorderLayout.WEST);
            JTextField dollarField = new JTextField("", 8);
            dollarAndCents.add(dollarField, BorderLayout.CENTER);
            JTextField centField = new JTextField("", 2);
            dollarAndCents.add(centField, BorderLayout.EAST);
            fieldsPanel.add(dollarAndCents);
            
            //DATE
            JLabel dateLabel = new JLabel("Date:"); //adding a custom date doesn't actually work yet
            fieldsPanel.add(dateLabel);
            JPanel datePanel = new JPanel();
            JTextField monthField = new JTextField("mm", 2);
            datePanel.add(monthField);
            datePanel.add(new JLabel("/"));
            JTextField dayField = new JTextField("dd", 2);
            datePanel.add(dayField);
            datePanel.add(new JLabel("/"));
            JTextField yearField = new JTextField("yyyy", 4);
            datePanel.add(yearField);
            fieldsPanel.add(datePanel);
            
            //NOTE
            JLabel noteLabel = new JLabel("Note:");
            fieldsPanel.add(noteLabel);
            JTextField noteField = new JTextField("", 30);
            fieldsPanel.add(noteField);
            
            //SAVE BUTTON
            JButton saveButton = new JButton("save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    Receipt newReceipt = new Receipt(titleField.getText(), Double.parseDouble(dollarField.getText())
                                             + Double.parseDouble(centField.getText())/100, //do exception handling
                                             monthField.getText() + "/" + dayField.getText() + "/" + yearField.getText(),
                                             noteField.getText());
                    Project p = ProjectManager.getProject(ProjectManager.getCurrentProjectIndex());
                    p.addReceipt(newReceipt);
                    ProjectManager.updateProject(ProjectManager.getCurrentProjectIndex(), p);
                    ProjectManager.saveProjects();
                    openReceiptsPage();
                }
            });
            
          this.add(new JLabel("ENTER INFORMATION FOR YOUR RECEIPT"), BorderLayout.NORTH);
          this.add(fieldsPanel, BorderLayout.CENTER);
          this.add(saveButton, BorderLayout.SOUTH);
        }
    }

    /**
     * this panel allows viewing all materials for the current project and removing materials from the project.
     * @author caleb
     *
     */
    private class ProjectMaterialsPanel extends JPanel {
        /**
         * generated serial id.
         */
        private static final long serialVersionUID = -2980969761153265743L;

        private ProjectMaterialsPanel(ProjectPanel projectPanel, Project theProject) {

            //JPanel panel = new JPanel(new GridLayout(0,1));
            this.setLayout(new GridLayout(1,1));
            JPanel materialsScrollPanel = new JPanel();
            //materialsScrollPanel.setBorder(BorderFactory.createBevelBorder(0));
            JPanel materialsDisplayPanel = new JPanel(new GridLayout(0,1));
            JScrollPane materialScrollPane = new JScrollPane(materialsScrollPanel,
                                                             JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            //if this is an existing project display all of its materials
            if(theProject != null) {
                List<Material> mats = theProject.getMaterials();
                for(Material mat : mats) {
                    String matText = mat.getName() +" $" + mat.totalCost() + " ";

                    JButton btnRemove = new JButton("Remove");
                    btnRemove.addActionListener(projectPanel);
                    btnRemove.setActionCommand(/*"_R"*/COMMAND.PREFIX_REMOVE_MATERIAL.name() + mat.toString());

                    JLabel matLabel = new JLabel(matText);
                    JPanel materialPanel = new JPanel(new GridLayout(1,0));
                    materialPanel.add(matLabel);
                    materialPanel.add(btnRemove);
                    materialsDisplayPanel.add(materialPanel);
                }
            }
            materialsScrollPanel.add(materialsDisplayPanel);
            this.add(materialScrollPane);
            //TODO add button to add materials that directs to shop
        }
    }

    /**
     * panel for editing an existing project or creating a new project.
     * @author caleb
     *
     */
    private class ProjectEditPanel extends JPanel {

        /**
         * generated serial id.
         */
        private static final long serialVersionUID = -8006320246280583297L;

        private Project myOldProject;

        private static final int MAX_TITLE_LEN = 45;
        
        private JTextField myTxtTitle = new JTextField(MAX_TITLE_LEN);
        private ArrayList<Material> myMaterials = new ArrayList<Material>();
        private ArrayList<Receipt> myReceipts = new ArrayList<Receipt>();

        /**
         * panel for editing a new project.
         * @author caleb
         */
        private ProjectEditPanel(ProjectPanel projectPanel) {
            this(projectPanel, null);
        }

        /**
         * panel for editing a project.
         * @param projectPanel
         * @param theProject existing project or null if this is for a new project
         * @author caleb
         */
        private ProjectEditPanel(ProjectPanel projectPanel, Project theProject) {
            myOldProject = theProject;
            if(theProject != null) {
                myReceipts = theProject.getReceipts();
                myMaterials = theProject.getMaterials();
            }

            this.setBorder(BorderFactory.createBevelBorder(0));
            //components

            JLabel lblTitle = new JLabel("Title: ");
            JLabel lblPanelHeader;
            
            if(theProject == null) {
                lblPanelHeader = new JLabel("New Project");
            } else {
                String title = theProject.getTitle();
                lblPanelHeader = new JLabel(title.substring(0, Math.min(title.length(), MAX_TITLE_LEN)));
                myTxtTitle.setText(title);
            }

            JButton btnAddMaterials = new JButton("Materials");
            btnAddMaterials.setActionCommand(COMMAND.MATERIALS.name());
            btnAddMaterials.addActionListener(projectPanel);

            JButton btnAddReceipts = new JButton("Receipts");
            btnAddReceipts.setActionCommand(COMMAND.RECEIPTS.name());
            btnAddReceipts.addActionListener(projectPanel);

            JButton btnCancelProjectEdit = new JButton("Cancel");
            btnCancelProjectEdit.setActionCommand(COMMAND.CLOSE_PANEL.name());
            btnCancelProjectEdit.addActionListener(projectPanel);

            JButton btnSaveProjectEdit = new JButton("Save");
            btnSaveProjectEdit.setActionCommand(COMMAND.SAVE_PROJECT_EDIT.name());
            btnSaveProjectEdit.addActionListener(projectPanel);

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.add(lblPanelHeader, BorderLayout.CENTER);

            JPanel infoPanel = new JPanel(new BorderLayout());
            infoPanel.add(lblTitle, BorderLayout.WEST);
            infoPanel.add(myTxtTitle, BorderLayout.EAST);

            JPanel materialsPanel = new JPanel(new BorderLayout());
            materialsPanel.add(btnAddMaterials, BorderLayout.NORTH);

            JPanel receiptsPanel = new JPanel(new BorderLayout());
            receiptsPanel.add(btnAddReceipts, BorderLayout.NORTH);

            JPanel costsPanel = new JPanel();
            costsPanel.setLayout(new GridLayout(0,2));
            costsPanel.add(materialsPanel);
            costsPanel.add(receiptsPanel);

            JPanel closingPanel = new JPanel(new BorderLayout());
            closingPanel.add(btnCancelProjectEdit, BorderLayout.WEST);
            closingPanel.add(btnSaveProjectEdit, BorderLayout.EAST);

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.add(infoPanel, BorderLayout.NORTH);
            centerPanel.add(costsPanel, BorderLayout.SOUTH);

            GridLayout layout = new GridLayout(0,1);
            layout.setVgap(20);
            JPanel mainPanel = new JPanel(layout);
            mainPanel.add(headerPanel);
            mainPanel.add(centerPanel);
            mainPanel.add(closingPanel);
            this.add(mainPanel);
        }

        /**
         * Update the current project to be this project.
         * @author caleb
         */
        private void updateProject() {
            String title = myTxtTitle.getText();
            if(myOldProject != null) {
                ProjectManager.updateProject(myOldProject, title, myMaterials, myReceipts);
            } else {
                Project p = new Project(title, myMaterials, myReceipts);
                ProjectManager.addProject(p);
            }
            setCurrentProject(ProjectManager.getCurrentProjectIndex());
        }

    }    


    /**
     * updates the mainframe title to match this project index title and sets the current project to this indexs project.
     * @param index
     * @author caleb
     */
    private void setCurrentProject(int index) {
        ProjectManager.setCurrentProject(index);
        Project p = ProjectManager.getProject(index);
        setFrameTitle(p.getTitle());
    }

    /**
     * calls the mainframes set title method with the title passed in here.
     * @param title
     * @author caleb
     */
    private void setFrameTitle(String title) {
        getMainFrame().SetTitle(title);
    }
    
    /**
     * gives the windowAncestor for this assuming it is a MainFrame.
     * @return
     * @author caleb
     */
    private MainFrame getMainFrame() {
        return (MainFrame) SwingUtilities.getWindowAncestor(this);
    }
    
    /**
     * sets the frames home page to the home page.
     * @author caleb
     */
    private void setPageToHome() {
        getMainFrame().changePanel(PAGE.HOME);
    }

    /**
     * panel for selecting an existing project.
     * @author caleb
     *
     */
    private class ProjectExistingPanel extends JPanel {
        /**
         * generated serial id.
         */
        private static final long serialVersionUID = 2933859283811046218L;

        private ProjectExistingPanel(ProjectPanel projectPanel) {
            this.setLayout(new GridLayout(0,1));

            JPanel projectsScrollPanel = new JPanel();
            JPanel projectsPanel = new JPanel(new GridLayout(0,1));
            JScrollPane scrollPane = new JScrollPane(projectsScrollPanel,
                                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                     JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            List<Project> projects = ProjectManager.getProjects();
            for(Project proj : projects) {
                int maxCharsInTitleLabel = 17;

                GridLayout layout = new GridLayout(1,0);
                layout.setHgap((int) (projectPanel.getWidth() * -.08));
                
                JPanel panel = new JPanel(layout);
                String cost = "";
                Double tempCost = proj.estimateTotal();
                cost = makeSmallMoney(tempCost);
                JLabel displayText = new JLabel(proj.getTitle().substring
                                                (0, Integer.min(maxCharsInTitleLabel, proj.getTitle().length())) +
                                                "   Created: " + proj.getDateCreated() +
                                                "   Modified: "+ proj.getDateLastModified() +
                                                "   Cost: $" + cost);
                int projIndex = ProjectManager.getIndex(proj);

                JButton btnEdit = new JButton("Edit");
                btnEdit.setActionCommand(/*"_P"*/COMMAND.PREFIX_EDIT_EXISTING_PROJECT.name() + projIndex);
                btnEdit.addActionListener(projectPanel);

                JButton btnSelect = new JButton("Select");
                btnSelect.setActionCommand(/*"_S"*/COMMAND.PREFIX_SELECT_PROJECT.name() + projIndex);
                btnSelect.addActionListener(projectPanel);

                JButton btnDelete = new JButton("Delete");
                btnDelete.setActionCommand(/*"_D"*/COMMAND.PREFIX_REMOVE_EXISTING_PROJECT.name() + projIndex);
                btnDelete.addActionListener(projectPanel);

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(btnEdit);
                buttonPanel.add(btnSelect);
                buttonPanel.add(btnDelete);

                panel.add(displayText);
                panel.add(buttonPanel);
                panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                projectsPanel.add(panel);
            }
            projectsScrollPanel.add(projectsPanel);
            this.add(scrollPane);
        }

        /**
         * method to limit character size of money for display in panels.
         * @param tempCost
         * @return
         * @author caleb
         */
        private String makeSmallMoney(Double tempCost) {
            DecimalFormat df = new DecimalFormat("#.##");
            String output = "";
            if(tempCost < 0) {//negative Double so should have huge cost ~ 4b+
                output = "4B +";
            } else if(tempCost >= 1000000000) {//range [max, 1B]
                tempCost /=       1000000000;
                output = df.format(tempCost); //X.XX
                output += "B";                //X.XXB
            } else if(tempCost >= 1000000) { //range (1B, 1M]
                tempCost /=       1000000;
                output = df.format(tempCost); //X.XX
                output += "M";                //Y.YYM
            } else {
                output = new DecimalFormat("#,###.##").format(tempCost);
            }
            return output;
        }
    }

    /**
     * what to do when buttons are used.
     * for static buttons the COMMAND enum is used, for dynamic buttons prefixes are used and 
     * the related identifier is appended to the end of the action command.
     * maybe these prefixes could be in an enum instead and then appended.
     * @author caleb
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String strActionCommand = e.getActionCommand();
        if(strActionCommand.startsWith(/*"_P"*/COMMAND.PREFIX_EDIT_EXISTING_PROJECT.name())) {//used with editing an existing project
            int index = Integer.parseInt(strActionCommand.substring(COMMAND.PREFIX_EDIT_EXISTING_PROJECT.name().length()));
            setCurrentProject(index);
            Project proj = ProjectManager.getProject(index);
            this.remove(displayPanel);
            displayPanel = new ProjectEditPanel(this, proj);
            this.add(displayPanel, BorderLayout.CENTER);
            this.repaint();
            this.validate();

        } else if(strActionCommand.startsWith(/*"_S"*/COMMAND.PREFIX_SELECT_PROJECT.name())) {//used with selecting a project from existing projects panel
            setCurrentProject(Integer.parseInt(strActionCommand.substring(COMMAND.PREFIX_SELECT_PROJECT.name().length())));
            this.remove(displayPanel);
            setPageToHome();
            this.repaint();
        } else if(strActionCommand.startsWith(/*"_R"*/COMMAND.PREFIX_REMOVE_MATERIAL.name())){//used for removing a material from a project
            String matToString = strActionCommand.substring(COMMAND.PREFIX_REMOVE_MATERIAL.name().length());
            Project p = ProjectManager.getProject(ProjectManager.getCurrentProjectIndex());
            for(Material mat : p.getMaterials()) {
                if(mat.toString().equals(matToString)) {//remove one of these materials
                    p.removeMaterial(mat);
                    ProjectManager.updateProject(ProjectManager.getCurrentProjectIndex(), p);
                    ProjectManager.saveProjects();
                    setCurrentProject(ProjectManager.getCurrentProjectIndex());
                    break;//only delete one 
                }
            }
            this.remove(displayPanel);
            displayPanel = new ProjectMaterialsPanel(this, p);
            this.add(displayPanel, BorderLayout.CENTER);
            this.repaint();
            this.validate();
        } else if(strActionCommand.startsWith(/*"_D"*/COMMAND.PREFIX_REMOVE_EXISTING_PROJECT.name())) {//used with deleting a project from list of existing projects panel
            Project p = ProjectManager.getProject(Integer.parseInt(strActionCommand.substring(COMMAND.PREFIX_REMOVE_EXISTING_PROJECT.name().length())));
            Integer indexToRemove = ProjectManager.getIndex(p);
            ProjectManager.removeProject(p);
            Integer curProjIndex = ProjectManager.getCurrentProjectIndex();
            if(curProjIndex != null && curProjIndex < indexToRemove) {
                setCurrentProject(curProjIndex);
            } else if(curProjIndex != null && curProjIndex > indexToRemove) {
                curProjIndex -= 1;
                setCurrentProject(curProjIndex); //project has been removed index moved 1 up
            } else {
                setFrameTitle("");
            } 
            this.remove(displayPanel);
            displayPanel = new ProjectExistingPanel(this);
            this.add(displayPanel, BorderLayout.CENTER);
            this.repaint();
            this.validate();
        } else if(strActionCommand.startsWith(COMMAND.PREFIX_REMOVE_RECEIPT.name())) {
            String recToString = strActionCommand.substring(COMMAND.PREFIX_REMOVE_RECEIPT.name().length());
            Project p = ProjectManager.getProject(ProjectManager.getCurrentProjectIndex());
            for(Receipt rec : p.getReceipts()) {
                if(rec.toString().equals(recToString)) {//remove one of these receipts
                    p.removeReceipt(rec);
                    ProjectManager.updateProject(ProjectManager.getCurrentProjectIndex(), p);
                    ProjectManager.saveProjects();
                    setCurrentProject(ProjectManager.getCurrentProjectIndex());
                    break;//only delete one 
                }
            }
            //reload screen to show new receipts
            this.remove(displayPanel);
            displayPanel = new ProjectReceiptsPanel(this, p);
            this.add(displayPanel, BorderLayout.CENTER);
            this.repaint();
            this.validate();
        } else if (strActionCommand.startsWith(COMMAND.PREFIX_ADD_RECEIPT.name())) {
            //Michelle's block of code for adding receipt
            this.remove(displayPanel);
            displayPanel = new AddReceiptPanel();
            this.add(displayPanel, BorderLayout.CENTER);
            this.repaint();
            this.validate();
        } else {
            COMMAND actionComm = COMMAND.getCommand(e.getActionCommand());
            switch(actionComm) {
                case EDIT_PROJECT: //edit brand new or existing project
                    this.remove(displayPanel);
                    displayPanel = new ProjectEditPanel(this);
                    this.add(displayPanel, BorderLayout.CENTER);
                    this.repaint();
                    this.validate();//used because panel components changed
                    break;
                case EXISTING_PROJECT:
                    this.remove(displayPanel);
                    displayPanel = new ProjectExistingPanel(this);
                    this.add(displayPanel);
                    this.repaint();
                    this.validate();
                    break;
                case MATERIALS:
                    //ProjectEditPanel panel = (ProjectEditPanel) displayPanel;//the panel that made this action
                    ((ProjectEditPanel)displayPanel).updateProject();
                    this.remove(displayPanel);
                    displayPanel = new ProjectMaterialsPanel(this, 
                                                             ProjectManager.getProject(ProjectManager.getCurrentProjectIndex()));
                    this.add(displayPanel);
                    this.repaint();
                    this.validate();
                    break;
                case RECEIPTS:
                    openReceiptsPage();
                    break;
                case CLOSE_PANEL:
                    this.remove(displayPanel);
                    setPageToHome();
                    this.repaint();
                    this.validate();
                    break;
                case SAVE_PROJECT_EDIT:
                    ((ProjectEditPanel)displayPanel).updateProject();
                    this.remove(displayPanel);
                    setPageToHome();
                    this.repaint();
                    break;
                default: 
                    System.out.println("action with action command:" + actionComm); 
                    break;
            }

        }

    }
    
    /**
     * @author Michelle
     */
    private void openReceiptsPage() {
        this.remove(displayPanel);
        displayPanel = new ProjectReceiptsPanel(this,
                                                ProjectManager.getProject(ProjectManager.getCurrentProjectIndex()));
        this.add(displayPanel);
        this.repaint();
        this.validate();
    }



}
