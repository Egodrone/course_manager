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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;



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
        assertFalse(testObject == null);
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

        Collection<Course> expected = courseRepository.findByNameContains("Python");
        assertEquals(expected.size(), 1);

        // Test non existing course
        expected = courseRepository.findByNameContains("C++");
        assertEquals( 0, expected.size());
    }



    @Test
    public void test_findByDateBefore() {
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
        assertEquals(2, courseB.size());
        assertEquals(expectedStr, courseB.toString());

    }



    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }



}
