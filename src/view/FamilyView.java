/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

import java.awt.*;

import java.io.*;

import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.Map.Entry;
import model.*;

/**
 *
 * @author furae_000
 */
public class FamilyView extends JPanel {

    private JPopupMenu menuPopUp1 = new JPopupMenu();
    private JPopupMenu menuPopUp2 = new JPopupMenu();
    private JMenuItem remove;
    private JMenuItem properties;
    private JMenuItem add;
    private JMenuItem addChild;
    private JMenuItem addSpouse;

    private FamilyTree tree;
    private HashMap<String, Coordinates> coord;
    static boolean needSort;
    private String currentPers;
    private int mouseX;
    private int mouseY;
    private boolean leftMousePressed;

    public class Coordinates {

        private int x;
        private int y;
        private int width;
        private int height;

        public Coordinates(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        private Coordinates(int level, int num, Dimension dim) {
            calcCoordinates(level, num, dim);
        }

        public void setCoordinates(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void calcCoordinates(int level, int num, Dimension dim) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, false, false);
            Font f = new Font("SERIF", 1, 12);
            this.width = (int) (f.getStringBounds(tree.getPerson(level, num).getName(), frc).getWidth()) + 10;
            this.height = 20;
            this.x = dim.width / (tree.getLevelSize(level) + 1) * (num + 1) - this.width / 2;
            this.y = 20 + level * 50 + 34;
        }

        private boolean isIn(int x, int y) {
            if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
                return true;
            } else {
                return false;
            }
        }

        private void setX(int x) {
            this.x = x;
        }

        private void setY(int y) {
            this.y = y;
        }

