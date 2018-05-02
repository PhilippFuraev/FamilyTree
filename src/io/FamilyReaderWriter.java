/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import model.*;
import view.FamilyView;
import view.FamilyView.Coordinates;

/**
 *
 * @author furae_000
 */
public class FamilyReaderWriter {

    private FamilyTree tree;
    private HashMap<String, Coordinates> coordinates;

    public FamilyReaderWriter() {
        tree = new FamilyTree();
        coordinates = new HashMap<String, Coordinates>();

    }

    public FamilyReaderWriter(FamilyTree tree, HashMap<String, Coordinates> coordinates) {
        this.tree = tree;
        this.coordinates = coordinates;
    }

    public FamilyTree getTree() {
        return tree;
    }

    public HashMap<String, Coordinates> getCoordinates() {
        return coordinates;
    }

    public void familyWriter(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        Person p;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < tree.getLevelSize(i); j++) {
                p = tree.getPerson(i, j);
                fw.write(p.savePersonToString() + coordinates.get(p.getName()).toString() + "\r\n");
                fw.flush();
            }
        }

    }

    public void familyOpen(File file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner treeScanner = new Scanner(fr).useDelimiter("\r\n");
        Scanner personScanner;
        Scanner childrenScanner;
        String name = null;
        int age = 0;
        LinkedList<String> children = new LinkedList();
        LinkedList<String> parents = new LinkedList();
        String spouse = null;
        int level = 0;
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        FamilyView view = new FamilyView();
        while (treeScanner.hasNextLine()) {
            name = null;
            age = 0;
            children = new LinkedList();
            parents = new LinkedList();
            spouse = null;
            level = 0;
            x = 0;
            y = 0;
            width = 0;
            height = 0;
            personScanner = new Scanner(treeScanner.nextLine()).useDelimiter("[.]");
            if (personScanner.hasNext()) {
                name = personScanner.next();
            } else {
                return;
            }
            if (personScanner.hasNextInt()) {
                age = personScanner.nextInt();
            } else {
                return;
            }
            if (personScanner.hasNext()) {
                childrenScanner = new Scanner(personScanner.next()).useDelimiter("[,]");;
                while (childrenScanner.hasNext()) {
                    children.add(childrenScanner.next());
                }

            }
            if (personScanner.hasNext()) {
                childrenScanner = new Scanner(personScanner.next()).useDelimiter("[,]");
                while (childrenScanner.hasNext()) {
                    parents.add(childrenScanner.next());
                }
            }
            if (personScanner.hasNext()) {
                spouse = personScanner.next();
            }
            if (personScanner.hasNextInt()) {
                level = personScanner.nextInt();
            } else {
                return;
            }
            if (personScanner.hasNextInt()) {
                x = personScanner.nextInt();
            }
            if (personScanner.hasNextInt()) {
                y = personScanner.nextInt();
            }
            if (personScanner.hasNextInt()) {
                width = personScanner.nextInt();
            }
            if (personScanner.hasNextInt()) {
                height = personScanner.nextInt();
            }
            tree.add(new Person(name, age, children, parents, spouse, level));
            view.changePersonCoords(name, x, y, width, height);
            coordinates = view.getFamilyViewCoordinates();

        }

    }
}
