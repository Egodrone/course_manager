package se.lexicon.course_manager_assignment.data.dao;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = {CourseCollectionRepository.class})



public class CourseCollectionRepositoryTest {



    @Autowired
    private CourseDao testObject;



    @BeforeEach
    void setUp() {
        CourseSequencer.setCourseSequencer(0);
        StudentSequencer.setStudentSequencer(0);
    }



    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }



    @Test
    public void test_createCourse() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = testObject.createCourse("Java", date, 10);
        String expected = "Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=10, students=[]}";
        assertEquals(c1.toString(), expected);

    }



    @Test
    public void test_findById() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date, 10);
        testObject.createCourse("BASH", date, 30);
        Course c1 = testObject.findById(1);
        String expected = "Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=15, students=[]}";
        assertEquals(c1.toString(), expected);

        // Test to find another course
        c1 =  testObject.findById(3);
        expected = "Course{id=3, courseName='BASH', startDate=2021-01-21, weekDuration=30, students=[]}";
        assertEquals(c1.toString(), expected);
    }



    @Test
    public void test_findByNameContains() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date, 10);
        testObject.createCourse("BASH", date, 30);

        Collection<Course> actual = testObject.findByNameContains("Python");
        assertEquals(1, actual.size());

        // Test non existing course
        actual = testObject.findByNameContains("C++");
        assertEquals( 0, actual.size());
    }



    @Test
    public void test_findAll() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date, 10);
        testObject.createCourse("BASH", date, 30);

        Collection<Course> actual = testObject.findAll();
        int expected = 3;
        assertEquals( expected, actual.size());
    }



    @Test
    public void test_findByDateBefore() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate testDate = LocalDate.parse("2022-01-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date2, 10);

        Collection<Course> courseB = testObject.findByDateBefore(testDate);
        String expectedStr = "[Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=15, students=[]}, " +
                "Course{id=2, courseName='Python', startDate=2021-01-13, weekDuration=10, students=[]}]";
        assertEquals(2, courseB.size());
        assertEquals(expectedStr, courseB.toString());

    }



    @Test
    public void test_findByStudentId() {
        Collection<Student> students = new HashSet<>();
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = testObject.createCourse("Java", date, 10);
        Course c2 = testObject.createCourse("Python", date2, 19);

        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Stefan", "Stefan@gmail.com", "Test street");
        Student s2 = studentRepository.createStudent("Boris", "boris@gmail.com", "Boris street");

        boolean actual = c1.enrollStudent(s1);
        boolean actual2 = c2.enrollStudent(s2);
        assertTrue(actual);
        assertTrue(actual2);
        assertEquals(c1.getCourseName(), "Java");

        Collection<Course> studentSearch = testObject.findByStudentId(1);
        String expected = "[Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=10, " +
                "students=[Student{id=1, name='Stefan', email='Stefan@gmail.com', address='Test street'}]}]";
        assertEquals(expected, studentSearch.toString());

    }



    @Test
    public void test_removeCourse() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = testObject.createCourse("Java", date, 15);
        Course c2 = testObject.createCourse("Python", date2, 10);

        boolean test = testObject.removeCourse(c1);
        assertTrue(test);
        Collection<Course> actual = testObject.findAll();
        assertEquals(1, actual.size());
        test = testObject.removeCourse(c2);
        assertTrue(test);
        actual = testObject.findAll();
        assertEquals(0, actual.size());
    }



    @Test
    public void test_findByDateAfter() {
        LocalDate date = LocalDate.parse("2001-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-19", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate testDate = LocalDate.parse("2009-01-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date2, 10);

        Collection<Course> courseB = testObject.findByDateAfter(testDate);
        String expectedStr = "[Course{id=2, courseName='Python', startDate=2021-01-19, weekDuration=10, students=[]}]";
        assertEquals(1, courseB.size());
        assertEquals(expectedStr, courseB.toString());
    }



    @Test
    public void test_clear() {
        int expected = 0;
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        testObject.createCourse("Java", date, 15);
        testObject.createCourse("Python", date, 10);
        testObject.createCourse("BASH", date, 30);

        Collection<Course> actual = testObject.findAll();
        assertEquals(3, actual.size());
        testObject.clear();
        actual = testObject.findAll();
        assertEquals(expected, actual.size());
    }



    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }



}