        public String toString() {
            String str = x + "." + y + "." + width + "." + height + ".";
            return str;
        }

    }

    public FamilyView() {
        tree = new FamilyTree();
        coord = new HashMap();
        needSort = false;
        addMouseListener(new MyMouseListener());
        menuPopUp1 = new JPopupMenu();
        menuPopUp2 = new JPopupMenu();
        remove = new JMenuItem("Remove", new ImageIcon("delete.png"));
        properties = new JMenuItem("Info", new ImageIcon("Studio2-icon-Properties.png"));
        add = new JMenuItem("Add first person", new ImageIcon("add-icon.png"));
        addChild = new JMenuItem("Add child", new ImageIcon("Add child icon.png"));
        addSpouse = new JMenuItem("Add spouse", new ImageIcon("add-icon.png"));
        addChild.addActionListener(new PopButtonActionListener());
        remove.addActionListener(new PopButtonActionListener());
        properties.addActionListener(new PopButtonActionListener());
        add.addActionListener(new PopButtonActionListener());
        addSpouse.addActionListener(new PopButtonActionListener());
        addMouseMotionListener(new MyMouseMotionListener());
        menuPopUp1.add(remove);
        menuPopUp1.add(addChild);
        menuPopUp1.add(addSpouse);
        menuPopUp1.add(properties);
        menuPopUp2.add(add);
    }

    public FamilyView(FamilyTree tree, HashMap coord) {
        this();
        this.tree = tree;
        this.coord = coord;

    }

    public FamilyTree getFamilyViewTree() {
        return tree;
    }

    public HashMap<String, Coordinates> getFamilyViewCoordinates() {
        return coord;
    }

    public void changePersonCoords(String name, int x, int y, int width, int heigth) {
        coord.put(name, new Coordinates(x, y, width, heigth));
    }

    private String isIn(int x, int y) {
        for (Entry e : coord.entrySet()) {
            Coordinates c = (Coordinates) e.getValue();
            if (c.isIn(x, y)) {
                return (String) e.getKey();
            }
        }
        return null;
    }

    public void sort(Dimension dim) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < tree.getLevelSize(i); j++) {
                coord.put(tree.getPerson(i, j).getName(), new Coordinates(i, j, dim));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        Dimension dim = getSize();
        if (needSort) {
            sort(dim);
            needSort = false;
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < tree.getLevelSize(i); j++) {
                Coordinates personCoords = coord.get(tree.getPerson(i, j).getName());
                g.setFont(new Font("SERIF", 1, 12));
                g.setColor(Color.red);
                g.fillOval(personCoords.x, personCoords.y, personCoords.width, personCoords.height);
                g.setColor(Color.black);
                g.drawString(tree.getPerson(i, j).getName(), personCoords.x + 5, personCoords.y + 15);
                g.setColor(Color.blue);
                g.drawOval(personCoords.x, personCoords.y, personCoords.width, personCoords.height);
                g.setColor(Color.black);
                if (tree.getPerson(i, j).getSpouse() != null) {
                    for (int l = 0; l < tree.getLevelSize(i); l++) {
                        if (tree.getPerson(i, j).getSpouse().equals(tree.getPerson(i, l).getName())) {
                            if (j < l) {
                                g.drawLine(personCoords.x + personCoords.width, personCoords.y + 10, coord.get(tree.getPerson(i, l).getName()).x, coord.get(tree.getPerson(i, l).getName()).y + 10);
                            } else {

                            }
                        }

                    }

                }
                if (tree.getPerson(i, j).getChildren().size() != 0 && i < 19) {
                    ListIterator<String> iter = tree.getPerson(i, j).getChildren().listIterator();
                    while (iter.hasNext()) {
                        String child = iter.next();
                        for (int k = 0; k < tree.getLevelSize(i + 1); k++) {
                            if (child.equals(tree.getPerson(i + 1, k).getName())) {

                                g.drawLine(personCoords.x + personCoords.width / 2, personCoords.y + 20, coord.get(tree.getPerson(i + 1, k).getName()).x + coord.get(tree.getPerson(i + 1, k).getName()).width / 2, coord.get(tree.getPerson(i + 1, k).getName()).y);

                            }
                        }

                    }
                }
            }
        }

    }

    public class MyMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 3) {
                mouseX = e.getX();
                mouseY = e.getY();
                String str = isIn(mouseX, mouseY);
                if (str != null) {
                    FamilyView.this.menuPopUp1.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    FamilyView.this.menuPopUp2.show(e.getComponent(), e.getX(), e.getY());
                }
                currentPers = str;

            }
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {

            if (e.getButton() == 1) {
                leftMousePressed = true;
                String str = isIn(e.getX(), e.getY());
                if (str != null) {
                    mouseX = e.getX() - coord.get(str).x;
                    mouseY = e.getY() - coord.get(str).y;
                }
                currentPers = str;
            }

        }

        public void mouseReleased(MouseEvent e) {
            leftMousePressed = false;
        }
    }

    public class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (leftMousePressed) {
                if (currentPers != null) {
                    coord.get(currentPers).setX(e.getX() - mouseX);
                    coord.get(currentPers).setY(e.getY() - mouseY);
                }
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }

    class PopButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(add)) {
                if (tree.getLevelSize(0) < 1) {
                    add.setEnabled(false);
                }
                TreeAddWindow frame = new TreeAddWindow();
                if (frame.isOk()) {
                    Person p = frame.getPerson();
                    if (tree.getPerson(p.getName()) == null) {
                        tree.add(p);
                        tree.setChildToParents(p);
                        tree.setSpouse(p);
                        AffineTransform affinetransform = new AffineTransform();
                        FontRenderContext frc = new FontRenderContext(affinetransform, false, false);
                        Font f = new Font("SERIF", 1, 12);
                        coord.put(p.getName(), new Coordinates(mouseX, mouseY, (int) (f.getStringBounds(p.getName(), frc).getWidth()) + 10, 20));
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Entered name allready in use. Add details", "Warning", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Main Parent Allready in tree", "Warning", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (e.getSource().equals(addChild)) {
                addChildWindow frame = new addChildWindow(FamilyView.this.getFamilyViewTree(), FamilyView.this.tree.getPerson(currentPers).getLevel() + 1);
                if (frame.isOk()) {
                    LinkedList<String> list = new LinkedList();
                    String str = "";
                    list.add(currentPers);
                    if (tree.getPerson(currentPers).getSpouse() != null && !"".equals(tree.getPerson(currentPers).getSpouse())) {
                        ParentsQuestionWindow secondframe = new ParentsQuestionWindow(tree.getPerson(currentPers).getSpouse(), frame.getName());
                        if (secondframe.getResult() == true) {
                            list.add(tree.getPerson(currentPers).getSpouse());
                        }

                    }
                    Person p = new Person(frame.getName(), frame.getAge(), new LinkedList<String>(), list, str, frame.getLevel());
                    tree.add(p);
                    tree.setChildToParents(p);
                    AffineTransform affinetransform = new AffineTransform();
                    FontRenderContext frc = new FontRenderContext(affinetransform, false, false);
                    Font f = new Font("SERIF", 1, 12);
                    coord.put(p.getName(), new Coordinates(mouseX, mouseY, (int) (f.getStringBounds(p.getName(), frc).getWidth()) + 10, 20));
                    repaint();
                }
            }

            if (e.getSource().equals(addSpouse)) {
                AddSpouseWindow frame = new AddSpouseWindow(FamilyView.this.getFamilyViewTree(), FamilyView.this.tree.getPerson(currentPers).getLevel());
                if (frame.isOk()) {
                    LinkedList<String> list = new LinkedList();
                    ListIterator iter = (ListIterator) tree.getPerson(currentPers).getChildren().listIterator();
                    while (iter.hasNext()) {
                        String str = (String) iter.next();
                        ParentsQuestionWindow secondframe = new ParentsQuestionWindow(frame.getName(), str);
                        if (secondframe.getResult() == true) {
                            list.add(str);
                        }
                    }
                    Person p = new Person(frame.getName(), frame.getAge(), list, new LinkedList<String>(), currentPers, frame.getLevel());
                    tree.add(p);
                    tree.setSpouse(p);
                    AffineTransform affinetransform = new AffineTransform();
                    FontRenderContext frc = new FontRenderContext(affinetransform, false, false);
                    Font f = new Font("SERIF", 1, 12);
                    coord.put(p.getName(), new Coordinates(mouseX, mouseY, (int) (f.getStringBounds(p.getName(), frc).getWidth()) + 10, 20));
                    repaint();
                }
            }
            if (e.getSource().equals(remove)) {
                Person p = tree.getPerson(currentPers);
                tree.removeParentFromChild(p);
                tree.removeChildFromParents(p);
                tree.removeSpouse(p);
                tree.remove(p);

                coord.remove(currentPers);
                FamilyView.this.repaint();

            }
            if (e.getSource().equals(properties)) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, tree.getPerson(currentPers).toString(), "About", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }
}
