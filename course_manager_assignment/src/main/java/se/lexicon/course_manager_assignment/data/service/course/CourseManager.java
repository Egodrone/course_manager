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
import java.util.Iterator;
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

        Course createdCourse = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());
        System.out.println("createdCourse.getId() = " + createdCourse.getId());
        CourseView convertedCourse = converters.courseToCourseView(createdCourse);

        return convertedCourse;
    }



    @Override
    public CourseView update(UpdateCourseForm form) {

        if (form == null) {
            throw new IllegalArgumentException(" Form object is null ");
        }

        Course tmpCourse = courseDao.findById(form.getId());
        boolean checkBoolean = courseDao.removeCourse(tmpCourse);

        // If course exist, update it by id (removing older one and replacing)
        if (checkBoolean == true) {
            courseDao.removeCourse(tmpCourse);
            Course updatedCourse = courseDao.createCourse(form.getCourseName(), form.getStartDate(), form.getWeekDuration());
            System.out.println("createdCourse.getId() = " + updatedCourse.getId());
            CourseView convertedCourse = converters.courseToCourseView(updatedCourse);
            return convertedCourse;
        }

        return null;
    }



    @Override
    public List<CourseView> searchByCourseName(String courseName) {

        if (courseName == null) {
            throw new IllegalArgumentException(" String courseName is null ");
        }

        Collection<Course> allCourses = courseDao.findAll();
        List<CourseView> covList  = converters.coursesToCourseViews(allCourses);
        List<CourseView> result = new ArrayList<>();

        for (CourseView c: covList) {
            if (c.getCourseName().equalsIgnoreCase(courseName)) {
                result.add(c);
            }
        }

        return result;
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

        Collection<Course> allCourses = courseDao.findAll();
        List<CourseView> courseList  = converters.coursesToCourseViews(allCourses);

        for (CourseView c : courseList) {
            if (c.getId() == courseId) {
                //todo enroll student
                //courseDao.
                isAdded = true;
            }
        }

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
        Collection<Course> allCourses = courseDao.findAll();

        return converters.coursesToCourseViews(allCourses);
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

        Collection<Course> allCourses = courseDao.findAll();
        List<CourseView> covList  = converters.coursesToCourseViews(allCourses);

        Iterator<CourseView> iterator = covList.iterator();

        while (iterator.hasNext()) {
            CourseView result = iterator.next();
            if (result.getId() == id) {
                iterator.remove();
                isDeleted = true;
            }
        }

        return isDeleted;
    }



}
