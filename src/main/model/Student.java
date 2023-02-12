package model;

import java.util.*;

// Represents a student with a list of grades, graduation date,
// major, and status (domestic or international)
public class Student {
    private String name;
    private String status;
    private String major;
    private int gradDate;
    private ArrayList<CourseGrade> courseGrade;

    // REQUIRES: status must be "international" or "domestic", major must be a valid major,
    // and grade date must be >= the current year
    // EFFECT: creates a student with specified major, status, graduation date,
    // and an empty list of grades
    public Student(String name, String status, String major, int gradDate) {
        this.name = name;
        this.status = status;
        this.major = major;
        this.gradDate = gradDate;
        courseGrade = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new CDF if cdf is true, and a new PercentageGrade otherwise.
    // If the course name isn't in the course list already,
    // sorts it into the list in lexicographical order and returns true, otherwise
    // if the course name is in the course list already, returns false
    public boolean addCourseGrade(Course course, int percentage, boolean cdf) {
        for (int i = 0; i < courseGrade.size(); i++) {
            if (course.getName().equals(courseGrade.get(i).getName())) {
                return false;
            } else if (course.getName().compareTo(courseGrade.get(i).getName()) < 0) {
                if (cdf) {
                    courseGrade.add(i, new CDF(course, percentage));
                } else {
                    courseGrade.add(i, new PercentageGrade(course, percentage));
                }
                return true;
            }
        }
        if (cdf) {
            courseGrade.add(new CDF(course, percentage));
        } else {
            courseGrade.add(new PercentageGrade(course, percentage));
        }
        return true;
    }

    // EFFECTS: returns the weighted gpa of all of this student's course grades. If
    // everthing is credit/d/fail OR this student isn't taking any courses currently, returns -1
    public String getGPA() {
        int num = 0;
        int dem = 0;
        for (int i = 0; i < courseGrade.size(); i++) {
            num += courseGrade.get(i).getWeightedGrade();
            dem += courseGrade.get(i).getCredit();
        }
        if (dem == 0) {
            return "N/A";
        }
        return num / dem + "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGradDate() {
        return gradDate;
    }

    public void setGradDate(int gradDate) {
        this.gradDate = gradDate;
    }

    public ArrayList<CourseGrade> getCourseGrade() {
        return courseGrade;
    }
}
