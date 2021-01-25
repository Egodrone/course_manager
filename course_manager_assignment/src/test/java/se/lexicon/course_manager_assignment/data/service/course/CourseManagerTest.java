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
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = {CourseManager.class, CourseCollectionRepository.class, ModelToDto.class, StudentCollectionRepository.class})
public class CourseManagerTest {



    @Autowired
    private CourseService testObject;



    @Autowired
    private CourseDao courseDao;




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
        // Create Course Form Object
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CreateCourseForm form = new CreateCourseForm(0, "Java", date, 10);
        CourseView cv = testObject.create(form);
        assertEquals(form.getCourseName(), cv.getCourseName());

    }



    @Test
    public void test_update() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // First creating a new course
        CreateCourseForm form1 = new CreateCourseForm(1, "Java", date, 10);
        CourseView cv = testObject.create(form1);
        assertEquals(form1.getCourseName(), cv.getCourseName());

        // Updating
        UpdateCourseForm form = new UpdateCourseForm(1, "Java Advanced", date, 12);
        CourseView cv2 = testObject.update(form);
        assertEquals(form.getCourseName(), cv2.getCourseName());
    }



    @Test
    public void test_searchByCourseName() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CreateCourseForm form1 = new CreateCourseForm(0, "Java", date, 30);
        CreateCourseForm form2 = new CreateCourseForm(0, "Python", date, 20);
        CourseView cv = testObject.create(form1);
        CourseView cv2 = testObject.create(form2);
        List<CourseView> courseViewList = testObject.searchByCourseName("Python");
        List<CourseView> expected = new ArrayList<>();
        expected.add(cv2);
        System.out.println(courseViewList.toString());
        assertTrue(courseViewList.equals(expected) && expected.equals(courseViewList));
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
        // Create Course Form Object
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CreateCourseForm form = new CreateCourseForm(0, "Java", date, 10);
        CreateCourseForm form2 = new CreateCourseForm(0, "Python", date, 12);
        CreateCourseForm form3 = new CreateCourseForm(0, "Cpp", date, 12);
        CourseView cv = testObject.create(form);
        testObject.create(form2);
        testObject.create(form3);
        assertEquals(form.getCourseName(), cv.getCourseName());

        // Find by id
        CourseView actual = testObject.findById(2);
        assertEquals(form2.getCourseName(), actual.getCourseName());

    }



    @Test
    public void test_findAll() {
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CreateCourseForm form1 = new CreateCourseForm(0, "Java", date, 30);
        CreateCourseForm form2 = new CreateCourseForm(0, "Python", date, 20);
        testObject.create(form1);
        testObject.create(form2);
        List<CourseView> courseViewList = testObject.findAll();
        System.out.println(courseViewList);
        assertEquals(2, courseViewList.size());
    }



}
