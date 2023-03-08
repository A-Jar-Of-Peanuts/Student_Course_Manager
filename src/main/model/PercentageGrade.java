package model;

// Represents a percentage course grade.
public class PercentageGrade extends CourseGrade {

    // REQUIRES: percentage >= 0
    // EFFECTS: creates a percentage grade with the given course and percentage. sets cdf to false
    public PercentageGrade(Course c, int percentage) {
        super(c, percentage);
        cdf = false;
    }

    // EFFECTS: return the grade for this course grade
    @Override
    public String getGrade() {
        return percentage + "";
    }

    // REQUIRES: percentage != -1
    // EFFECTS: returns the weighted grade for this course grade (percentage * credit)
    @Override
    public int getWeightedGrade() {
        return percentage * getCredit();
    }

    // EFFECTS: returns the amount of credit the course is worth
    @Override
    public int getCredit() {
        return course.getCredit();
    }
}
