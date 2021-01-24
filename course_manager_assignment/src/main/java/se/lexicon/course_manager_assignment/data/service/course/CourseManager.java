package se.lexicon.course_manager_assignment.data.service.course;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.data.service.converter.ModelToDto;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;



    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }



    @Override
    public CourseView create(CreateCourseForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" Form object is null ");
        }

        courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());
        List<StudentView> sv = new ArrayList<>();
        //sv = converters.studentsToStudentViews(studentDao.findAll());
        System.out.println("Length: " + sv.size());

        CourseView cov = new CourseView(form.getId(),
                form.getCourseName(),
                form.getStartDate(),
                form.getWeekDuration(),
                sv);

        return cov;
    }



    @Override
    public CourseView update(UpdateCourseForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" Form object is null ");
        }

        //CourseView cov = new CourseView(form.getId(), form.getCourseName(), form.getWeekDuration());
        //return cov;

        return null;
    }



    @Override
    public List<CourseView> searchByCourseName(String courseName) {

        if (courseName == null) {
            throw new IllegalArgumentException(" String courseName is null ");
        }

        List<CourseView> covList = new ArrayList<>();

        return covList;
    }



    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {

        if (end == null) {
            throw new IllegalArgumentException(" LocalDate end is null ");
        }

        List<CourseView> covList = new ArrayList<>();

        return covList;
    }



    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {

        if (start == null) {
            throw new IllegalArgumentException(" LocalDate start is null ");
        }

        List<CourseView> covList = new ArrayList<>();

        return covList;
    }



    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {

        if (courseId <= 0) {
            throw new IllegalArgumentException(" courseId is not valid ");
        }
        if (studentId <= 0) {
            throw new IllegalArgumentException(" studentId is not valid ");
        }

        boolean isAdded = false;

        return isAdded;
    }



    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {

        if (courseId <= 0) {
            throw new IllegalArgumentException(" courseId is not valid ");
        }
        if (studentId <= 0) {
            throw new IllegalArgumentException(" studentId is not valid ");
        }

        boolean isRemoved = false;

        return isRemoved;
    }



    @Override
    public CourseView findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(" Invalid id value ");
        }
        if (id > 0) {
            System.out.println(" Find by id ");
        }
        //CourseView cov = new CourseView();

        return null;
    }



    @Override
    public List<CourseView> findAll() {
        List<CourseView> result = new ArrayList<>();

        /*
        for (CourseView cv : courseNames) {

        }*/

        return result;
    }



    @Override
    public List<CourseView> findByStudentId(int studentId) {

        if (studentId <= 0) {
            throw new IllegalArgumentException(" Invalid studentId value ");
        }

        List<CourseView> cov = new ArrayList<>();

        return cov;
    }



    @Override
    public boolean deleteCourse(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(" Invalid id value ");
        }

        boolean isDeleted = false;

        return isDeleted;
    }



}
