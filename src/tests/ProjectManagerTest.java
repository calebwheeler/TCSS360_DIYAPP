package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Material;
import model.Measurement;
import model.Project;
import model.ProjectManager;

/**
 * The JUnit test for ProjectManager class.
 * 
 * @author Jim Phan
 * 
 * @version June 6, 2018
 */
public class ProjectManagerTest {

    private List<Project> testProjects;
    
    private List<Material> testMaterials;
    
    /**
     * Sets up the ProjectManager.
     * 
     * @throws Exception
     * 
     * @author Jim
     */
    @Before
    public void setUp() throws Exception {
        testProjects = new ArrayList<Project>();
        testMaterials = new ArrayList<Material>();
        //Add materials
        testMaterials.add(new Material("Board", 15.00, new Measurement(1.0, 2.0, 3.0, 0.0)));
        testMaterials.add(new Material("Tile", 10.00, new Measurement(4.0, 3.0, 1.0, 0.0)));
        testMaterials.add(new Material("Paint", 15.00, new Measurement(0.0, 0.0, 0.0, 16.0)));
        
        //Add projects
        Project project1 = new Project("Project 1");
        project1.addMaterial(testMaterials.get(0).clone());
        project1.addMaterial(testMaterials.get(0).clone());
        project1.addMaterial(testMaterials.get(0).clone());
        
        Project project2 = new Project("Project 2");
        project2.addMaterial(testMaterials.get(0).clone());
        project2.addMaterial(testMaterials.get(1).clone());
        project2.addMaterial(testMaterials.get(2).clone());
        
        Project project3 = new Project("Project 3");
        project2.addMaterial(testMaterials.get(1).clone());
        project2.addMaterial(testMaterials.get(1).clone());
        project2.addMaterial(testMaterials.get(2).clone());
        
        //Add projects to the manager.
        testProjects.add(project1);
        testProjects.add(project2);
        testProjects.add(project3);
        ProjectManager.addProject(project1);
    }

    /**
     * Check if project returned was the one added.
     * 
     * @author Jim
     */
    @Test
    public void testGetProject() {
        Project project = ProjectManager.getProject(0);
        assertTrue(project.getTitle().equals("Project 1"));
        List<Material> materials = project.getMaterials();
        Material board = new Material("Board", 15.00, new Measurement(1.0, 2.0, 3.0, 0.0));
        assertTrue(materials.size() == 3);
        assertTrue(materials.get(0).getName().equals(board.getName()));
        assertTrue(materials.get(0).getPrice() == board.getPrice());
        assertTrue(materials.get(0).getAmount() == board.getAmount());
    }

    /**
     * Check index returned is correct
     * 
     * @author Jim
     */
    @Test
    public void testGetCurrentProjectIndex() {
        ProjectManager.addProject(testProjects.get(1));
        ProjectManager.setCurrentProject(1);
        assertTrue(ProjectManager.getCurrentProjectIndex() == 1);
        ProjectManager.removeProject(testProjects.get(1));
    }

    /**
     * Checks that the current project was set properly
     * 
     * @author Jim
     */
    @Test
    public void testSetCurrentProjectProject() {
        ProjectManager.addProject(testProjects.get(1));
        ProjectManager.setCurrentProject(0);
        Project project = ProjectManager.getProject(0);
        ProjectManager.setCurrentProject(1);
        ProjectManager.setCurrentProject(project);
        assertTrue(ProjectManager.getCurrentProjectIndex() == 0);
        assertTrue(ProjectManager
                   .getProject(ProjectManager.getCurrentProjectIndex()).equals(project));
        ProjectManager.removeProject(testProjects.get(1));
    }

    /**
     * Checks if the index of the current project is correct
     * 
     * @author Jim
     */
    @Test
    public void testSetCurrentProjectInteger() {
        ProjectManager.addProject(testProjects.get(1));
        ProjectManager.addProject(testProjects.get(2));
        assertTrue(ProjectManager.getCurrentProjectIndex() == 2);
        ProjectManager.setCurrentProject(0);
        assertTrue(ProjectManager.getCurrentProjectIndex() == 0);
        ProjectManager.setCurrentProject(1);
        assertTrue(ProjectManager.getCurrentProjectIndex() == 1);
        ProjectManager.removeProject(testProjects.get(1));
        ProjectManager.removeProject(testProjects.get(2));
    }

    /**
     * Number of projects value check
     * 
     * @author Jim
     */
    @Test
    public void testCount() {
        assertTrue(ProjectManager.count() == ProjectManager.getProjects().size());
    }

    /**
     * Checks if project was added
     * 
     * @author Jim
     */
    @Test
    public void testAddProject() {
        ProjectManager.addProject(testProjects.get(1));
        assertTrue(testProjects.get(1).equals(ProjectManager.getProject(ProjectManager.getIndex(testProjects.get(1)))));
        ProjectManager.removeProject(testProjects.get(1));
    }

    /**
     * Check if project was removed
     * 
     * @author Jim
     */
    @Test
    public void testRemoveProject() {
        ProjectManager.addProject(testProjects.get(1));
        int testValue = ProjectManager.getIndex(testProjects.get(1));
        assertTrue(testValue != -1);
        ProjectManager.removeProject(testProjects.get(1));
        testValue = ProjectManager.getIndex(testProjects.get(1));
        assertTrue(testValue == -1);
    }

    /**
     * Checks if the projects returned are the correct ones
     * 
     * @author Jim
     */
    @Test
    public void testGetProjects() {
        List<Project> projects = ProjectManager.getProjects();
        assertTrue(projects.size() == ProjectManager.getProjects().size());
        assertTrue(projects.get(0).getMaterials().size() == 3);
    }

}
