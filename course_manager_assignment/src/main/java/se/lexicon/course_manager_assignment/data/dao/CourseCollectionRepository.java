package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Course;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;



public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;



    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }



    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course c1 = new Course(courseName, startDate, weekDuration);
        courses.add(c1);

        return c1;
    }



    @Override
    public Course findById(int id) {
        Course result = null;

        if (id > 0) {
            for (Course c : courses) {
                if (c.getId() == id) {
                    result = c;
                }
            }
        }

        return result;
    }



    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> result = new HashSet<>();

        for (Course c : courses) {
            if (c.getCourseName().contains(name)) {
                result.add(c);
            }
        }

        return result;
    }



    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> result = new HashSet<>();
        LocalDate endDate;

        if (end == null) {
            throw new IllegalArgumentException(" Invalid date ");
        }

        for (Course c : courses) {
            endDate = c.getStartDate().plusWeeks(c.getWeekDuration());
            System.out.println(endDate);
            if (endDate.isEqual(endDate) || end.isBefore(endDate)) {
                result.add(c);
            }
        }

        return result;
    }



    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        return null;
    }



    @Override
    public Collection<Course> findAll() {
        return null;
    }



    @Override
    public Collection<Course> findByStudentId(int studentId) {
        return null;
    }



    @Override
    public boolean removeCourse(Course course) {
        return false;
    }



    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }



}
