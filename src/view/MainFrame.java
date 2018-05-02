/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import io.FamilyReaderWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import model.*;

/**
 *
 * @author furae_000
 */
public class MainFrame extends JFrame {

    JMenuBar menu;
    JMenu fileButton, treeButton;
    JMenuItem saveButton, loadButton, sortButton, helpButton;

    public MainFrame(String title) {
        super(title);
        menu = new JMenuBar();
        //menu.add(fileButton);
        //menu.add(treeButton);
        saveButton = new JMenuItem("Save", new ImageIcon("icon_save_16x16.gif"));
        loadButton = new JMenuItem("Open", new ImageIcon("icon_OpenFile.png"));
        loadButton.addActionListener(new ButtonActionListener());
        saveButton.addActionListener(new ButtonActionListener());
        sortButton = new JMenuItem("Sort", new ImageIcon("sort-descending-icon.png"));
        helpButton = new JMenuItem("Help", new ImageIcon("Help_Circle_Blue.png"));
        helpButton.addActionListener(new ButtonActionListener());
        saveButton.setSize(18, 18);
        FlowLayout menuLayout = new FlowLayout();
        menuLayout.setAlignment(FlowLayout.LEFT);
        menu.setLayout(menuLayout);
        menu.add(loadButton);
        menu.add(saveButton);
        menu.add(sortButton);
        menu.add(helpButton);
        sortButton.addActionListener(new ButtonActionListener());
        setSize(1000, 1000);
        menu.setSize(130, 50);
        menu.setPreferredSize(new Dimension(130, 34));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        FamilyView panel1 = new FamilyView();
        this.setLayout(new BorderLayout());
        c.add(menu, BorderLayout.PAGE_START);
        c.add(panel1, BorderLayout.CENTER);
        setVisible(true);
    }

    class ButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(loadButton)) {
                JFileChooser fc = new JFileChooser();
                FamilyReaderWriter frw = new FamilyReaderWriter();
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fc.getSelectedFile();
                        frw.familyOpen(file);
                        setVisible(true);
                        Container c = MainFrame.this.getContentPane();
                        c.remove(1);
                        c.add(new FamilyView(frw.getTree(), frw.getCoordinates()));
                        setVisible(true);
                        repaint();

                    } catch (IOException r) {
                        System.out.println("IOError");
                    }
                }
            }
            if (e.getSource().equals(saveButton)) {
                Container c = MainFrame.this.getContentPane();
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fc.getSelectedFile();
                        FamilyView fv = (FamilyView) c.getComponent(1);
                        FamilyReaderWriter frw = new FamilyReaderWriter(fv.getFamilyViewTree(), fv.getFamilyViewCoordinates());
                        frw.familyWriter(file);
                    } catch (IOException r) {
                        System.out.println("IO error");
                    }
                }
            }
            if (e.getSource().equals(sortButton)) {
                FamilyView.needSort = true;
                repaint();
            }
            if (e.getSource().equals(helpButton)) {
            JOptionPane.showMessageDialog(new JFrame(), "1.Add the first person in a tree\n" +
"2. Add his spouse\n" +
"3.add children", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }

}
