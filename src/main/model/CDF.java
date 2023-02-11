package model;

// represents a Credit/D/Fail course grade.
public class CDF extends CourseGrade {

    // EFFECTS: creates a CDF with the given course and -1 as percentage
    public CDF(Course c) {
        super(c);
    }

    // REQUIRES: percentage >= 0
    // EFFECTS: creates a CDF with the given course and percentage
    public CDF(Course c, int percentage) {
        super(c, percentage);
    }

    // REQUIRES: percentage != -1
    // EFFECTS: returns Cr if higher than 55%, D if percentage is between 50-54.9%,
    // and F if under 50%.
    public String getGrade() {
        if (percentage >= 55) {
            return "Cr";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // EFFECTS: returns 0
    public int getWeightedGrade() {
        return 0;
    }

    // EFFECTS: returns 0
    public int getCredit() {
        return 0;
    }
}
