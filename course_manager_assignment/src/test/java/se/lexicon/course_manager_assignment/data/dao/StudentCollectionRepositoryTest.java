package se.lexicon.course_manager_assignment.data.dao;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest(classes = {StudentCollectionRepository.class})



public class StudentCollectionRepositoryTest {



    @Autowired
    private StudentDao testObject;



    @BeforeEach
    void setUp() {
        CourseSequencer.setCourseSequencer(0);
        StudentSequencer.setStudentSequencer(0);
    }



    @Test
    @DisplayName(" Test context successfully setup ")
    void context_loads() {
        assertNotNull(testObject);
    }



    @Test
    public void test_StudentCollectionRepository() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);

        Student s1 = studentRepository.createStudent("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        String expectedObj = "Student{id=1, name='Rocky', email='rockyBalboa@gmail.com', address='Philadelphia'}";
        assertEquals(expectedObj, s1.toString());
    }



    @Test
    public void test_StudentCollectionRepository2() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Test", "test@gmail.com", "Philadelphia");
        String expected = "Student{id=1, name='Test', email='test@gmail.com', address='Philadelphia'}";
        assertEquals(expected, s1.toString());
    }



    @Test
    public void test_findByEmailIgnoreCase() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Alfredo", "alfredo@gmail.com",
                "Left turn street");
        Student actual = studentRepository.findByEmailIgnoreCase("alfredo@gmail.com");
        assertEquals(s1.toString(), actual.toString());

        //Test invalid email
        actual = studentRepository.findByEmailIgnoreCase("wrongEmail@gmail.com");
        assertNull(actual);
    }



    @Test
    public void test_findByNameContains() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        studentRepository.createStudent("Albert", "albert@gmail.com", "Philadelphia");
        studentRepository.createStudent("Albert", "albert_staffan@gmail.com", "New street");
        studentRepository.createStudent("Robert", "robert@gmail.com", "Robert street");
        Collection<Student> actual = studentRepository.findByNameContains("Albert");
        //Expected 2 students
        assertEquals(2, actual.size());

        // Test with name that does not exist
        actual = studentRepository.findByNameContains("NotExistingName");
        assertEquals(0, actual.size());
    }



    @Test
    public void test_findById() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        studentRepository.createStudent("Albert", "albert@gmail.com", "Philadelphia");
        studentRepository.createStudent("Sven", "sven@gmail.com", "New street");
        studentRepository.createStudent("Robert", "robert@gmail.com", "Robert street");
        Student actual = studentRepository.findById(3);
        String expected  = "Student{id=3, name='Robert', email='robert@gmail.com', address='Robert street'}";
        assertEquals(expected, actual.toString());
    }



    @Test
    public void test_findAll() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        studentRepository.createStudent("Albert", "test@gmail.com", "Philadelphia");
        studentRepository.createStudent("Sven", "another@gmail.com", "New street");
        studentRepository.createStudent("Lena", "lena@gmail.com", "Robert street");
        Collection<Student> actual = studentRepository.findAll();
        assertEquals(3, actual.size());
    }



    @Test
    public void test_removeStudent() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        Student s1 = studentRepository.createStudent("Albert", "test@gmail.com", "Philadelphia");
        Student s2 = studentRepository.createStudent("Sven", "another@gmail.com", "New street");
        studentRepository.createStudent("Lena", "lena@gmail.com", "Robert street");
        boolean actual = studentRepository.removeStudent(s2);
        assertTrue(actual);

        actual = studentRepository.removeStudent(s1);
        assertTrue(actual);

        Collection<Student> actualSize = studentRepository.findAll();
        assertEquals(1, actualSize.size());
    }



    @Test
    public void test_clear() {
        Collection<Student> students = new HashSet<>();
        StudentCollectionRepository studentRepository = new StudentCollectionRepository(students);
        studentRepository.createStudent("Albert", "test@gmail.com", "Philadelphia");
        studentRepository.createStudent("Sven", "another@gmail.com", "New street");
        studentRepository.createStudent("Lena", "lena@gmail.com", "Robert street");
        studentRepository.clear();
        Collection<Student> actual = studentRepository.findAll();
        assertEquals(0, actual.size());
    }



    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }



}
