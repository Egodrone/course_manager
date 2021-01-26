package se.lexicon.course_manager_assignment.data.service.student;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;
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

        if (form != null) {
            Student student = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());

            return converters.studentToStudentView(student);
        }

        return null;
    }



    @Override
    public StudentView update(UpdateStudentForm form) {

        if (form != null) {
            if (form.getId() > 0) {
                Student updateStudent = studentDao.findById(form.getId());
                updateStudent.setName(form.getName());
                updateStudent.setEmail(form.getEmail());
                updateStudent.setAddress(form.getAddress());

                return converters.studentToStudentView(updateStudent);
            }
        }

        return null;
    }



    @Override
    public StudentView findById(int id) {

        if (id > 0) {
            Student findStudentById = studentDao.findById(id);

            return converters.studentToStudentView(findStudentById);
        }

        return null;
    }



    @Override
    public StudentView searchByEmail(String email) {

        if (email != null && !email.equals("")) {
            Student findStudentByEmail = studentDao.findByEmailIgnoreCase(email);

            return converters.studentToStudentView(findStudentByEmail);
        }

        return null;
    }



    @Override
    public List<StudentView> searchByName(String name) {

        if (name != null && !name.equals("")) {
            Collection<Student> findStudentByName = studentDao.findByNameContains(name);

            return converters.studentsToStudentViews(findStudentByName);
        }

        return null;
    }



    @Override
    public List<StudentView> findAll() {
        Collection<Student> result = studentDao.findAll();

        return converters.studentsToStudentViews(result);
    }



    @Override
    public boolean deleteStudent(int id) {

        if (id > 0) {
            Student removeStudent = studentDao.findById(id);

            return studentDao.removeStudent(removeStudent);
        }

        return false;
    }



}
