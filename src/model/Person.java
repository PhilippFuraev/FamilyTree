/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;

/**
 *
 * @author furae_000
 */
public class Person {

    private String name;
    private int age;
    private int level;
    private LinkedList<String> parents;
    private LinkedList<String> children;
    private String spouse;

    public Person(String name, int age, LinkedList<String> children, LinkedList<String> parents, String spouse, int level) {
        this.name = name;
        this.age = age;
        this.children = children;
        this.parents = parents;
        this.spouse = spouse;
        this.level = level;
    }

    private String listToString(LinkedList<String> list) {
        if (list.size() != 0) {
            ListIterator<String> cur = list.listIterator();
            String s = cur.next();
            while (cur.hasNext()) {
                s = s + "," + cur.next();
            }
            return s;
        } else {
            return "null";
        }
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String string = "Name: " + name + "\nAge: " + age;
        string += "\nChildren: ";
        string += listToString(children);
        string += "\nParents: ";
        string += listToString(parents);
        string += "\nSpouse: ";
        string += spouse;
        //string += "\nlevel: ";
        //string += (level + 1);
        return string;

    }
    


    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public void addChild(String child) {
        children.add(child);
    }

    public void removeChild(String child) {
        children.remove(child);
    }
    
    public void removeParent(String parent)
    {
        parents.remove(parent);
    }

    public LinkedList getChildren() {
        return children;
    }

    public LinkedList getParents() {
        return parents;
    }

    public String savePersonToString() {
        String string = name + "." + age;
        string += ".";
        string += listToString(children);
        string += ".";
        string += listToString(parents);
        string += ".";
        string += spouse;
        string += ".";
        string += level;
        string += ".";
        return string;
    }

}
