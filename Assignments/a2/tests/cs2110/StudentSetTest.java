package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentSetTest {
    @Test
    void testConstructorAndSize() {
        // Constructor should yield an empty set
        StudentSet students = new StudentSet();
        assertEquals(0, students.size());
    }

    @DisplayName("WHEN add() is called and the backing array is out of space, "
                 + "THEN a new backing array with twice the capacity and all the elements from the old array copied to it.")
    @Test
    void testAdd() {
        // tests to make sure resizing is done properly
        StudentSet set = new StudentSet();

        // creating list of 15 students so they can be added to set
        Student[] studentList = new Student[15];
        for (int i=0; i < studentList.length; i++) {
            studentList[i] = new Student("StudentFirst " + i, "StudentLast " + i);
        }

        // adding list of students to student set
        for (int i=0; i < studentList.length; i++) {
            set.add(studentList[i]);
        }

        // creating 16th student and adding should result in resizing
        // GLASS BOX TEST
        Student s = new Student("Anderson", "Smith");
        set.add(s);

        assertEquals(30, set.length());
    }

    @Test
    void testSize() {
        StudentSet set = new StudentSet();

        // creating list of 15 students so they can be added to set
        Student[] studentList = new Student[15];
        for (int i=0; i < studentList.length; i++) {
            studentList[i] = new Student("StudentFirst " + i, "StudentLast " + i);
        }

        // adding list of students to student set
        for (int i=0; i < studentList.length; i++) {
            set.add(studentList[i]);
        }

        // size-1 should be equal to set.length
        assertEquals(set.length(), set.size());
    }

    @DisplayName("WHEN contains(s) is called, "
            + "IF the element is in the array then True should be returned, else false.")
    @Test
    void testContains() {
        StudentSet set = new StudentSet();

        // creating list of 15 students so they can be added to set
        Student[] studentList = new Student[15];
        for (int i=0; i < studentList.length; i++) {
            studentList[i] = new Student("StudentFirst " + i, "StudentLast " + i);
        }

        // adding list of students to student set
        for (int i=0; i < studentList.length; i++) {
            set.add(studentList[i]);
        }

        assertEquals(true, set.contains(studentList[5]));
    }


    @DisplayName("WHEN remove(s) is called, "
            + "THEN contains(s) should return false")
    @Test
    void testRemove() {
        StudentSet set = new StudentSet();

        // creating list of 15 students so they can be added to set
        Student[] studentList = new Student[15];
        for (int i=0; i < studentList.length; i++) {
            studentList[i] = new Student("StudentFirst " + i, "StudentLast " + i);
        }

        // adding list of students to student set
        for (int i=0; i < studentList.length; i++) {
            set.add(studentList[i]);

        // removing student from the set and verifying it worked
        set.remove(studentList[5]);
        assertEquals(false, set.contains(studentList[5]));
        }

    }



}
