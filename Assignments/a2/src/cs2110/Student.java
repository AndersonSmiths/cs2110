package cs2110;

/**
 * A student tracked by the CMSμ course management system.
 */
public class Student {
    // TODO 1: Add fields and write the class invariant.
    // Declare fields to represent the following pieces of state.  Your fields will hold information
    // describing each Student.  Make the fields private and include comments describing how each
    // one relates to the class's state and how their values are constrained (the class invariant).
    // If fields are never reassigned by the class's methods, they should be declared `final`.
    // * First name of this Student (may not be empty or null).
    // * Last name of this Student (may not be empty or null).
    // * Number of credits student is currently enrolled in (integer; may not be negative).
    // The number of credits is also involved in an invariant of the `CMSu` class:
    // it must be equal to the sum of the credits of all Courses this student is enrolled in.  This
    // invariant cannot be checked or enforced within `Student` itself, but other code in the
    // package must be aware of it and respect it.
    // Reminder: You should delete this comment block after completing the TODO.  You are welcome to
    // borrow text from it when writing your field specifications, however.

    /**
     * The first name of a student. Not empty or null.
     */
    private final String firstName;

    /**
     * The last name of a student. Not empty or null.
     */
    private final String lastName;

    /**
     * The number of credits a student is currently enrolled in.
     * Non-negative integer
     */

    private int creditsEnrolled = 0; // not final because subject to add/drop courses

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {

        // enrolled credits may not be negative
        assert creditsEnrolled >= 0;

        // first and last name cannot be empty or null
        assert (firstName != null) && !(firstName.isEmpty()); // originally used firstName.length != 0, changed to isEmpty via intellij suggestion
        assert (lastName != null) && !(lastName.isEmpty());

    }

    /**
     * Create a new Student with first name `firstName` and last name `lastName` who is not enrolled
     * for any credits.  Requires firstName and lastName are not empty.
     */
    public Student(String firstName, String lastName) {

        // ensure preconditions are met
        assert !(firstName.isEmpty());
        assert !(lastName.isEmpty());

        // initialization
        this.firstName = firstName;
        this.lastName = lastName;

        assertInv();
    }

    /**
     * Return the first name of this Student.  Will not be empty.
     */
    public String firstName() {
        assert !(firstName.isEmpty());

        assertInv();
        return this.firstName;
    }

    /**
     * Return the last name of this Student.  Will not be empty.
     */
    public String lastName() {
        assert !(lastName.isEmpty());

        assertInv();
        return this.lastName;
    }

    /**
     * Return the full name of this student, formed by joining their first and last names separated
     * by a space.
     */
    public String fullName() {
        // Observe that, by invoking methods instead of referencing this fields, this method was
        // implemented without knowing how you will name your fields.
        assert !(firstName.isEmpty());
        assert !(lastName.isEmpty());

        assertInv();
        return firstName() + " " + lastName();
    }

    /**
     * Return the number of credits this student is currently enrolled in.  Will not be negative.
     */
    public int credits() {
        assert this.creditsEnrolled>= 0;

        assertInv();
        return this.creditsEnrolled;
    }

    /**
     * Change the number of credits this student is enrolled in by `deltaCredits`. For example, if
     * this student were enrolled in 12 credits, then `this.adjustCredits(3)` would result in their
     * credits changing to 15, whereas `this.adjustCredits(-4)` would result in their credits
     * changing to 8.  Requires that the change would not cause the student's credits to become
     * negative.
     */
    void adjustCredits(int deltaCredits) {
        // ensures that credit adjustment does not violate preconditon
        int dif = this.creditsEnrolled + deltaCredits;
        assert dif >= 0;

        // adjustment of credits operation
        this.creditsEnrolled += deltaCredits;
        assertInv();
    }

    /**
     * Return the full name of this student as its string representation.
     */
    @Override
    public String toString() {
        return fullName();
    }
}
