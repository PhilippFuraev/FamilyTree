/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import model.FamilyTree;
import model.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.FamilyView;

/**
 *
 * @author furae_000
 */
public class FamilyReaderWriterTest {
    FamilyTree treeFt;
    Person p;
    FamilyView treeFV;
    
    
    public FamilyReaderWriterTest() {
                treeFt = new FamilyTree();
                treeFV = new FamilyView();
        LinkedList<String> parents = new LinkedList();
        parents.add("Father");
        parents.add("Mother");
        LinkedList<String> children = new LinkedList();
        children.add("Vasya");
        children.add("Masha");
        String s = "";
        p = new Person("Vitya", 33, children, parents, s, 2);
        treeFt.add(p);
        treeFV.changePersonCoords("Vitya", 1, 2, 3, 4);
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
     * Test of familyWriter method, of class FamilyReaderWriter.
     */
    @Test
    public void testFamilyWritergood() throws Exception {
        System.out.println("familyWriter");
        File file = new File("GoodSaveTestFile.txt");
        FamilyReaderWriter instance = new FamilyReaderWriter(treeFt, treeFV.getFamilyViewCoordinates());
        instance.familyWriter(file);
        FileReader fR = new FileReader(new File("GoodSaveTestFile.txt"));
        Scanner scanner = new Scanner(fR);
        String goodSaveFileString = "Vitya.33.Vasya,Masha.Father,Mother..2.1.2.3.4.";
        String fileString = new String();
        while (scanner.hasNextLine())
        {
            fileString = scanner.nextLine();
        }
        assertEquals(fileString, goodSaveFileString);
    }
    @Test
        public void testFamilyWriterbad() throws Exception {
        System.out.println("familyWriter");
        File file = new File("badTest/null/familyWriter.txt");
        FamilyReaderWriter instance = new FamilyReaderWriter(treeFt, treeFV.getFamilyViewCoordinates());
        try {
        instance.familyWriter(file);
        fail("test Bad Family Writer failed");
        }
        catch (IOException e)
        {
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of familyOpen method, of class FamilyReaderWriter.
     */
    @Test
    public void testFamilyOpengood() throws Exception {
        System.out.println("familyOpen");
        FamilyReaderWriter instance = new FamilyReaderWriter();
        instance.familyOpen(new File("GoodLoadTestFile.txt"));
        FamilyTree instanceTree = instance.getTree();
        assertEquals(instance.getTree().getLevelSize(0), 2);
        assertEquals(instance.getCoordinates().size(), 3);
    }
    
        @Test
    public void testFamilyOpenbad() throws Exception {
        System.out.println("familyOpen");
        File file = new File("nonexistentFile");
        FamilyReaderWriter instance = new FamilyReaderWriter();
        try{
        instance.familyOpen(file);
        fail("OpenBadFileTestFile failed");
        }
        catch (IOException e)
        {
            
        }
    }
    
}
