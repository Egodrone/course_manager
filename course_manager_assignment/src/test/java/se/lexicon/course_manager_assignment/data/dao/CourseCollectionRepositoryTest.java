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
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = courseRepository.createCourse("Java", date, 10);
        String expected = "Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=10, students=[]}";
        assertEquals(c1.toString(), expected);

    }



    @Test
    public void test_findById() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date, 10);
        courseRepository.createCourse("BASH", date, 30);
        Course c1 = courseRepository.findById(1);
        String expected = "Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=15, students=[]}";
        assertEquals(c1.toString(), expected);

        // Test to find another course
        c1 =  courseRepository.findById(3);
        expected = "Course{id=3, courseName='BASH', startDate=2021-01-21, weekDuration=30, students=[]}";
        assertEquals(c1.toString(), expected);
    }



    @Test
    public void test_findByNameContains() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date, 10);
        courseRepository.createCourse("BASH", date, 30);

        Collection<Course> actual = courseRepository.findByNameContains("Python");
        assertEquals(1, actual.size());

        // Test non existing course
        actual = courseRepository.findByNameContains("C++");
        assertEquals( 0, actual.size());
    }



    @Test
    public void test_findAll() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date, 10);
        courseRepository.createCourse("BASH", date, 30);

        Collection<Course> actual = courseRepository.findAll();
        int expected = 3;
        assertEquals( expected, actual.size());
    }



    @Test
    public void test_findByDateBefore() {
        //todo
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate testDate = LocalDate.parse("2021-01-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date2, 10);

        Collection<Course> courseB = courseRepository.findByDateBefore(testDate);
        String expectedStr = "[Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=15, students=[]}, " +
                "Course{id=2, courseName='Python', startDate=2021-01-13, weekDuration=10, students=[]}]";
        //assertEquals(2, courseB.size());
        //assertEquals(expectedStr, courseB.toString());

    }



    @Test
    public void test_findByStudentId() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = courseRepository.createCourse("Java", date, 10);
        Course c2 = courseRepository.createCourse("Python", date2, 19);

        Student s1 = new Student();
        s1.setName("Stefan");
        s1.setEmail("Stefan@gmail.com");
        s1.setAddress("Test street");
        Student s2 = new Student();
        s2.setName("Boris");
        s2.setEmail("boris@gmail.com");
        s2.setAddress("Boris street");

        boolean actual = c1.enrollStudent(s1);
        boolean actual2 = c2.enrollStudent(s2);
        assertTrue(actual);
        assertTrue(actual2);
        assertEquals(c1.getCourseName(), "Java");

        Collection<Course> studentSearch = courseRepository.findByStudentId(1);
        String expected = "[Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=10, " +
                "students=[Student{id=1, name='Stefan', email='Stefan@gmail.com', address='Test street'}]}]";
        assertEquals(expected, studentSearch.toString());

    }



    @Test
    public void test_removeCourse() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = courseRepository.createCourse("Java", date, 15);
        Course c2 = courseRepository.createCourse("Python", date2, 10);

        boolean test = courseRepository.removeCourse(c1);
        assertTrue(test);
        Collection<Course> actual = courseRepository.findAll();
        assertEquals(1, actual.size());
        test = courseRepository.removeCourse(c2);
        assertTrue(test);
        actual = courseRepository.findAll();
        assertEquals(0, actual.size());
    }



    @Test
    public void test_findByDateAfter() {
        //todo
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-19", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate testDate = LocalDate.parse("2020-01-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date2, 10);

        Collection<Course> courseB = courseRepository.findByDateAfter(testDate);
        String expectedStr = "[Course{id=1, courseName='Java', startDate=2021-01-21, weekDuration=15, students=[]}, " +
                "Course{id=2, courseName='Python', startDate=2021-01-13, weekDuration=10, students=[]}]";
        //assertEquals(2, courseB.size());
        //assertEquals(expectedStr, courseB.toString());
    }



    @Test
    public void test_clear() {
        int expected = 0;
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date, 10);
        courseRepository.createCourse("BASH", date, 30);

        Collection<Course> actual = courseRepository.findAll();
        assertEquals(3, actual.size());
        courseRepository.clear();
        actual = courseRepository.findAll();
        assertEquals(expected, actual.size());
    }



    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }



}
