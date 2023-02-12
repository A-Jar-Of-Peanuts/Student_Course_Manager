package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudentManager {
    StudentManager cm1, cm2;

    @BeforeEach
    public void setup() {
        cm1 = new StudentManager();
        cm2 = new StudentManager();
    }

    @Test
    public void addRemoveCourseTest() {
        assertTrue(cm1.addStudent("c", "international", "", 2027));
        assertEquals(1, cm1.getLength());

        assertFalse(cm1.addStudent("c", "domestic", "", 2027));
        assertEquals(1, cm1.getLength());

        cm1.addStudent("c2", "international", "", 2029);

        cm1.removeStudent("c");
        assertEquals(1, cm1.getLength());

        cm1.removeStudent(0);
        assertEquals(0, cm1.getLength());
    }

    @Test
    public void sortTest() {
        cm1.addStudent("c2", "international", "", 2029);
        cm1.addStudent("c", "international", "", 2027);
        assertEquals("c", cm1.getStudent(0).getName());
        assertEquals("c2", cm1.getStudent(1).getName());

        cm2.addStudent("a", "international", "", 2029);
        cm2.addStudent("d", "international", "", 2029);
        cm2.addStudent("b1", "international", "", 2029);
        assertEquals("a", cm2.getStudent(0).getName());
        assertEquals("b1", cm2.getStudent(1).getName());
        assertEquals("d", cm2.getStudent(2).getName());
    }

    @Test
    public void getTest() {
        cm1.addStudent("CPSC 210", "international", "", 2029);
        cm1.addStudent("CPSC 110", "international", "", 2029);
        assertEquals(cm1.getStudent("CPSC 210").getName(), "CPSC 210");
        assertEquals(cm1.getStudent("CPSC 110").getName(), "CPSC 110");
    }


}
