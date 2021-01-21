package se.lexicon.course_manager_assignment.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;



public class CourseTest {



    @BeforeEach
    void setUp() {
        CourseSequencer.setCourseSequencer(0);
        StudentSequencer.setStudentSequencer(0);
    }



    @Test
    public void test_sequencer() {
        int expected = 30;
        int counter = 0;

        while(counter < 30) {
            ++counter;
            expected = CourseSequencer.nextCourseId();
        }

        assertEquals(expected, 30);
    }



    @Test
    public void test_constructor1() {
        Course testCorse = new Course();
        assertEquals(testCorse.getId(), 1);
    }



    @Test
    public void test_constructor2() {
        Student s1 = new Student();
        s1.setName("Rocky");
        s1.setEmail("rockyBalboa@gmail.com");
        s1.setAddress("Philadelphia");
        Collection<Student> students = new HashSet<>();
        students.add(s1);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course testCorse = new Course("Java Advanced", date, 10, students);
        assertEquals(testCorse.getId(), 1);
        String expected = "[Student{id=1, name='Rocky', email='rockyBalboa@gmail.com', address='Philadelphia'}]";
        assertEquals(testCorse.getStudents().toString(), expected);
    }



    @Test
    public void test_getCourseName() {
        Student s1 = new Student();
        s1.setName("Rocky");
        s1.setEmail("rockyBalboa@gmail.com");
        s1.setAddress("Philadelphia");
        Collection<Student> students = new HashSet<>();
        students.add(s1);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course testCorse = new Course("Java Advanced", date, 10, students);
        assertEquals(testCorse.getId(), 1);
        String expected = "Java Advanced";
        assertEquals(testCorse.getCourseName(), expected);
    }



    @Test
    public void test_getCourseName2() {
        Student s1 = new Student();
        s1.setName("Rocky");
        s1.setEmail("rockyBalboa@gmail.com");
        s1.setAddress("Philadelphia");
        Student s2 = new Student();
        s2.setName("Adrian");
        s2.setEmail("adrian@gmail.com");
        s2.setAddress("New Zealand");
        Collection<Student> students = new HashSet<>();
        students.add(s1);
        students.add(s2);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course testCorse = new Course("Java Advanced", date, 10, students);
        assertEquals(testCorse.getId(), 1);
        String expected = "Java Advanced";
        for (Student s : students) {
            System.out.println(s.toString());
        }
        assertEquals(testCorse.getCourseName(), expected);
    }



    @Test
    public void test_enrollStudent() {
        Student s1 = new Student();
        s1.setName("Stefan");
        s1.setEmail("Stefan@gmail.com");
        s1.setAddress("Test street");
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course course = new Course("CSS", date, 10);
        boolean actual = course.enrollStudent(s1);
        assertTrue(actual);
        assertEquals(course.getCourseName(), "CSS");
    }



    @Test
    public void test_unenrollStudent() {

        Student s1 = new Student();
        s1.setName("Erik");
        s1.setEmail("erik@gmail.com");
        s1.setAddress("Erik street");
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course course = new Course("Python3", date, 10);
        boolean actual = course.enrollStudent(s1);

        //add student to the course
        assertTrue(actual);
        assertEquals(course.getCourseName(), "Python3");

        //remove student from the course
        actual = course.unenrollStudent(s1);
        assertTrue(actual);

        //Test to remove non existing student
        actual = course.unenrollStudent(null);
        assertFalse(actual);
    }





}
