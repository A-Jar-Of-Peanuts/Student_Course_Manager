package persistence;

import model.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            StudentManager sm = new StudentManager();
            CourseManager cm = new CourseManager();
            JsonWriter writer = new JsonWriter("./data/my\0illegalfilename.json");
            writer.open();
            fail();
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testEmpty() {
        try {
            StudentManager sm = new StudentManager();
            CourseManager cm = new CourseManager();
            JsonWriter writer = new JsonWriter("./data/testEmpty.json");
            writer.open();
            writer.write(sm, cm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmpty.json");
            sm = reader.readSM();
            cm = reader.readCM();
            assertEquals(0, sm.getLength());
            assertEquals(0, cm.getLength());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testGeneral() {
        try {
            StudentManager sm = new StudentManager();
            CourseManager cm = new CourseManager();
            addObjects(sm, cm);

            JsonWriter writer = new JsonWriter("./data/testGeneral.json");
            writer.open();
            writer.write(sm, cm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneral.json");
            sm = reader.readSM();
            cm = reader.readCM();
            assertEquals(1, sm.getLength());
            assertEquals(2, cm.getLength());
            checkStudentFields(sm.getStudent(0), "Bob", "international", "Computer Science", 2025);
            checkCourseGrade(sm.getStudent(0).getCourseGrade(0), 90, true, "CPSC 110", 4);
            checkCourseGrade(sm.getStudent(0).getCourseGrade(1), 60, false, "MATH 100", 3);
            checkCourse(cm.getCourse(0), "CPSC 110", 4);
            checkCourse(cm.getCourse(1), "MATH 100", 3);
        } catch (IOException e) {
            fail();
        }
    }

    void addObjects(StudentManager sm, CourseManager cm) {
        cm.addCourse("CPSC 110", 4);
        cm.addCourse("MATH 100", 3);
        sm.addStudent("Bob", "international", "Computer Science", 2025);
        Student bob = sm.getStudent("Bob");
        Course cpsc = cm.getCourse("CPSC 110");
        Course math = cm.getCourse("MATH 100");
        bob.addCourseGrade(cpsc, 90, true);
        bob.addCourseGrade(math, 60, false);
    }
}
