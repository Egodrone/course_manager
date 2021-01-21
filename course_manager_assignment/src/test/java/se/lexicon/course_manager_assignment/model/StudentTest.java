package se.lexicon.course_manager_assignment.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import static org.junit.jupiter.api.Assertions.*;



public class StudentTest {



    @BeforeEach
    void setUp() {
        StudentSequencer.setStudentSequencer(0);
    }



    @Test
    public void test_setters_getters_id() {
        Student s1 = new Student();
        s1.setName("Rocky");
        s1.setEmail("rockyBalboa@gmail.com");
        s1.setAddress("Philadelphia");
        assertEquals("Rocky", s1.getName());
        assertEquals("rockyBalboa@gmail.com", s1.getEmail());
        assertEquals("Philadelphia", s1.getAddress());
        assertEquals(1, s1.getId());
    }



    @Test
    public void test_equals_hashCode() {
        Student x = new Student("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        Student y = new Student("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        // Different id's should give false
        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.hashCode() == y.hashCode());
    }



    @Test
    public void test_equals() {
        System.out.println();
        Student x = new Student("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        Student y = new Student("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        assertTrue(x.getName().equals(y.getName()) && y.getName().equals(x.getName()));
        assertTrue(x.getEmail().equals(y.getEmail()) && y.getEmail().equals(x.getEmail()));
        assertTrue(x.getAddress().equals(y.getAddress()) && y.getAddress().equals(x.getAddress()));
    }



    @Test
    public void test_toString() {
        Student x = new Student("Rocky", "rockyBalboa@gmail.com", "Philadelphia");
        String expected = "Student{id=1, name='Rocky', email='rockyBalboa@gmail.com', address='Philadelphia'}";
        System.out.println(x.toString());
        assertEquals(expected, x.toString());
    }



}
