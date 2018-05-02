/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author furae_000
 */
public class FamilyTreeTest {
    FamilyTree tree;
    Person p;
    
    public FamilyTreeTest() {
        tree = new FamilyTree();
        LinkedList<String> parents = new LinkedList();
        parents.add("Father");
        parents.add("Mother");
        LinkedList<String> children = new LinkedList();
        children.add("Vasya");
        children.add("Masha");
        p = new Person("Vitya", 33, children, parents, null, 2);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class FamilyTree.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        FamilyTree instance = this.tree;
        instance.add(p);
        if(instance.getLevelSize(2)!=1)
        fail("add test failed");
    }

    /**
     * Test of remove method, of class FamilyTree.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Person p=this.p;
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.remove(p);
        if (instance.getLevelSize(2)!=0)
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class FamilyTree.
     */
    @Test
    public void testGetPerson_int_int() {
        System.out.println("getPerson");
        int level = 2;
        int num = 0;
        FamilyTree instance = this.tree;
        Person expResult = p;
        instance.add(p);
        Person result = instance.getPerson(level, num);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPerson method, of class FamilyTree.
     */
    @Test
    public void testGetPerson_String() {
        System.out.println("getPerson");
        String name = "Vitya";
        FamilyTree instance = this.tree;
        instance.add(p);
        Person expResult = p;
        Person result = instance.getPerson(name);
        assertEquals(expResult, result);
    }


    /**
     * Test of getLevelSize method, of class FamilyTree.
     */
    @Test
    public void testGetLevelSize() {
        System.out.println("getLevelSize");
        int level = 2;
        FamilyTree instance = this.tree;
        instance.add(p);
        int expResult = 1;
        int result = instance.getLevelSize(level);
        assertEquals(expResult, result);
    }

    /**
     * Test of setChildToParents method, of class FamilyTree.
     */
    @Test
    public void testSetChildToParents() {
        System.out.println("setChildToParents");
        LinkedList<String> parents = new LinkedList();
        LinkedList<String> children = new LinkedList();
        parents.add("Vitya");
        Person child = new Person ("Sasha", 22, children, parents, "Gera", 3);
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.add(child);
        instance.setChildToParents(child);
        if (!instance.getPerson("Vitya").getChildren().get(2).equals("Sasha"))
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpouse method, of class FamilyTree.
     */
    @Test
    public void testSetSpouse() {
        System.out.println("setSpouse");
        LinkedList<String> parents = new LinkedList();
        LinkedList<String> children = new LinkedList();
        Person spouse = new Person ("Sasha", 22, children, parents, "Vitya", 2);
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.setSpouse(spouse);
        assertEquals(instance.getPerson(2,0).getSpouse(), "Sasha" );
    }

    /**
     * Test of removeSpouse method, of class FamilyTree.
     */
    @Test
    public void testRemoveSpouse() {
        System.out.println("removeSpouse");
        LinkedList<String> parents = new LinkedList();
        LinkedList<String> children = new LinkedList();
        Person spouse = new Person ("Sasha", 22, children, parents, "Vitya", 2);
        
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.add(spouse);
        instance.setSpouse(spouse);
        instance.removeSpouse(spouse);
        assertEquals(instance.getPerson(2,0).getSpouse(), "");
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of removeChildFromParents method, of class FamilyTree.
     */
    @Test
    public void testRemoveChildFromParents() {
        System.out.println("removeChildFromParents");
        LinkedList<String> parents = new LinkedList();
        LinkedList<String> children = new LinkedList();
        parents.add("Vitya");
        Person child = new Person ("Sasha", 22, children, parents, "Gera", 3);
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.setChildToParents(child);
        instance.removeChildFromParents(child);
        if(instance.getPerson(2,0).getChildren().size()!=2)
        fail("The test case is a prototype.");
    }
    
        @Test
    public void testRemoveParentFromChild() {
        System.out.println("RemoveParentFromChild");
        LinkedList<String> parents = new LinkedList();
        LinkedList<String> children = new LinkedList();
        children.add("Vitya");
        Person child = new Person ("Sasha", 22, children, parents, "Gera", 1);
        FamilyTree instance = this.tree;
        instance.add(p);
        instance.add(child);
        instance.removeParentFromChild(child);
        if(instance.getPerson(2,0).getParents().size()!=2)
        fail("The test case is a prototype.");
    }
    
}
