package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPercentageGrade {
    PercentageGrade c1, c2, c3;

    @BeforeEach
    public void setup() {
        c1 = new PercentageGrade(new Course("", 4), 0);
        c2 = new PercentageGrade(new Course("ABC", 3), 50);
        c3 = new PercentageGrade(new Course("", 1), 55);
    }

    @Test
    public void getGradeTest() {
        assertEquals("0", c1.getGrade());
        assertEquals("50", c2.getGrade());
        assertEquals("55", c3.getGrade());
    }

    @Test
    public void getWeightedGradeTest() {
        assertEquals(0, c1.getWeightedGrade());
        assertEquals(3*50, c2.getWeightedGrade());
        assertEquals(1*55, c3.getWeightedGrade());
    }

    @Test
    public void getCreditTest() {
        assertEquals(4, c1.getCredit());
        assertEquals(3, c2.getCredit());
        assertEquals(1, c3.getCredit());
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
