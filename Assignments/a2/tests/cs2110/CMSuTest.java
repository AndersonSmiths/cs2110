package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CMSuTest {


    @DisplayName("WHEN a student is enrolled in two overlapping courses, " +
            "THEN, hasConflict() should return true, otherwise false.")
    @Test
    void testHasConflict() {

        // student does have conflict
        {
            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 10, 30, 60);
            cms.addCourse(c);
            cms.addCourse(c2);

            Student anderson = new Student("Anderson", "Smith");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            cms.addStudent(anderson);

            assertEquals(true, cms.hasConflict(anderson));
        }

        // student has no conflict
        {
            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 12, 30, 60);
            cms.addCourse(c);
            cms.addCourse(c2);

            Student anderson = new Student("Anderson", "Smith");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            cms.addStudent(anderson);

            assertEquals(true, cms.hasConflict(anderson));
        }


    }

    @DisplayName("WHEN students are enrolled in too many credits in the CMSu system" +
            "THEN auditCredits() should return a list of said students.")
    @Test
    void testAuditCredits() {

        // has too many credits
        {
            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 12, 30, 60);
            cms.addCourse(c);
            cms.addCourse(c2);

            Student anderson = new Student("Anderson", "Smith");
            Student faith = new Student("Faith", "Lawson");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            c.enrollStudent(faith);
            c2.enrollStudent(faith);
            cms.addStudent(anderson);
            cms.addStudent(faith);

            StudentSet overLimit = cms.auditCredits(7);


            assertEquals(2, overLimit.size());
        }

        // no students with too many credits
        {
            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 12, 30, 60);
            cms.addCourse(c);
            cms.addCourse(c2);

            Student anderson = new Student("Anderson", "Smith");
            Student faith = new Student("Faith", "Lawson");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            c.enrollStudent(faith);
            c2.enrollStudent(faith);
            cms.addStudent(anderson);

            StudentSet overLimit = cms.auditCredits(9);


            assertEquals(0, overLimit.size());
        }

    }

    @DisplayName("WHEN the number of credits that each student tracked by this CMS thinks they are" +
            "enrolled in matches the total of the credits offered by each course managed by this CMS that considers the student to be enrolled" +
    "THEN, this method should return true, else false.")
    @Test
    void testCreditConsistency() {
        // true case
        {
            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 12, 30, 60);
            cms.addCourse(c);
            cms.addCourse(c2);

            Student anderson = new Student("Anderson", "Smith");
            Student faith = new Student("Faith", "Lawson");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            c.enrollStudent(faith);
            c2.enrollStudent(faith);
            cms.addStudent(anderson);

            assertEquals(true, cms.checkCreditConsistency());

        }

        // false case
        {

            CMSu cms = new CMSu();
            Course c = new Course("CS1112", 4, "Fan", "Baker", 10, 0, 60);
            Course c2 = new Course("CS1112", 4, "Fan", "Baker", 12, 30, 60);
            cms.addCourse(c);

            Student anderson = new Student("Anderson", "Smith");
            Student faith = new Student("Faith", "Lawson");
            c.enrollStudent(anderson);
            c2.enrollStudent(anderson);
            c.enrollStudent(faith);
            c2.enrollStudent(faith);
            cms.addStudent(anderson);

            // becasuse c2 was not added to cms, should return false
            assertEquals(false, cms.checkCreditConsistency());
        }
    }


}