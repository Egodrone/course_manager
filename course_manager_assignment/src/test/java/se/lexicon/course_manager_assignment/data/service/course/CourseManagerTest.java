package se.lexicon.course_manager_assignment.data.service.course;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {



    @Autowired
    private CourseService testObject;



    @Autowired
    private CourseDao courseDao;
    private StudentDao studentDao;
    private Converters converters;
    private Object CourseDao;
    private Object StudentDao;
    private Object Converters;



    @Test
    @DisplayName(" Test context successfully setup ")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(courseDao);
    }



    @AfterEach
    void tearDown() {
        courseDao.clear();
        CourseSequencer.setCourseSequencer(0);
    }



    @Test
    public void test_create() {
        CourseManager cm = new CourseManager(courseDao, studentDao, converters);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);

        Course c1 = courseRepository.createCourse("Java", date, 10);
        //Course c2 = courseRepository.createCourse("Python", date2, 19);

        Student s1 = new Student("Eddie", "ed@gmail.com", "Java street");
        Student s2 = new Student("Tanja", "tanja@mail.com", "Python street");

        System.out.println(s1.getName());

        boolean actual = c1.enrollStudent(s1);
        boolean actual2 = c1.enrollStudent(s2);

        assertTrue(actual && actual2);

        CreateCourseForm form = new CreateCourseForm(1, "Java", date, 10);
        CourseView testCreatedCourseForm = cm.create(form);

        //System.out.println(testCreatedCourseForm.toString());
        //System.out.println(testCreatedCourseForm.getCourseName());
        //todo: student list should be added, right now it is empty
        System.out.println(testCreatedCourseForm.getStudents().toString());

        assertEquals("Java", testCreatedCourseForm.getCourseName());
    }



    @Test
    public void test_update() {

    }



    @Test
    public void test_searchByCourseName() {

    }



    @Test
    public void test_searchByDateBefore() {

    }



    @Test
    public void test_searchByDateAfter() {

    }



    @Test
    public void test_addStudentToCourse() {

    }



    @Test
    public void test_removeStudentFromCourse() {

    }



    @Test
    public void test_findById() {

    }



    @Test
    public void test_findAll() {

    }



}
