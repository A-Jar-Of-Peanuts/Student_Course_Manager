package ui;

import javax.swing.*;
import java.awt.*;

import model.*;

// creates a jpanel that displays options for the student manager class
public class StudentPanel extends JPanel {
    private GridBagLayout gl;
    private StudentManager sm;
    private CourseManager cm;
    private JTextArea ta;
    private GridBagConstraints gbc;
    private SaveLoadPanel slp;

    // EFFECTS: creates a student panel
    public StudentPanel(StudentManager sm, CourseManager cm) {
        this.sm = sm;
        this.cm = cm;
        this.slp = slp;
        gl = new GridBagLayout();
        this.setLayout(gl);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 500;
        gbc.ipady = 500;
        gbc.gridwidth = 500;
        gbc.gridheight = 500;
        ta = new JTextArea("There are no students here!");
        ta.setEditable(false);
        JScrollPane scroll = new JScrollPane(ta);
        this.add(scroll, gbc);
        addStudents();

        addNewStudents();
    }

    // EFFECTS: gui for adding new students
    public void addNewStudents() {
        gbc.gridx = 501;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.gridheight = 10;
        gbc.gridwidth = 10;
        JLabel as = new JLabel("Add New Student: \n");
        this.add(as, gbc);
        name();
        status();
        major();
        grad();
        button();
    }

    // EFFECTS: adds submit button
    public void button() {

    }

    // EFFECTS: adds coursegrade gui
    public void courseGrade() {

    }

    // EFFECTS: adds name gui
    public void name() {
        gbc.gridx = 501;
        gbc.gridy = 10;
        gbc.ipadx = 10;
        JLabel one = new JLabel("Name: ");
        this.add(one, gbc);
        gbc.gridx = 511;
        gbc.gridy = 10;
        gbc.ipadx = 100;
        JTextField two = new JTextField();
        this.add(two, gbc);
    }

    // EFFECTS: adds status gui
    public void status() {
        gbc.gridx = 501;
        gbc.gridy = 20;
        gbc.ipadx = 10;
        JLabel three = new JLabel("Status: ");
        this.add(three, gbc);
        gbc.gridx = 511;
        gbc.gridy = 20;
        gbc.ipadx = 100;
        JTextField four = new JTextField();
        this.add(four, gbc);
    }

    // EFFECTS: adds major gui
    public void major() {
        gbc.gridx = 501;
        gbc.gridy = 30;
        gbc.ipadx = 10;
        JLabel five = new JLabel("Major: ");
        this.add(five, gbc);
        gbc.gridx = 511;
        gbc.gridy = 30;
        gbc.ipadx = 100;
        JTextField six = new JTextField();
        this.add(six, gbc);
    }

    // EFFECTS: adds grad date gui
    public void grad() {
        gbc.gridx = 501;
        gbc.gridy = 40;
        gbc.ipadx = 10;
        JLabel seven = new JLabel("Graduation Date: ");
        this.add(seven, gbc);
        gbc.gridx = 511;
        gbc.gridy = 40;
        gbc.ipadx = 100;
        JTextField eight = new JTextField();
        this.add(eight, gbc);
    }

    //EFFECTS: adds students and student info to text area.
    public void addStudents() {
        if (sm.getLength() == 0) {
            ta.setText("There are no students here!");
        } else {
            String text = "";
            for (int i = 0; i < sm.getLength(); i++) {
                text += sm.getStudent(i).getName() + "\n";
                text += "\tMajor: " + sm.getStudent(i).getMajor() + "\n";
                text += "\tGraduation Date: " + sm.getStudent(i).getGradDate() + "\n";
                text += "\tStatus: " + sm.getStudent(i).getStatus() + "\n";
                text += "\tGPA: " + sm.getStudent(i).getGPA() + "\n";
                text += addCourseGrade(sm.getStudent(i));
            }
            ta.setText(text);
        }
    }

    private String addCourseGrade(Student s) {
        String text = "\tCourses: \n";
        for (int i = 0; i < s.getCourseGrade().size(); i++) {
            text += "\t\t" + s.getCourseGrade(i).getName() + "\n";
            text += "\t\t\tCredit: " + s.getCourseGrade(i).getCredit() + "\n";
            text += "\t\t\tGrade: " + s.getCourseGrade(i).getGrade() + "\n";
        }
        return text;
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
