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
public class FamilyTree {

    private LinkedList<Person>[] tree;
    int maximumLevel;

    public FamilyTree() {
        tree = new LinkedList[20];
        for (int i = 0; i < 20; i++) {
            tree[i] = new LinkedList<Person>();
            maximumLevel=0;
        }
    }

    public void add(Person person) {
        int i = 0;
        i = person.getLevel();
        if(i>maximumLevel)
            maximumLevel=i;
        tree[i].add(person);
    }

    public void remove(Person person) {
        int i = 0;
        i = person.getLevel();
        tree[i].remove(person);
    }

    public Person getPerson(int level, int num) {
        return tree[level].get(num);
    }

    public Person getPerson(String name) {
        for (int i = 0; i < 20; i++) {
            ListIterator iter = tree[i].listIterator();
            while (iter.hasNext()) {
                Person p = (Person) iter.next();
                if (p.getName().equals(name)) {
                    return p;
                }
            }
        }
        return null;
    }

/*   public void swapPerson(int level, int num1, int num2) {
        Person tmp = tree[level].get(num1);
        tree[level].set(num1, tree[level].get(num2));
        tree[level].set(num2, tmp);

    }
*/
    public int getLevelSize(int level) {
        return tree[level].size();
    }

    public void setChildToParents(Person child) {
        if (child.getLevel() > 0) {
            for (int i = 0; i < child.getParents().size(); i++) {
                ListIterator iter = tree[child.getLevel() - 1].listIterator();
                while (iter.hasNext()) {
                    Person p = (Person) iter.next();
                    if (p.getName().equals(child.getParents().get(i))) {
                        p.addChild(child.getName());
                        iter.set(p);
                        break;
                    }
                }
            }
        }
    }
    
    public int getMaximumLevel ()
    {
        return maximumLevel;
    }

    public void setSpouse(Person spouse) {
        ListIterator iter = tree[spouse.getLevel()].listIterator();
        while (iter.hasNext()) {
            Person p = (Person) iter.next();
            if (p.getName().equals(spouse.getSpouse())) {
                removeSpouse(p);
                p.setSpouse(spouse.getName());
                iter.set(p);
            }
        }

    }

    public void removeSpouse(Person spouse) {
        ListIterator iter = tree[spouse.getLevel()].listIterator();
        while (iter.hasNext()) {
            Person p = (Person) iter.next();
            if (p.getName().equals(spouse.getSpouse())) {
                p.setSpouse("");
                iter.set(p);
            }
        }

    }

    public void removeChildFromParents(Person child) {
        if (child.getLevel() > 0) {
            for (int i = 0; i < child.getParents().size(); i++) {
                ListIterator iter = tree[child.getLevel() - 1].listIterator();
                while (iter.hasNext()) {
                    Person p = (Person) iter.next();
                    if (p.getName().equals(child.getParents().get(i))) {
                        p.removeChild(child.getName());
                        iter.set(p);
                        break;
                    }
                }
            }
        }
    }
    
        public void removeParentFromChild(Person parent) {
        if (parent.getLevel() < 19) {
            for (int i = 0; i < parent.getChildren().size(); i++) {
                ListIterator iter = tree[parent.getLevel() + 1].listIterator();
                while (iter.hasNext()) {
                    
                    Person p = (Person) iter.next();
                    if (p.getName().equals(parent.getChildren().get(i))) {
                        p.removeParent(parent.getName());
                        iter.set(p);
                        break;
                    }
                }
            }
        }
    }
}
