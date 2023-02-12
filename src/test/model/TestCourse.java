package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {
    Course c1, c2;

    @BeforeEach
    public void setup() {
        c1 = new Course("CPSC 210", 4);
        c2 = new Course("MATH 100", 3);
    }

    @Test
    public void getNameTest() {
        assertEquals("CPSC 210", c1.getName());
        assertEquals("MATH 100", c2.getName());
    }

    @Test
    public void getCreditTest() {
        assertEquals(4, c1.getCredit());
        assertEquals(3, c2.getCredit());
    }
}
