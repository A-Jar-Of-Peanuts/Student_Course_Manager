package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

// creates a jpanel that displays options for the student manager class
public class StudentPanel extends JPanel {
    private GridBagLayout gl;
    private StudentManager sm;
    private CourseManager cm;
    private JTextArea ta;
    private GridBagConstraints gbc;
    private JTextField name, status, major, grad, removeName;

    // EFFECTS: creates a student panel
    public StudentPanel(StudentManager sm, CourseManager cm) {
        this.sm = sm;
        this.cm = cm;
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
        remove();
        removeButton();
        courseGrade();
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
        submitButton();
    }

    // EFFECTS: adds submit button
    public void submitButton() {
        JButton b = new JButton("Submit");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String n = name.getText().trim();
                    String s = status.getText().trim();
                    String m = major.getText().trim();
                    Integer g = Integer.parseInt(grad.getText().trim());
                    if (n.isEmpty() || s.isEmpty() || m.isEmpty()) {
                        throw new Exception();
                    }
                    sm.addStudent(n, s, m, g);
                    addStudents();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Input. Please try again.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 501;
        gbc.gridy = 50;
        gbc.ipadx = 10;
        this.add(b, gbc);
    }

    // EFFECTS: adds coursegrade gui
    public void courseGrade() {
        gbc.gridx = 501;
        gbc.gridy = 140;
        gbc.ipadx = 10;
        JLabel one = new JLabel("Student Name");
        this.add(one, gbc);
        gbc.gridx = 511;
        gbc.gridy = 140;
        gbc.ipadx = 100;
        JTextField sname = new JTextField();
        this.add(sname, gbc);

        gbc.gridx = 501;
        gbc.gridy = 150;
        gbc.ipadx = 10;
        JLabel two = new JLabel("Course Name");
        this.add(two, gbc);
        gbc.gridx = 511;
        gbc.gridy = 150;
        gbc.ipadx = 100;
        JTextField cname = new JTextField();
        this.add(cname, gbc);

        gbc.gridx = 501;
        gbc.gridy = 160;
        gbc.ipadx = 10;
        JLabel three = new JLabel("CDF? (Y/N)");
        this.add(three, gbc);
        gbc.gridx = 511;
        gbc.gridy = 160;
        gbc.ipadx = 100;
        JTextField cdf = new JTextField();
        this.add(cdf, gbc);

        gbc.gridx = 501;
        gbc.gridy = 170;
        gbc.ipadx = 10;
        JLabel four = new JLabel("Percentage");
        this.add(four, gbc);
        gbc.gridx = 511;
        gbc.gridy = 170;
        gbc.ipadx = 100;
        JTextField perc = new JTextField();
        this.add(perc, gbc);

        JButton b = new JButton("Submit");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = sname.getText().trim();
                    String c = cname.getText().trim();
                    String cd = cdf.getText().trim();
                    Integer p = Integer.parseInt(perc.getText().trim());
                    if (s.isEmpty() || c.isEmpty() || cd.isEmpty()) {
                        throw new Exception();
                    }
                    if (cd.equals("Y")) {
                        sm.getStudent(s).addCourseGrade(cm.getCourse(c), p, true);
                    } else if (cd.equals("N")) {
                        sm.getStudent(s).addCourseGrade(cm.getCourse(c), p, false);
                    } else {
                        throw new Exception();
                    }
                    addStudents();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Input. Please try again.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 501;
        gbc.gridy = 180;
        gbc.ipadx = 10;
        this.add(b, gbc);
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
        name = new JTextField();
        this.add(name, gbc);
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
        status = new JTextField();
        this.add(status, gbc);
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
        major = new JTextField();
        this.add(major, gbc);
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
        grad = new JTextField();
        this.add(grad, gbc);
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

    // EFFECTS: adds course grade info to text area
    private String addCourseGrade(Student s) {
        String text = "\tCourses: \n";
        for (int i = 0; i < s.getCourseGrade().size(); i++) {
            text += "\t\t" + s.getCourseGrade(i).getName() + "\n";
            text += "\t\t\tCredit: " + s.getCourseGrade(i).getCredit() + "\n";
            text += "\t\t\tGrade: " + s.getCourseGrade(i).getGrade() + "\n";
        }
        return text;
    }

    // EFFECTS: adds remove button
    public void removeButton() {
        JButton b = new JButton("Remove");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String n = removeName.getText().trim();
                    if (n.isEmpty()) {
                        throw new Exception();
                    }
                    sm.removeStudent(n);
                    addStudents();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Input. Please try again.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 501;
        gbc.gridy = 130;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        this.add(b, gbc);
    }

    // EFFECTS: adds remove gui
    public void remove() {
        gbc.gridx = 501;
        gbc.gridy = 110;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        JLabel two = new JLabel("Remove Student: ");
        this.add(two, gbc);
        gbc.gridx = 501;
        gbc.gridy = 120;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        JLabel one = new JLabel("Name: ");
        this.add(one, gbc);
        gbc.gridx = 511;
        gbc.gridy = 120;
        gbc.ipadx = 100;
        removeName = new JTextField();
        this.add(removeName, gbc);
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
