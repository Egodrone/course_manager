package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;



    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }



    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int id = CourseSequencer.nextCourseId();
        Course c1 = new Course(id, courseName, startDate, weekDuration);
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
        } else {
            throw new IllegalArgumentException(" invalid id number ");
        }

        return result;
    }



    @Override
    public Collection<Course> findByNameContains(String name) {

        if (name == null) {
            throw new IllegalArgumentException(" String name is null ");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException(" String name is empty ");
        }

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
        LocalDate setDate;

        if (end == null) {
            throw new IllegalArgumentException(" LocalDate date is null ");
        }

        for (Course c : courses) {
            setDate = c.getStartDate().plusWeeks(c.getWeekDuration());
            System.out.println(" End date: " + setDate);
            
            int diff = setDate.compareTo(end);
            if(diff > 0) {
                System.out.println(setDate + " is after than " + end);
            } else if (diff < 0) {
                System.out.println(setDate + " is before than " + end);
                result.add(c);
            } else {
                System.out.println(setDate + " is equal to " + end);
            }
        }

        return result;
    }



    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> result = new HashSet<>();
        LocalDate setDate;

        if (start == null) {
            throw new IllegalArgumentException(" LocalDate start is null ");
        }

        for (Course c : courses) {
            setDate = c.getStartDate().plusWeeks(c.getWeekDuration());
            System.out.println(" End date: " + setDate);

            int diff = setDate.compareTo(start);
            if(diff > 0) {
                System.out.println(setDate + " is after than " + start);
                result.add(c);
            } else if (diff < 0) {
                System.out.println(setDate + " is before than " + start);
            } else {
                System.out.println(setDate + " is equal to " + start);
            }
        }

        return result;
    }



    @Override
    public Collection<Course> findAll() {
        Collection<Course> result = new HashSet<>();

        for (Course c : courses) {
            result.add(c);
        }

        return result;
    }



    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> result = new HashSet<>();

        if (studentId <= 0) {
            throw new IllegalArgumentException(" Invalid studentId ");
        }

        for (Course c : courses) {
            for (Student s : c.getStudents()) {
                if (s.getId() == studentId) {
                    result.add(c);
                }
            }
        }

        return result;
    }



    @Override
    public boolean removeCourse(Course course) {
        boolean isRemoved = false;

        if (course == null) {
            throw new IllegalArgumentException(" Course course object is null ");
        }

        Iterator<Course> iterator = courses.iterator();

        while (iterator.hasNext()) {
            Course result = iterator.next();
            if (result.equals(course)) {
                iterator.remove();
                isRemoved = true;
            }
        }

        return isRemoved;
    }



    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }



}
