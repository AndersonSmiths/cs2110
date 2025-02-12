package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {

    // display name not added because it was not added in studentSetTest
    @Test
    void testConstructor() {
        // typical case
        Course c = new Course("CS1112", 4, "Fan", "Baker", 8, 30, 75);
        assertEquals("CS1112", c.title());
        assertEquals(4, c.credits());
        assertEquals("Baker", c.location());

    }
    // TODO 14: Write a test case that covers `Course`'s constructor and the observers that have
    // already been implemented in the release code.  Confirm that the case initially fails, then
    // complete TODOs 15-16 and return here to confirm that your case now passes.
    // As you proceed with TODOs 17-22, start by adding a test case here for the method being
    // implemented, then repeat the above (confirm that it fails, implement the method, confirm that
    // it passes).  This TODO is complete when there are test cases covering all of `Course`'s
    // public methods.
    // Be sure to verify any effects on objects of other classes as well.

    @DisplayName("WHEN the instrucor method is called, " +
            "THEN, it should have the format 'Professor, LastName")
    @Test
    void testInstructor() {
        Course c = new Course("CS1112", 4, "Fan", "Baker", 8, 30, 75);
        assertEquals("Professor Fan", c.instructor());
    }

    @DisplayName("WHEN the formatStartTime method is called " +
    "THEN, it should have the format HR:MIN AM/PM")
    @Test

    void testFormatStartTime() {
        // AM Test case
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 8, 30, 75);
            assertEquals("8:30 AM", c.formatStartTime());
        }

        // PM Test case
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 16, 30, 75);
            assertEquals("4:30 PM", c.formatStartTime());
        }

    }

    @DisplayName("WHEN two classes are overlapping by at least one minute, " +
    "THEN, overlaps(c) should return false. If one course ends when the other one" +
            "starts, they do NOT overlap.")
    @Test
    void testOverlap() {

        // standard test case
        {

            Course c = new Course("CS1112", 4, "Fan", "Baker", 11, 30, 75);
            Course c2 = new Course("CS1110", 4, "Fowler", "Baker", 11, 0, 60);
            assertEquals(true, c.overlaps(c2));
        }

        // edge case
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1110", 4, "Fowler", "Baker", 11, 0, 60);
            assertEquals(false, c.overlaps(c2));
        }
    }

    @DisplayName("WHEN, enrollStudent(s) is called, " +
            "THEN, it should return true if the student was successfully added and false if unsuccessful.")
    @Test
    void testEnrollStudent() {

        // successful enrollment
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Student anderson = new Student("Anderson", "Smith");
            assertEquals(true, c.enrollStudent(anderson));
        }

        // unsuccessful, redundant enrollment
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Student anderson = new Student("Anderson", "Smith");
            c.enrollStudent(anderson);
            // anderson is already enrolled, so should return false
            assertEquals(false, c.enrollStudent(anderson));
        }


    }

    @DisplayName("WHEN the hasStudent() method is called, " +
            "THEN it should return true if the student is already enrolled in the class and false if otherwise.")
    @Test
    void testHasStudent() {
        // already enrolled
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Student anderson = new Student("Anderson", "Smith");
            c.enrollStudent(anderson);
            assertEquals(true, c.hasStudent(anderson));
        }

        // not enrolled
        {
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Student anderson = new Student("Anderson", "Smith");
            assertEquals(false, c.hasStudent(anderson));
        }
    }

    @DisplayName("WHEN, dropStudent is called, " +
            "THEN it should reflect on both the courses enrollmentm, and the students credit horus")
    @Test
    void testDrop() {
        Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
        Student anderson = new Student("Anderson", "Smith");
        c.enrollStudent(anderson);

        // testing credit hour adjustment
        {
            c.dropStudent(anderson);
            assertEquals(0, anderson.credits());
        }

        // testing enrollment adjustment
        {
            c.dropStudent(anderson);
            assertEquals(false, c.hasStudent(anderson));
        }

        // if student is not in course, then should be false
        {
            c.dropStudent(anderson);
            assertEquals(false, c.dropStudent(anderson));
        }

    }


}
