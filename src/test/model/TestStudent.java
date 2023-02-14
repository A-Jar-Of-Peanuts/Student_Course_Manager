package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {
    Student c1, c2;

    @BeforeEach
    public void setup() {
        c1 = new Student("Bob", "international", "Math", 2027);
        c2 = new Student("Joe", "domestic", "", 2027);
    }

    @Test
    public void getFieldTest() {
        assertEquals("Bob", c1.getName());
        assertEquals("Joe", c2.getName());
        assertEquals("international", c1.getStatus());
        assertEquals("Math", c1.getMajor());
        assertEquals(2027, c1.getGradDate());
    }

    @Test
    public void setFieldTest() {
        c1.setName("b");
        c2.setName("s");
        c1.setStatus("domestic");
        c1.setMajor("anthropology");
        c1.setGradDate(2088);
        assertEquals("b", c1.getName());
        assertEquals("s", c2.getName());
        assertEquals("domestic", c1.getStatus());
        assertEquals("anthropology", c1.getMajor());
        assertEquals(2088, c1.getGradDate());
    }

    @Test
    public void getGPAtest() {
        assertEquals("N/A", c1.getGPA());
        c1.addCourseGrade(new Course("a", 4), 4, true);
        assertEquals("N/A", c1.getGPA());
        c1.addCourseGrade(new Course("b", 4), 4, false);
        assertEquals("4.0", c1.getGPA());
        c1.addCourseGrade(new Course("c", 3), 98, false);
        assertEquals("44.0", c1.getGPA());
    }

    @Test
    public void sortTest() {
        c1.addCourseGrade(new Course("CPSC 210", 4), 4, true);
        c1.addCourseGrade(new Course("CPSC 110", 4), 4, true);
        assertEquals("CPSC 110", c1.getCourseGrade(0).getName());
        assertEquals("CPSC 210", c1.getCourseGrade(1).getName());

        c2.addCourseGrade(new Course("a", 0), 0, false);
        c2.addCourseGrade(new Course("c", 0), 0, false);
        c2.addCourseGrade(new Course("b1", 0), 0, false);
        assertEquals("a", c2.getCourseGrade(0).getName());
        assertEquals("b1", c2.getCourseGrade(1).getName());
        assertEquals("c", c2.getCourseGrade(2).getName());
    }

    @Test
    public void addStudentTest() {
        assertTrue(c1.addCourseGrade(new Course("CPSC 110", 4), 4, false));
        assertTrue(c1.addCourseGrade(new Course("CPSC 210", 4), 4, true));
        assertEquals(2, c1.getCourseGrade().size());

        assertFalse(c1.addCourseGrade(new Course("CPSC 110", 3), 4, false));
        assertFalse(c1.addCourseGrade(new Course("CPSC 210", 3), 4, true));
        assertEquals(2, c1.getCourseGrade().size());
    }
}
