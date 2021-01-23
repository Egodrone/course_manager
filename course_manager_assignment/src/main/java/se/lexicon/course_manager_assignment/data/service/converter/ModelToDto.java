package se.lexicon.course_manager_assignment.data.service.converter;



import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Component
public class ModelToDto implements Converters {

    @Override
    public StudentView studentToStudentView(Student student) {

         return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
    }



    @Override
    public CourseView courseToCourseView(Course course) {

        return new CourseView(course.getId(),
                course.getCourseName(),
                course.getStartDate(),
                course.getWeekDuration(),
                studentsToStudentViews(course.getStudents()));
    }



    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> cvs = new ArrayList<>();

        for (Course c : courses) {
            cvs.add(courseToCourseView(c));
        }

        return cvs;
    }



    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {

        if (students == null) {
            throw new IllegalArgumentException(" Invalid students Collection value ");
        }

        List<StudentView> stv = new ArrayList<>();

        for (Student s : students) {
            stv.add(studentToStudentView(s));
        }

        return stv;
    }



}
