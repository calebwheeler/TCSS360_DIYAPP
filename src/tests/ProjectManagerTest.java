package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Material;
import model.Measurement;
import model.Project;

/**
 * The JUnit test for ProjectManager class.
 * 
 * @author Jim Phan
 * @author Michelle Brown
 * @version 6/6/2018
 */
public class ProjectManagerTest {

    private List<Project> testProjects;
    
    private List<Material> testMaterials;
    
    /**
     * Sets up the ProjectManager.
     * 
     * @throws Exception
     * @author Jim Phan
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
        project2.addMaterial(testMaterials.get(1).clone());
        project2.addMaterial(testMaterials.get(2).clone());
        project2.addMaterial(testMaterials.get(3).clone());
        
        Project project3 = new Project("Project 3");
        project2.addMaterial(testMaterials.get(2).clone());
        project2.addMaterial(testMaterials.get(2).clone());
        project2.addMaterial(testMaterials.get(3).clone());
        
        //Add projects to the manager.
        testProjects.add(project1);
        testProjects.add(project2);
        testProjects.add(project3);
    }

    @Test
    public void testGetProject() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCurrentProjectIndex() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetCurrentProjectProject() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetCurrentProjectInteger() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetIndex() {
        fail("Not yet implemented");
    }

    @Test
    public void testCount() {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveProjects() {
        fail("Not yet implemented");
    }

    @Test
    public void testLoadProjects() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddProject() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveProject() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateProjectProjectStringListOfMaterialListOfReceipt() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateProjectIntegerStringListOfMaterialListOfReceipt() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateProjectIntegerProject() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetProjects() {
        fail("Not yet implemented");
    }

}
