package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestCDF {
    CDF c1, c2, c3;

    @BeforeEach
    public void setup() {
        c1 = new CDF(new Course("", 4), 0);
        c2 = new CDF(new Course("ABC", 4), 50);
        c3 = new CDF(new Course("", 4), 55);
    }

    @Test
    public void getGradeTest() {
        assertEquals("F", c1.getGrade());
        assertEquals("D", c2.getGrade());
        assertEquals("Cr", c3.getGrade());
    }

    @Test
    public void getWeightedGradeTest() {
        assertEquals(0, c1.getWeightedGrade());
        assertEquals(0, c2.getWeightedGrade());
        assertEquals(0, c3.getWeightedGrade());
    }

    @Test
    public void getCreditTest() {
        assertEquals(0, c1.getCredit());
        assertEquals(0, c2.getCredit());
        assertEquals(0, c3.getCredit());
    }

    @Test
    public void percentageTest() {
        assertEquals(0, c1.getPercentage());
        c1.setPercentage(90);
        assertEquals(90, c1.getPercentage());

        c1.setPercentage(70);
        assertEquals(70, c1.getPercentage());
    }

    @Test
    public void courseTest() {
        assertEquals("", c1.getCourse().getName());
        assertEquals("ABC", c2.getCourse().getName());
    }
}