package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCourseManager {
    CourseManager cm1, cm2;

    @BeforeEach
    public void setup() {
        cm1 = new CourseManager();
        cm2 = new CourseManager();
    }

    @Test
    public void addRemoveCourseTest() {
        assertTrue(cm1.addCourse("CPSC 110", 4));
        assertEquals(1, cm1.getLength());

        assertFalse(cm1.addCourse("CPSC 110", 3));
        assertEquals(1, cm1.getLength());

        cm1.addCourse("CPSC 210", 4);

        cm1.removeCourse("CPSC 110");
        assertEquals(1, cm1.getLength());

        cm1.removeCourse(0);
        assertEquals(0, cm1.getLength());
    }

    @Test
    public void sortTest() {
        cm1.addCourse("CPSC 210", 4);
        cm1.addCourse("CPSC 110", 4);
        assertEquals("CPSC 110", cm1.getCourse(0).getName());
        assertEquals("CPSC 210", cm1.getCourse(1).getName());

        cm2.addCourse("a", 0);
        cm2.addCourse("c", 0);
        cm2.addCourse("b1", 0);
        assertEquals("a", cm2.getCourse(0).getName());
        assertEquals("b1", cm2.getCourse(1).getName());
        assertEquals("c", cm2.getCourse(2).getName());
    }

    @Test
    public void getTest() {
        cm1.addCourse("CPSC 210", 4);
        cm1.addCourse("CPSC 110", 4);
        assertEquals(cm1.getCourse("CPSC 210").getName(), "CPSC 210");
        assertEquals(cm1.getCourse("CPSC 110").getName(), "CPSC 110");
    }
}
