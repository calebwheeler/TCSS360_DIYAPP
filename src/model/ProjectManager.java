package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds all of a user's projects and manages operations on them such as
 * adding, deleting and updating relevant information.
 * 
 * @author Caleb Wheeler
 * 
 * @version May 27, 2018
 */
public class ProjectManager implements Serializable{
    
    /**
     * Don't change unless this is no longer compatible with previous serialized files.
     */
    private static final long serialVersionUID = 1591199260729617379L;

    private static List<Project> myProjects = new ArrayList<Project>();
    
    private static Integer myCurrentProjectIndex = null;
    
    //where the file should be saved
    private final static String PROJECT_FILE_PATH = "projects.seri";
    
    //this is a helper class
    private ProjectManager() {}
    
    /**
     * used to get a particular project, return null if that project doesn't exist.
     * 
     * @param index
     * @return the project at index
     * 
     * @author Caleb
     */
    public static Project getProject(Integer index) {
        return myProjects.get(index);
    }
    
    /**
     * @return the index the current project is sitting
     * 
     * @author Caleb
     */
    public static Integer getCurrentProjectIndex() {
        return myCurrentProjectIndex;
    }
    
    /**
     * use to set the project as the current project.
     * 
     * @param theProject
     * 
     * @author Caleb
     */
    public static void setCurrentProject(Project theProject) {
        Integer index = ProjectManager.getIndex(theProject);
        if(index != null) {
            setCurrentProject(index);
        }
        
    }
    
    /**
     * set the index to for the current project.
     * 
     * @param index
     * 
     * @author Caleb
     */
    public static void setCurrentProject(Integer index) {
        if(ProjectManager.getProject(index) != null)
            myCurrentProjectIndex = index;
    }
    
    /**
     * gives the index in the list of projects of the searched project or -1 if not found.
     * 
     * @param theProject
     * @return the index of the project
     * 
     * @author Caleb
     */
    public static int getIndex(Project theProject) {
        for(int i = 0; i < myProjects.size(); i++) {
            if(myProjects.get(i).equals(theProject)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * gives total number of projects.
     * 
     * @return the number of projects
     * 
     * @author Caleb
     */
    public static int count() {
        return myProjects.size();
    }
    
    /**
     * save all the projects in projectManager.
     * 
     * @author Caleb
     */
    public static void saveProjects() {
        try {
            // write object to file
            FileOutputStream fos = new FileOutputStream(PROJECT_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myProjects);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * load all projects in project manager.
     * 
     * @author Caleb
     */
    public static void loadProjects() {
        // read object from file
        try {
            FileInputStream fis = new FileInputStream(PROJECT_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            myProjects = (ArrayList<Project>) ois.readObject();
            ois.close();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * adds the new project to the list then saves the updated list of all projects.
     * 
     * @param theProject
     * 
     * @author Caleb
     */
    public static void addProject(Project theProject) {
        myProjects.add(theProject);
        saveProjects();
        myCurrentProjectIndex = ProjectManager.getIndex(theProject);
    }
    
    /**
     * removes the new project to the list then saves the updated list of all projects.
     * 
     * @param theProject
     * 
     * @author Caleb
     */
    public static void removeProject(Project theProject) {
        int index = ProjectManager.getIndex(theProject);
        if(myCurrentProjectIndex != null) { //if there is a current project and it is at this index, =null
            if(index == myCurrentProjectIndex) {
                myCurrentProjectIndex = null;
            }
        }
        myProjects.remove(theProject);
        saveProjects();
    }
    
    /**
     * update project from original old project to have these new properties.
     * 
     * @param theOldProject
     * @param theTitle
     * @param theMaterials
     * @param theReceipts
     * 
     * @author Caleb
     */
    public static void updateProject(Project theOldProject, String theTitle, 
                                     List<Material> theMaterials, List<Receipt> theReceipts) {
        for(int i = 0; i < myProjects.size(); i++) {
            if(myProjects.get(i).equals(theOldProject)) {
                Project p = myProjects.get(i);
                p.changeProjectTitle(theTitle);
                p.replaceMaterials(theMaterials);
                p.replaceReceipts(theReceipts);
                //TODO: delete the replace receipts below with the commented out line above this
                /*Receipt r = new Receipt("receipt title", new Double(5.14), new Date(), "Notes");
                p.addReceipt(r);*/
                ProjectManager.setCurrentProject(p);
                myCurrentProjectIndex = ProjectManager.getIndex(p);
                break;
            }
        }
        ProjectManager.saveProjects();
    }
    
    /**
     * update a project by its index.
     * 
     * @param index
     * @param theTitle
     * @param theMaterials
     * @param theReceipts
     * 
     * @author Caleb
     */
    public static void updateProject(Integer index, String theTitle,
                                     List<Material> theMaterials, List<Receipt> theReceipts) {
        Project p = ProjectManager.getProject(index);
        ProjectManager.updateProject(p, theTitle, theMaterials, theReceipts);
    }
    
    /**
     * update a project by index to have the materials and receipts and title of theProject
     * 
     * @param index
     * @param theProject
     * 
     * @author Caleb
     */
    public static void updateProject(Integer index, Project theProject) {
        updateProject(index, theProject.getTitle(), theProject.getMaterials(), theProject.getReceipts());
    }
    
    /**
     * return a deep clone of myProjects.
     * 
     * @return list of cloned projects
     * 
     * @author Caleb
     */
    public static List<Project> getProjects() {
        List<Project> projects = new ArrayList<Project>();
        for(int i = 0; i < myProjects.size(); i++) {
            projects.add(myProjects.get(i));
        }
        return projects;
    }
    
}
