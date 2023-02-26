package model;

import persistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

// abstract class that represents a course and the grade
// attained in that course.
public abstract class CourseGrade implements Writable {
    protected final Course course;
    protected int percentage;

    // REQUIRES: percentage >= 0
    // EFFECTS: creates a course grade with the given course and percentage
    public CourseGrade(Course course, int percentage) {
        this.course = course;
        this.percentage = percentage;
    }

    // EFFECTS: return the grade for this course grade
    public abstract String getGrade();

    // EFFECTS: returns the weighted grade for this course grade
    public abstract int getWeightedGrade();

    // EFFECTS: returns the amount of credit this course grade is worth
    public abstract int getCredit();

    public String getName() {
        return course.getName();
    }

    public Course getCourse() {
        return course;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course", course);
        json.put("percentage", percentage);
        return json;
    }

}
