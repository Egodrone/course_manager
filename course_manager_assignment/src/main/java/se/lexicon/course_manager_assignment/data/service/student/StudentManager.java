package se.lexicon.course_manager_assignment.data.service.student;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;



    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }



    @Override
    public StudentView create(CreateStudentForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" CreateStudentForm form is null ");
        }

        Student student = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
        return converters.studentToStudentView(student);
    }



    @Override
    public StudentView update(UpdateStudentForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" UpdateStudentForm form is null ");
        }

        if (form.getId() > 0) {
            Student updateStudent = studentDao.findById(form.getId());
            updateStudent.setName(form.getName());
            updateStudent.setEmail(form.getEmail());
            updateStudent.setAddress(form.getAddress());

            return converters.studentToStudentView(updateStudent);
        }

        return null;
    }



    @Override
    public StudentView findById(int id) {

        if (id < 1) {
            throw new IllegalArgumentException(" Invalid id value ");
        }

        Student findStudentById = studentDao.findById(id);

        return converters.studentToStudentView(findStudentById);
    }



    @Override
    public StudentView searchByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException(" Email value is null ");
        }
        if (email.equals("")) {
            throw new IllegalArgumentException(" Email value is empty ");
        }

        Student findStudentByEmail = studentDao.findByEmailIgnoreCase(email);

        return converters.studentToStudentView(findStudentByEmail);
    }



    @Override
    public List<StudentView> searchByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException(" name value is null ");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException(" name value is empty ");
        }

        Collection<Student> findStudentByName = studentDao.findByNameContains(name);

        return converters.studentsToStudentViews(findStudentByName);
    }



    @Override
    public List<StudentView> findAll() {
        List<StudentView> result = new ArrayList<>();

        return null;
    }



    @Override
    public boolean deleteStudent(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(" Invalid id value ");
        }

        boolean isDeleted = false;

        return isDeleted;
    }



}
