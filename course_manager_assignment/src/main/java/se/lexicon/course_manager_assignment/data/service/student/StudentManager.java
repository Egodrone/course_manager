package se.lexicon.course_manager_assignment.data.service.student;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;

import java.util.ArrayList;
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

        //studentDao.createStudent(form.getName(), form.getEmail(), );
        //studentDao.createStudent();
        studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
        return new StudentView(form.getId(), form.getName(), form.getEmail(), form.getAddress());
    }



    @Override
    public StudentView update(UpdateStudentForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" UpdateStudentForm form is null ");
        }

        //StudentView sv = new StudentView(form.setId(), form.setName(), form.setEmail(), form.setAddress());
        return null;
    }



    @Override
    public StudentView findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(" Invalid id value ");
        }

        StudentView sv = null;

        return null;
    }



    @Override
    public StudentView searchByEmail(String email) {

        if (email == null) {
            throw new IllegalArgumentException(" Email value is null ");
        }
        if (email.equals("")) {
            throw new IllegalArgumentException(" Email value is empty ");
        }

        return null;
    }



    @Override
    public List<StudentView> searchByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException(" name value is null ");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException(" name value is empty ");
        }

        List<StudentView> stv = new ArrayList<>();

        return null;
    }



    @Override
    public List<StudentView> findAll() {
        List<StudentView> result = new ArrayList<>();

        //return result;
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
