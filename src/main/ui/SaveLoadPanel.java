package ui;

import javax.swing.*;
import model.*;
import persistence.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// jpanel for saving and loading
public class SaveLoadPanel extends JPanel {
    private static final String JSON_STORE = "./data/studentcoursemanager.json";
    private StudentManager sm;
    private CourseManager cm;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GridBagLayout gl;
    private StudentPanel sp;
    private CoursePanel cp;
    private GridBagConstraints gbc;

    // EFFECTS: creates a new save load panel
    public SaveLoadPanel(StudentManager sm, CourseManager cm, StudentPanel sp, CoursePanel cp) {
        this.sp = sp;
        this.cp = cp;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.sm = sm;
        this.cm = cm;
        gl = new GridBagLayout();
        this.setLayout(gl);
        this.gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 50;
        gbc.ipadx = 50;
        addUI();
    }

    // EFFECTS: adds ui
    public void addUI() {
        JButton save = new JButton("save", new ImageIcon("data/floppyDisk.png"));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.add(save, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton load = new JButton("load", new ImageIcon("data/load.png"));
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                load();
                sp.setSM(sm);
                sp.setCM(cm);
                sp.addStudents();
                cp.setSM(sm);
                cp.setCM(cm);
                cp.addCourses();
            }
        });
        this.add(load, gbc);
    }

    // EFFECTS: saves the student manager and course manager to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(sm, cm);
            jsonWriter.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("unable to save");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the student manager and course manager from file
    private void load() {
        try {
            cm = jsonReader.readCM();
            sm = jsonReader.readSM();
            System.out.println("loaded!");
        } catch (IOException e) {
            System.out.println("unable to load");
        }
    }

    // EFFECTS: sets student manager
    public void setSM(StudentManager sm) {
        this.sm = sm;
    }

    // EFFECTS: sets course manager
    public void setCM(CourseManager cm) {
        this.cm = cm;
    }
}
