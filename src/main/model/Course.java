package model;

// A course with a name and the amount of credit its worth
public class Course {
    private final String name;
    private final int credit;

    // EFFECTS: creates a course with the specified name and credit amount
    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }
}
