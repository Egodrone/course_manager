package se.lexicon.course_manager_assignment.data.service.converter;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.lexicon.course_manager_assignment.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;



@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {



    @Autowired
    private Converters testObject;



    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }



    @Test
    public void test_studentToStudentView() {
        ModelToDto mtd = new ModelToDto();
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Rocky", "rockyBalboa@gmail.com",
                "Philadelphia");
        String expected = "Student{id=1, name='Rocky', email='rockyBalboa@gmail.com', address='Philadelphia'}";
        assertEquals(s1.toString(), expected);

        StudentView stv = testObject.studentToStudentView(s1);
        assertEquals("Rocky", stv.getName());
    }



    @Test
    public void test_courseToCourseView() {
        Collection<Course> courses = new HashSet<>();
        Collection<Student> students = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Course c1 = courseRepository.createCourse("Java", date, 10);
        Course c2 = courseRepository.createCourse("Python", date2, 19);

        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Stefan", "Stefan@gmail.com", "Park street");
        Student s2 = studentRepository.createStudent("Boris", "boris@gmail.com", "Auerbach Avenue");

        boolean actual = c1.enrollStudent(s1);
        boolean actual2 = c2.enrollStudent(s2);

        assertTrue(actual && actual2);

        ModelToDto mtd = new ModelToDto();
        CourseView cov = testObject.courseToCourseView(c1);

        assertEquals("Java", cov.getCourseName());
        assertEquals(10, cov.getWeekDuration());
    }



    @Test
    public void test_coursesToCourseViews() {
        Collection<Course> courses = new HashSet<>();
        CourseCollectionRepository courseRepository = new CourseCollectionRepository(courses);
        LocalDate date = LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        courseRepository.createCourse("Java", date, 15);
        courseRepository.createCourse("Python", date, 10);
        courseRepository.createCourse("BASH", date, 30);

        ModelToDto mtd = new ModelToDto();
        List<CourseView> cov = testObject.coursesToCourseViews(courses);
        assertEquals(3, cov.size());
    }



    @Test
    public void test_studentsToStudentViews() {

        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        studentRepository.createStudent("Albert", "test@gmail.com", "Philadelphia");
        studentRepository.createStudent("Sven", "another@gmail.com", "New street");
        studentRepository.createStudent("Lena", "lena@gmail.com", "Robert street");
        Collection<Student> actual = studentRepository.findAll();

        ModelToDto mtd = new ModelToDto();

        List<StudentView> stv = testObject.studentsToStudentViews(actual);
        assertEquals(3, stv.size());
    }



}
