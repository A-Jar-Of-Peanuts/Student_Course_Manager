package model;

import java.util.*;
import persistence.Writable;
import org.json.JSONObject;
import org.json.JSONArray;

// Creates and manages students, and sorts them in lexicographical order according
// to their names. Can add, remove, and return students.
public class StudentManager implements Writable {
    private ArrayList<Student> students;
    private HashMap<String, Student> map;

    // EFFECTS: creates an empty list of students and an empty hashmap
    public StudentManager() {
        students = new ArrayList<>();
        map = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new student with the given fields,
    // and if the name isn't in the student list already,
    // sorts them into the list in lexicographical order and returns true, otherwise
    // returns false. Also, if successfully added into the list, maps the student object with
    // its name as the key.
    public boolean addStudent(String name, String status, String major, int gradDate) {
        for (int i = 0; i < students.size(); i++) {
            if (name.equals(students.get(i).getName())) {
                return false;
            } else if (name.compareTo(students.get(i).getName()) < 0) {
                students.add(i, new Student(name, status, major, gradDate));
                map.put(name, students.get(i));
                return true;
            }
        }
        students.add(new Student(name, status, major, gradDate));
        map.put(name, students.get(students.size() - 1));
        return true;
    }

    // REQUIRES: remove is smaller than the length of students
    // MODIFIES: this
    // EFFECTS: removes the student at the given index from students
    public void removeStudent(int remove) {
        Student temp = students.get(remove);
        map.remove(temp.getName());
        students.remove(remove);
    }

    // REQUIRES: remove is the name of a student in the student list
    // MODIFIES: this
    // EFFECTS: removes the student with the given name from the student list
    // as well as the hashmap
    public void removeStudent(String remove) {
        Student temp = map.get(remove);
        students.remove(temp);
        map.remove(remove);
    }

    // REQUIRES: index is smaller than the length of students
    // EFFECTS: returns the student at the given index
    public Student getStudent(int index) {
        return students.get(index);
    }

    // REQUIRES: name is the name of a student in the student list
    // EFFECTS: returns the student with the given name
    public Student getStudent(String name) {
        Student temp = map.get(name);
        return temp;
    }

    // EFFECTS: returns the length of students
    public int getLength() {
        return students.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("students", studentsToJson());
        return json;
    }

    // EFFECTS: returns students in this student manager as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Student s : students) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
