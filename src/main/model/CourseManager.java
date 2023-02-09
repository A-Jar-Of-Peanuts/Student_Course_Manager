package model;

import java.util.*;

// Creates and manages courses, and sorts them in lexicographical order
public class CourseManager {
    private ArrayList<Course> courses;

    // EFFECTS: creates an empty list of courses
    public CourseManager() {
        courses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new course and if the name isn't in the course list already,
    // sorts it into the list in lexicographical order and returns true, otherwise
    // returns false
    public boolean addCourse(String name, int credit) {
        for (int i = 0; i < courses.size(); i++) {
            if (name.equals(courses.get(i).getName())) {
                return false;
            } else if (name.compareTo(courses.get(i).getName()) < 0) {
                courses.add(i, new Course(name, credit));
                return true;
            }
        }
        courses.add(new Course(name, credit));
        return true;
    }

    // REQUIRES: remove is smaller than the length of courses
    // MODIFIES: this
    // EFFECTS: removes the course at the given index from courses
    public void removeCourse(int remove) {
        courses.remove(remove);
    }

    // EFFECTS: returns the length of courses
    public int getLength() {
        return courses.size();
    }
}
