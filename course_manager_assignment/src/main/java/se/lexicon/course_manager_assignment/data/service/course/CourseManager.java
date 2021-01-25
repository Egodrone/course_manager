package se.lexicon.course_manager_assignment.data.service.course;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;
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

        if (form != null) {
            Course createdCourse = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());

            return converters.courseToCourseView(createdCourse);
        }

        return null;
    }



    @Override
    public CourseView update(UpdateCourseForm form) {

        if (form != null) {

            if (form.getId() > 0) {
                Course updateCourse = courseDao.findById(form.getId());
                updateCourse.setCourseName(form.getCourseName());
                updateCourse.setStartDate(form.getStartDate());
                updateCourse.setWeekDuration(form.getWeekDuration());

                return converters.courseToCourseView(updateCourse);
            }

        }

        return null;
    }



    @Override
    public List<CourseView> searchByCourseName(String courseName) {

        if (courseName != null) {
            Collection<Course> allCourses = courseDao.findAll();
            List<CourseView> covList = converters.coursesToCourseViews(allCourses);
            List<CourseView> result = new ArrayList<>();

            for (CourseView c : covList) {

                if (c.getCourseName().equalsIgnoreCase(courseName)) {
                    result.add(c);
                }

            }

            return result;
        }

        return null;
    }



    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {

        if (end != null) {
            Collection<Course> allCourses = courseDao.findByDateBefore(end);

            return converters.coursesToCourseViews(allCourses);
        }

        return null;
    }



    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {

        if (start != null) {
            Collection<Course> allCourses = courseDao.findByDateAfter(start);

            return converters.coursesToCourseViews(allCourses);
        }

        return null;
    }



    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {

        if (courseId > 0 && studentId > 0) {
            return courseDao.findById(courseId).enrollStudent(studentDao.findById(studentId));
        }

        return false;
    }



    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {

        if (courseId > 0 && studentId > 0) {
            return courseDao.findById(courseId).unenrollStudent(studentDao.findById(studentId));
        }

        return false;
    }



    @Override
    public CourseView findById(int id) {

        if (id > 0) {
            Collection<Course> allCourses = courseDao.findAll();
            List<CourseView> covList  = converters.coursesToCourseViews(allCourses);

            for (CourseView c: covList) {
                if (c.getId() == id) {
                    return c;
                }
            }
        }

        return null;
    }



    @Override
    public List<CourseView> findAll() {
        return converters.coursesToCourseViews(courseDao.findAll());
    }



    @Override
    public List<CourseView> findByStudentId(int studentId) {

        if (studentId > 0) {
            return converters.coursesToCourseViews(courseDao.findByStudentId(studentId));
        }

        return null;
    }



    @Override
    public boolean deleteCourse(int id) {

        if (id > 0) {
            return courseDao.removeCourse(courseDao.findById(id));
        }

        return false;
    }



}
