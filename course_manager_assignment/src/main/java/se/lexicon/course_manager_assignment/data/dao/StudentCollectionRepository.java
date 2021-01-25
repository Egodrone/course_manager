package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;
import java.util.*;



public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;



    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }



    @Override
    public Student createStudent(String name, String email, String address) {
        int id = StudentSequencer.nextStudentId();
        Student s1 = new Student(id, name, email, address);
        students.add(s1);

        return s1;
    }



    @Override
    public Student findByEmailIgnoreCase(String email) {

        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return s;
            }
        }

        return null;
    }



    @Override
    public Collection<Student> findByNameContains(String name) {
        Collection<Student> result = new HashSet<>();

        for (Student s : students) {
            if (s.getName().contains(name)) {
                result.add(s);
            }
        }

        return result;
    }



    @Override
    public Student findById(int id) {

        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }



    @Override
    public Collection<Student> findAll() {
        Collection<Student> result = new HashSet<>();

        for (Student s : students) {
                result.add(s);
        }

        return result;
    }



    @Override
    public boolean removeStudent(Student student) {
        boolean isDelete = false;

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student result = iterator.next();
            if (result.equals(student)) {
                iterator.remove();
                isDelete = true;
            }
        }

        return isDelete;
    }



    @Override
    public void clear() {
        this.students = new HashSet<>();
    }



}
