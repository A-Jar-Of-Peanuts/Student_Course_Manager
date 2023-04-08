package model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import persistence.Writable;

// Creates and manages courses, and sorts them in lexicographical order. Can
// remove, return and add courses.
public class CourseManager implements Writable {
    private ArrayList<Course> courses;
    private HashMap<String, Course> map;

    // EFFECTS: creates an empty list of courses and an empty hashmap
    public CourseManager() {
        courses = new ArrayList<>();
        map = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new course and if the name isn't in the course list already,
    // sorts it into the list in lexicographical order and returns true, otherwise
    // returns false. Also, if successfully added into the list, maps the course object with
    // its course name as the key.
    public boolean addCourse(String name, int credit) {
        for (int i = 0; i < courses.size(); i++) {
            if (name.equals(courses.get(i).getName())) {
                return false;
            } else if (name.compareTo(courses.get(i).getName()) < 0) {
                courses.add(i, new Course(name, credit));
                map.put(name, courses.get(i));
                EventLog.getInstance().logEvent(new Event("Course added"));
                return true;
            }
        }
        courses.add(new Course(name, credit));
        map.put(name, courses.get(courses.size() - 1));
        EventLog.getInstance().logEvent(new Event("Course added"));
        return true;
    }

    // REQUIRES: remove is smaller than the length of courses
    // MODIFIES: this
    // EFFECTS: removes the course at the given index from courses
    // as well as removes the course from the hashmap
    public void removeCourse(int remove) {
        Course temp = courses.get(remove);
        map.remove(temp.getName());
        courses.remove(remove);
        EventLog.getInstance().logEvent(new Event("Course removed"));
    }

    // REQUIRES: remove is the name of a course in the course list
    // MODIFIES: this
    // EFFECTS: removes the course with the given course name from the course list
    // as well as the hashmap
    public void removeCourse(String remove) {
        Course temp = map.get(remove);
        courses.remove(temp);
        map.remove(remove);
        EventLog.getInstance().logEvent(new Event("Course removed"));
    }

    // REQUIRES: index is smaller than the length of courses
    // EFFECTS: returns the course at the given index
    public Course getCourse(int index) {
        return courses.get(index);
    }

    // REQUIRES: name is the name of a course in the course list
    // EFFECTS: returns the course with the given name
    public Course getCourse(String name) {
        Course temp = map.get(name);
        return temp;
    }

    // EFFECTS: returns the length of courses
    public int getLength() {
        return courses.size();
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses in this course manager as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
