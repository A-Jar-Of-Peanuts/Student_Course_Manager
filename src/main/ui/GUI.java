package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// creates a gui for the student course manager
public class GUI extends JFrame {
    private JLabel label;
    private JTextField field;
    private StudentManager stManager;
    private CourseManager coManager;

    // creates a new gui
    public GUI() {
        super("Student Course Manager");
        stManager = new StudentManager();
        coManager = new CourseManager();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(5000, 5000));
        tabbedPane();
        pack();
        setVisible(true);
    }

    // EFFECTS: creates tabbed pane interface
    public void tabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        StudentPanel panel1 = new StudentPanel(stManager, coManager);
        tabbedPane.add("Student", panel1);

        CoursePanel panel2 = new CoursePanel(stManager, coManager);
        tabbedPane.add("Course", panel2);

        JComponent panel3 = new SaveLoadPanel(stManager, coManager, panel1, panel2);
        tabbedPane.add("Save/Load", panel3);
        add(tabbedPane);
    }

}


