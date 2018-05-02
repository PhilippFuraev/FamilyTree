/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import model.*;


public class AddSpouseWindow extends JDialog {
     private JTextField nField;
    private JTextField aField;
    private JButton next;
    private JButton cancel;
    private boolean result;
    private int level;
    private int numOfError;
    FamilyTree tree;

    public AddSpouseWindow(FamilyTree tree, int level) {
        super();
        this.tree=tree;
        this.level = level;
        numOfError = -1;
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        result = false;
        Box nameBox = Box.createHorizontalBox();
        Box okCancel = Box.createHorizontalBox();
        Box ageBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("Name:");
        nField = new JTextField(15);
        aField = new JTextField(15);
        next = new JButton("Ok");
        cancel = new JButton("Cancel");
        next.addActionListener(new AddSpouseWindowListener());
        cancel.addActionListener(new AddSpouseWindowListener());
        JLabel aLabel = new JLabel("Age:");
                ageBox.add(aLabel);
        ageBox.add(Box.createHorizontalStrut(6));
        ageBox.add(aField);
        nameBox.add(nLabel);
        nameBox.add(Box.createHorizontalStrut(6));
        nameBox.add(nField);
        okCancel.add(Box.createHorizontalGlue());
        okCancel.add(cancel);
        okCancel.add(Box.createHorizontalStrut(12));
        okCancel.add(next);
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(nameBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(ageBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(okCancel);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public String getName() {
        return nField.getText();
    }

    public int getLevel() {
        return level;
    }
    
        public int getAge() {
        String str = aField.getText();
        Integer i = Integer.valueOf(str);
        return i;
    }

    public boolean isOk() {
        return result;
    }
    
        private int checkInput() {
            String str = "";
        if (getName() == null) {
            numOfError = 0;
        }
        for (int i=0; i<tree.getMaximumLevel();i++)
        {
            for (int j=0; j<tree.getLevelSize(i); j++)
            {
                str = tree.getPerson(i, j).getName();
                if (str.equals(getName()))
                    numOfError = 1;
            }
        }
        return numOfError;
        }
        
        
            class AddSpouseWindowListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(next)) {
                if (checkInput() == -1) {
                    result = true;
                    AddSpouseWindow.this.setVisible(false);
                } if (checkInput()==0) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Enter name!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    result = false;
                    AddSpouseWindow.this.setVisible(false);
                }
                if (checkInput () == 1)
                {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "this name is already using. add some identifiers, like " + getName() + "2", "Warning!", JOptionPane.WARNING_MESSAGE);
                    result = false;
                    AddSpouseWindow.this.setVisible(false);
                    
                }

            }
            if (e.getSource().equals(cancel)) {
                AddSpouseWindow.this.setVisible(false);
                AddSpouseWindow.this.dispose();
            }

        }

    }
    
}
