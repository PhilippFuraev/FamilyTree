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
public class PersonTest {
    Person p;
    

    public PersonTest() {
        LinkedList<String> parents = new LinkedList();
        parents.add("Father");
        parents.add("Mother");
        LinkedList<String> children = new LinkedList();
        children.add("Vasya");
        String spouse = "Yulya";
        p = new Person("Vitya", 33, children, parents, spouse, 2);
    }



    /**
     * Test of getLevel method, of class Person.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        
        Person instance = this.p;
        int expResult = 2;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Person instance = p;
        String expResult = "Vitya";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpouse method, of class Person.
     */
    @Test
    public void testGetSpouse() {
        System.out.println("getSpouse");
        Person instance = p;
        String expResult = "Yulya";
        String result = instance.getSpouse();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpouse method, of class Person.
     */
    @Test
    public void testSetSpouse() {
        System.out.println("setSpouse");
        String testSpouse = "Petya";
        Person instance = p;
        instance.setSpouse(testSpouse);
        String result = testSpouse;
        assertEquals(testSpouse, instance.getSpouse());
    }

    /**
     * Test of addChild method, of class Person.
     */
    @Test
    public void testAddChild() {
        System.out.println("addChild");
        String child = "Lesha";
        Person instance = p;
        instance.addChild(child);
        assertEquals(child, instance.getChildren().get(1));
    }

    /**
     * Test of removeChild method, of class Person.
     */
    @Test
    public void testRemoveChild() {
        System.out.println("removeChild");
        String child = "Vasya";
        Person instance = p;
        String result = null;
        instance.removeChild(child);

        if (instance.getChildren().size() == 0) {
            assertNull(result);
        } else {
            fail("Remove Child test Failed");
        }
    }
        @Test
        public void testRemoveParent() {
        System.out.println("removeParent");
        String parent = "Mother";
        Person instance = p;
        String result = null;
        instance.removeParent(parent);

        if (instance.getChildren().size() == 1) {
            assertNull(result);
        } else {
            fail("Remove Child test Failed");
        }
    }

    /**
     * Test of getChildren method, of class Person.
     */
    @Test
    public void testGetChildren() {
        System.out.println("getChildren");
        LinkedList<String> expResult = new LinkedList();
        expResult.add("Vasya");
        Person instance = p;
        LinkedList result = instance.getChildren();
        assertEquals(expResult, result);

    }

    /**
     * Test of getParents method, of class Person.
     */
    @Test
    public void testGetParents() {
        System.out.println("getParents");
        LinkedList<String> expResult = new LinkedList();
        expResult.add("Father");
        expResult.add("Mother");
        Person instance = p;
        LinkedList result = instance.getParents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
