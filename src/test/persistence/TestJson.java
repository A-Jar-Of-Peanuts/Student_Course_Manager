package persistence;

import model.*;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJson {
    protected void checkStudentFields(Student s, String name, String status, String major, int gradDate) {
        assertEquals(s.getName(), name);
        assertEquals(s.getStatus(), status);
        assertEquals(s.getMajor(), major);
        assertEquals(s.getGradDate(), gradDate);
    }

    protected void checkCourseGrade(CourseGrade cg, int percentage, boolean cdf, String name, int credits) {
        boolean isCDF = cg instanceof CDF;
        assertEquals(cdf, isCDF);
        assertEquals(percentage, cg.getPercentage());
        checkCourse(cg.getCourse(), name, credits);
    }

    protected void checkCourse(Course c, String name, int credits) {
        assertEquals(c.getName(), name);
        assertEquals(c.getCredit(), credits);
    }
}
