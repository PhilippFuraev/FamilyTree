/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.FamilyTree;
import model.Person;

/**
 *
 * @author furae_000
 */
public class ParentsAndSpousesWindow extends JDialog {

    private JButton ok;
    private JButton cancel;
    private Person p = null;
    private String name;
    private int age;
    private int level;
    boolean result;
    private String firstParent;
    private String secondParent;
    private String spouse;

    public ParentsAndSpousesWindow(int level, String name, int age, FamilyTree tree) {
        super();
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.level = level;
        this.name = name;
        this.age = age;
        firstParent = "";
        secondParent = "";
        spouse = null;
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        Box parentsBox = Box.createHorizontalBox();
        Box spouseBox = Box.createHorizontalBox();
        Box okCancel = Box.createHorizontalBox();
        Box cantChoose = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("Parents:");
        JLabel sLabel = new JLabel("Spouse:");
        if (level > 0) {
            String[] parents = new String[tree.getLevelSize(level - 1) + 1];
            parents[0] = "";
            for (int i = 0; i < tree.getLevelSize(level - 1); i++) {
                parents[i + 1] = tree.getPerson(level - 1, i).getName();
            }
            JComboBox firstParentsComboBox = new JComboBox(parents);
            firstParentsComboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    firstParent = (String) firstParentsComboBox.getSelectedItem();
                }

            });
            JComboBox secondParentsComboBox = new JComboBox(parents);
            secondParentsComboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    secondParent = (String) secondParentsComboBox.getSelectedItem();
                }

            });
            parentsBox.add(pLabel);
            parentsBox.add(Box.createHorizontalStrut(6));
            parentsBox.add(firstParentsComboBox);
            parentsBox.add(Box.createHorizontalStrut(3));
            parentsBox.add(secondParentsComboBox);
            mainBox.add(parentsBox);
            mainBox.add(Box.createVerticalStrut(12));
        }

        if (tree.getLevelSize(level) > 0) {
            String[] spouses = new String[tree.getLevelSize(level) + 1];
            spouses[0] = "";
            for (int i = 0; i < tree.getLevelSize(level); i++) {
                spouses[i + 1] = tree.getPerson(level, i).getName();
            }
            JComboBox spouseComboBox = new JComboBox(spouses);
            spouseComboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    spouse = (String) spouseComboBox.getSelectedItem();
                    mainBox.add(Box.createVerticalStrut(12));
                }

            });
            spouseBox.add(sLabel);
            spouseBox.add(Box.createHorizontalStrut(6));
            spouseBox.add(spouseComboBox);
            mainBox.add(Box.createVerticalStrut(12));
            mainBox.add(spouseBox);
        }
        
        if (tree.getLevelSize(level) <= 0 && level <= 0)
        {
            cantChoose.add(Box.createHorizontalGlue());;
            cantChoose.add(new JLabel("no spouses or parents can be choosen now"));
            cantChoose.add(Box.createHorizontalGlue());
            mainBox.add(cantChoose);
        }
        ok = new JButton("Ok");
        cancel = new JButton("Cancel");
        ok.addActionListener(new ParentsAndSpousesWindowListener());
        cancel.addActionListener(new ParentsAndSpousesWindowListener());
        okCancel.add(Box.createHorizontalGlue());
        okCancel.add(ok);
        okCancel.add(Box.createHorizontalStrut(12));
        okCancel.add(cancel);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(okCancel);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public boolean checkInput() {
        if (level > 0) {
        if (firstParent.equals(new String("")) && secondParent.equals(new String(""))) {
            return true;
        }
        if (firstParent.equals(secondParent)) {
            return false;
        } else {
            return true;
        }
        }
        return true;
    }

    public LinkedList getParents() {
        LinkedList<String> list = new LinkedList();
        if(!firstParent.equals(new String("")) && !secondParent.equals(new String("")))
        {
        list.add(firstParent);
        list.add(secondParent);
        return list;
        }
        else 
            return list;

    }

    public String getSpouse() {
        return spouse;
    }

    public boolean isOk() {
        return result;
    }

    public Person getPerson() {
        return p;
    }

    class ParentsAndSpousesWindowListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(ok)) {
                if (checkInput()) {
                    ParentsAndSpousesWindow.this.p = new Person(name, age, new LinkedList<String>(), getParents(), getSpouse(), level);
                    result = true;
                    ParentsAndSpousesWindow.this.setVisible(false);
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Choose one or different parents", "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (e.getSource().equals(cancel)) {
                ParentsAndSpousesWindow.this.setVisible(false);
                ParentsAndSpousesWindow.this.dispose();
            }

        }

    }
}
