package ui;

import model.CourseManager;
import model.Student;
import model.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// creates a jpanel that displays options for the course manager class
public class CoursePanel extends JPanel {
    private GridBagLayout gl;
    private StudentManager sm;
    private CourseManager cm;
    private JTextArea ta;
    private GridBagConstraints gbc;
    private JTextField name;
    private JTextField credit;

    // EFFECTS: creates a course panel
    public CoursePanel(StudentManager sm, CourseManager cm) {
        this.sm = sm;
        this.cm = cm;
        gl = new GridBagLayout();
        this.setLayout(gl);
        name = new JTextField();
        credit = new JTextField();

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 500;
        gbc.ipady = 500;
        gbc.gridwidth = 500;
        gbc.gridheight = 500;
        ta = new JTextArea("There are no courses here!");
        ta.setEditable(false);
        JScrollPane scroll = new JScrollPane(ta);
        this.add(scroll, gbc);
        addCourses();

        addNewCourses();
    }

    // EFFECTS: gui for adding new students
    public void addNewCourses() {
        gbc.gridx = 501;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.gridheight = 10;
        gbc.gridwidth = 10;
        JLabel as = new JLabel("Add New Course: \n");
        this.add(as, gbc);
        name();
        credit();
        submitButton();
    }

    // EFFECTS: adds submit button
    public void submitButton() {
        JButton b = new JButton("Submit");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String n = name.getText().trim();
                    Integer c = Integer.parseInt(credit.getText().trim());
                    if (n.isEmpty()) {
                        throw new Exception();
                    }
                    cm.addCourse(n, c);
                    addCourses();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Input. Please try again.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 501;
        gbc.gridy = 30;
        gbc.ipadx = 10;
        this.add(b, gbc);
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
        this.add(name, gbc);
    }

    // EFFECTS: adds credit gui
    public void credit() {
        gbc.gridx = 501;
        gbc.gridy = 20;
        gbc.ipadx = 10;
        JLabel three = new JLabel("Credit: ");
        this.add(three, gbc);
        gbc.gridx = 511;
        gbc.gridy = 20;
        gbc.ipadx = 100;
        this.add(credit, gbc);
    }

    //EFFECTS: adds courses and course info to text area.
    public void addCourses() {
        if (cm.getLength() == 0) {
            ta.setText("There are no courses here!");
        } else {
            String text = "";
            for (int i = 0; i < cm.getLength(); i++) {
                text += cm.getCourse(i).getName() + "\n";
                text += "\tCredit: " + cm.getCourse(i).getCredit() + "\n";
            }
            ta.setText(text);
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
