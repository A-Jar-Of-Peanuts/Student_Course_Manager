package persistence;

import model.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.*;
import org.json.*;

// represents a reader that reads student and course manager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads course manager from file and returns it,
    // throws IOException if an error occurs while reading data from file
    public CourseManager readCM() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCM(jsonObject);
    }

    // EFFECTS: reads student manager from file and returns it,
    // throws IOException if an error occurs while reading data from file
    // TODO
//    public CourseManager readSM() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseSM(jsonObject);
//    }

    // EFFECTS: reads souce file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses course manager from JSON object and returns it
    private CourseManager parseCM(JSONObject jsonObject) {
        CourseManager cm = new CourseManager();
        addCourses(cm, jsonObject);
        return cm;
    }

    // MODIFIES: cm
    // EFFECTS: parses courses from JSON  object and adds them to course manager
    private void addCourses(CourseManager cm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(cm, nextCourse);
        }
    }

    // MODIFIES: cm
    // EFFECTS: parses course from JSON object and adds it to course manager
    public void addCourse(CourseManager cm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int credit = jsonObject.getInt("credit");
        cm.addCourse(name, credit);
    }

    // EFFECTS: parses course manager from JSON object and returns it
    private StudentManager parseSM(JSONObject jsonObject) {
        StudentManager sm = new StudentManager();
        addStudents(sm, jsonObject);
        return sm;
    }

    // MODIFIES: cm
    // EFFECTS: parses courses from JSON  object and adds them to course manager
    private void addStudents(StudentManager sm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addStudent(sm, nextCourse);
        }
    }

    // MODIFIES: cm
    // EFFECTS: parses course from JSON object and adds it to course manager
    public void addStudent(StudentManager sm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        String major = jsonObject.getString("major");
        int gradDate = jsonObject.getInt("gradDate");

        sm.addStudent(name, status, major, gradDate);
        Student s = sm.getStudent(name);

        JSONArray jsonArray = jsonObject.getJSONArray("courseGrade");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourseGrade(s, nextCourse);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses courseGrade from JSON objects and adds it to student
    public void addCourseGrade(Student s, JSONObject jsonObject) {
    }


}
