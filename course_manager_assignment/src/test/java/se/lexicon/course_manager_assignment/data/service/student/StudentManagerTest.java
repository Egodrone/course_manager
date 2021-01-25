package se.lexicon.course_manager_assignment.data.service.student;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest(classes = {StudentManager.class, CourseCollectionRepository.class, StudentCollectionRepository.class, ModelToDto.class})
public class StudentManagerTest {



    @Autowired
    private StudentService testObject;
    @Autowired
    private StudentDao studentDao;



    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
        assertNotNull(studentDao);
    }



    @Test
    public void test_create() {
        CreateStudentForm studentForm = new CreateStudentForm(0, "Joe", "joe@gmail.com", "New Zealand");
        StudentView testStudent = testObject.create(studentForm);
        assertEquals("Joe", testStudent.getName());
        assertEquals("joe@gmail.com", testStudent.getEmail());
    }



    @Test
    public void test_update() {
        CreateStudentForm studentForm = new CreateStudentForm(0, "Joe", "joe@gmail.com", "New Zealand");
        CreateStudentForm studentForm2 = new CreateStudentForm(0, "Annie", "annie@gmail.com", "Annie Street");
        CreateStudentForm studentForm3 = new CreateStudentForm(0, "Felicia", "felicia@gmail.com", "North Street");
        testObject.create(studentForm);
        testObject.create(studentForm2);
        testObject.create(studentForm3);

        UpdateStudentForm updateStudentForm = new UpdateStudentForm(3, "Felicia", "fel2@gmail.com", "North Street");
        StudentView testStudent = testObject.update(updateStudentForm);
        assertEquals("fel2@gmail.com", testStudent.getEmail());
    }



    @Test
    public void test_findById() {

    }



    @Test
    public void test_searchByEmail() {

    }



    @Test
    public void test_searchByName() {

    }



    @Test
    public void test_findAll() {

    }



    @Test
    public void test_deleteStudent() {

    }



    @AfterEach
    void tearDown() {
        StudentSequencer.setStudentSequencer(0);
        studentDao.clear();
    }



}
