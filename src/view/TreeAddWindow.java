/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Person;

public class TreeAddWindow extends JDialog {

    private JTextField nField;
    private JTextField aField;
    //private JTextField lField;
    private JButton next;
    private JButton cancel;
    private int level;
    Person p = null;
    private boolean result;
    String str;

    public TreeAddWindow() {
        super();
        level = 0;
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        result = false;
        Box nameBox = Box.createHorizontalBox();
        Box ageBox = Box.createHorizontalBox();
        Box okCancel = Box.createHorizontalBox();
        //Box levelBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("Name:");
        nField = new JTextField(15);
        JLabel aLabel = new JLabel("Age:");
        aField = new JTextField(15);
        //JLabel lLabel = new JLabel("Level:");
        //lField = new JTextField(15);
        String[] levels = new String[20];
        for (int i = 0; i < 20; i++) {
            levels[i] = "" + (i + 1);
        }
        JComboBox levelComboBox = new JComboBox(levels);
        levelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = levelComboBox.getSelectedIndex();
                str = (String) levelComboBox.getSelectedItem();

            }

        });

        levelComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                str = (String) levelComboBox.getSelectedItem();
            }

        });
        nameBox.add(nLabel);
        nameBox.add(Box.createHorizontalStrut(6));
        nameBox.add(nField);
        //levelBox.add(lLabel);
        //levelBox.add(Box.createHorizontalStrut(6));
        //levelBox.add(levelComboBox);
        ageBox.add(aLabel);
        ageBox.add(Box.createHorizontalStrut(6));
        ageBox.add(aField);
        next = new JButton("Next");
        cancel = new JButton("Cancel");
        next.addActionListener(new addWindowListener());
        cancel.addActionListener(new addWindowListener());
        okCancel.add(Box.createHorizontalGlue());
        okCancel.add(cancel);
        okCancel.add(Box.createHorizontalStrut(12));
        okCancel.add(next);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(nameBox);
        mainBox.add(Box.createVerticalStrut(12));
        //mainBox.add(levelBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(ageBox);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(okCancel);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public String getName() {
        return nField.getText();
    }

    public int getAge() {
        String str = aField.getText();
        Integer i = Integer.valueOf(str);
        return i;
    }







    public int getLevel() {
        return level;
    }

    public boolean isOk() {
        return result;
    }

    public Person getPerson() {
        return p;
    }

    private boolean checkInput() {
        if (getName() == null) {
            return false;
        }
        Scanner scanner1 = new Scanner(aField.getText());
        if (!scanner1.hasNextInt()) {
            return false;
        }
        return true;
    }

    class addWindowListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(next)) {
                if (checkInput()) {
                    String str = "";
                    TreeAddWindow.this.p = new Person (getName(), getAge(), new LinkedList(), new LinkedList(), str, 0);
                    result = true;
                    TreeAddWindow.this.setVisible(false);
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Incorrect input!", "Warning!", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (e.getSource().equals(cancel)) {
                TreeAddWindow.this.setVisible(false);
                TreeAddWindow.this.dispose();
            }

        }

    }

}
